package com.churchofphilippi.webserver.model.pagination;

import com.churchofphilippi.webserver.model.Position;

import java.util.List;

public class PositionPage extends BasePage<Position>  {

    public PositionPage(int pageNo, int totalPages, long totalItems, List<Position> positions) {
        super(pageNo, totalPages, totalItems, positions);
    }
}
