package com.disney.demo.repository.specification;

import com.disney.demo.dto.CharacterFiltersDto;
import com.disney.demo.entity.Movie;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
public class CharacterSpecification {

    public Specification<Character> getByFilters(CharacterFiltersDto filtersDto) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // en predicates vamos agregando los campos del filtro
            if (StringUtils.hasLength(filtersDto.getName())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("denominacion")),
                        "%" + filtersDto.getName().toLowerCase() + "%"));
            }
            
            if (filtersDto.getAge() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("age"), filtersDto.getAge()));
            }
            
            // if para filtro de tipo double
//            if (filtersDto.getWeight() != null) {
//                predicates.add(criteriaBuilder.equal(
//                        root.get("weight"), filtersDto.getWeight()));
//            }

            // if para filtro de tipo Date
//            if (StringUtils.hasLength(filtersDto.getDate())) {
//                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                LocalDate date = LocalDate.parse(filtersDto.getDate(), formatter);
//                predicates.add(criteriaBuilder.equal(root.<LocalDate>get("fechaCreacion"), date));
//
//            }

            // if para list - collections
            if (!CollectionUtils.isEmpty(filtersDto.getMovies())) {
                Join<Character, Movie> join = root.join("characters", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDto.getMovies()));
            }

            //remove duplicates
            query.distinct(true);

            //order resolver
            String orderByField = "denominacion";
            query.orderBy(filtersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField)) );
        
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
