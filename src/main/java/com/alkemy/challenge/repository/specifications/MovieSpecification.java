package com.alkemy.challenge.repository.specifications;

import com.alkemy.challenge.dto.MovieFiltersDto;
import com.alkemy.challenge.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieSpecification {
    public Specification<Movie> getByFilters(MovieFiltersDto filtersDto){
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
            if (StringUtils.hasLength(filtersDto.getGenre())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("genre")),
                                "%" + filtersDto.getGenre() + "%"
                        )
                );
            }
            if (StringUtils.hasLength(filtersDto.getCreationDate())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("creationDate")),
                                "%" + filtersDto.getCreationDate() + "%"
                        )
                );
            }

            query.distinct(true);

            String orderByField = "creationDate";
            query.orderBy(
                    filtersDto.isAsc() ?
                            criteriaBuilder.asc(root.get(orderByField)) :
                            criteriaBuilder.desc(root.get(orderByField))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));

        };
    }
}
