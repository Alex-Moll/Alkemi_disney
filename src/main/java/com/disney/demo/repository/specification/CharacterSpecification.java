package com.disney.demo.repository.specification;

import com.disney.demo.dto.CharacterFiltersDto;
import com.disney.demo.entity.MovieEntity;
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
import com.disney.demo.entity.CharacterEntity;

@Component
public class CharacterSpecification {

    public Specification<CharacterEntity> getByFilters(CharacterFiltersDto filtersDto) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            // en predicates vamos agregando los campos del filtro
            if (StringUtils.hasLength(filtersDto.getName())) {
                predicates.add(criteriaBuilder.like(
                        criteriaBuilder.lower(root.get("name")), // ("..") nombre del atributo
                        "%" + filtersDto.getName().toLowerCase() + "%"));
            }
            
             // if para filtro de tipo Integer
            if (filtersDto.getAge() != null) {
                predicates.add(criteriaBuilder.equal(
                        root.get("age"), filtersDto.getAge())); // ("..") nombre del atributo
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

            // if para filtro list - collections
            if (!CollectionUtils.isEmpty(filtersDto.getMovies())) { // pregunta si la lista no es vacia
                Join<MovieEntity,CharacterEntity> join = root.join("movies", JoinType.INNER); //nombre del atributo List de la Entidad character
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDto.getMovies()));
            }

            //remove duplicates
            query.distinct(true);

            //order resolver
            String orderByField = "name";
            query.orderBy(filtersDto.isASC() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField)) );
        
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }

}
