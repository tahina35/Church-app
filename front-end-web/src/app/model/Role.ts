import { Position } from "./Position";
import { Member } from "./Member";

export class Role {

    position: Position = <Position>{}
    member: Member = <Member>{}
    startDate: Date = new Date();
    endDate: Date = new Date();
    remark: string = "";

    constructor() {

    }

}