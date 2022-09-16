package com.alkemy.challenge.repository.specifications;

import com.alkemy.challenge.dto.MovieFiltersDto;
import com.alkemy.challenge.entity.Genre;
import com.alkemy.challenge.entity.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

            if (StringUtils.hasLength(filtersDto.getCreationDate())) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(filtersDto.getCreationDate(),formatter);
                predicates.add(
                        criteriaBuilder.equal(root.<LocalDate>get("creationDate"),date)
                );
            }

            if (StringUtils.hasLength(filtersDto.getGenre())) {
                Join<Genre, Movie> join = root.join("genre", JoinType.INNER);
                Expression<String> genreId = join.get("id");
                predicates.add(
                        genreId.in(filtersDto.getGenre())
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
