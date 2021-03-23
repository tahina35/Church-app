package com.churchofphilippi.webserver.model.searchSpecification;

import com.churchofphilippi.webserver.model.Dept;
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
public class DeptSpecification implements Specification<Dept> {

    private final String searchValue;

    @Override
    public Predicate toPredicate(Root<Dept> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.like(builder.lower(root.get("name")), "%" + getSearchValue().toLowerCase() + "%");
    }
}
