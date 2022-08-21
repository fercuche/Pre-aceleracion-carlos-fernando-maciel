package com.alkemy.challenge.repository.specifications;

import com.alkemy.challenge.dto.CharacterFiltersDto;
import com.alkemy.challenge.entity.Character;
import com.alkemy.challenge.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    public Specification<Character> getByFilters(CharacterFiltersDto filtersDto){
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasLength(filtersDto.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDto.getName().toLowerCase() + "%"
                        )
                );
            }
            if (StringUtils.hasLength((filtersDto.getAge()))) {
                Integer age = Integer.valueOf(filtersDto.getAge());
                predicates.add(
                        criteriaBuilder.equal(root.get("age"),age)
                );
            }
            if (StringUtils.hasLength((filtersDto.getWeight()))){
                Double weight = Double.valueOf(filtersDto.getWeight());
                predicates.add(
                        criteriaBuilder.equal(root.get("weight"),weight)

                );
            }
            if (!CollectionUtils.isEmpty(filtersDto.getMovies())){
                Join<Movie, Character> join = root.join("movies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDto.getMovies()));
            }

            query.distinct(true);

            String orderByField = "name";
            query.orderBy(
                    filtersDto.isAsc() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
