package com.churchofphilippi.webserver.model.pagination;

import com.churchofphilippi.webserver.model.Member;
import java.util.List;


public class MemberPage extends BasePage<Member> {

    public MemberPage(int pageNo, int totalPages, long totalItems, List<Member> members) {
        super(pageNo, totalPages, totalItems, members);
    }
}
