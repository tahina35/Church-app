import { Dept } from "./Dept";
import { Position } from "./Position";

export interface MemberFilters {

    depts: Dept[]
    positions: Position[]

}