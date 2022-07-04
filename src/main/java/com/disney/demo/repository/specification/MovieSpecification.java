package com.disney.demo.repository.specification;

import com.disney.demo.dto.MovieFilterDto;
import com.disney.demo.entity.CharacterEntity;
import com.disney.demo.entity.GenderEntity;
import com.disney.demo.entity.MovieEntity;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

@Component
public class MovieSpecification {
    
    public Specification<MovieEntity> getByFilters(MovieFilterDto filterDto) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // en predicates vamos agregando los campos del filtro
            if (StringUtils.hasLength(filterDto.getTitle())) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), // ("..") nombre del atributo
                        "%" + filterDto.getTitle().toLowerCase() + "%"));
            }
             
            // if para list - collections
            if (filterDto.getIdGender() != null) {
                Join<GenderEntity, MovieEntity> join = root.join("idGender", JoinType.INNER); // ".." nombre del atributo
                Expression<String> genderId = join.get("id");
                predicates.add(genderId.in(filterDto.getIdGender()));
            }

            //remove duplicates
            query.distinct(true);

            //order resolver
            String orderByField = "name";
            query.orderBy(filterDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField)) );
        
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
