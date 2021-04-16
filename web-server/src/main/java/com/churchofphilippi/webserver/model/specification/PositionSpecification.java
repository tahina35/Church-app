package com.churchofphilippi.webserver.model.specification;

import com.churchofphilippi.webserver.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PositionSpecification implements Specification<Position> {

    private final String searchValue;

    @Override
    public Predicate toPredicate(Root<Position> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.isTrue(root.get("editable")));
        predicates.add(builder.like(builder.lower(root.get("name")), "%" + getSearchValue().toLowerCase() + "%"));

        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
