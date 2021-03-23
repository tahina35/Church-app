package com.churchofphilippi.webserver.model.pagination;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.Member;

import java.util.List;

public class DeptPage extends BasePage<Dept> {

    public DeptPage(int pageNo, int totalPages, long totalItems, List<Dept> depts) {
        super(pageNo, totalPages, totalItems, depts);
    }
}
