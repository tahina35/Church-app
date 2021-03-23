package com.churchofphilippi.webserver.model.customModels;

import com.churchofphilippi.webserver.model.Dept;
import com.churchofphilippi.webserver.model.Position;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberFilters {

    private List<Dept> depts;
    private List<Position> positions;
}
