package com.churchofphilippi.webserver.model.searchSpecification;

import com.churchofphilippi.webserver.model.Member;
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
public class MemberSpecification implements Specification<Member> {

    private final String searchValue;

    @Override
    public Predicate toPredicate(Root<Member> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(builder.like(builder.lower(root.get("fname")), "%" + getSearchValue().toLowerCase() + "%"));
        predicates.add(builder.like(builder.lower(root.get("lname")), "%" + getSearchValue().toLowerCase() + "%"));
        predicates.add(builder.like(builder.lower(root.get("email")), "%" + getSearchValue().toLowerCase() + "%"));

        return builder.or(predicates.toArray(new Predicate[0]));
    }
}
