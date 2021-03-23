package com.churchofphilippi.webserver.model.pagination;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BasePage<T> {

    private int pageNo;
    private int totalPages;
    private long totalItems;
    private List<T> content;
}
