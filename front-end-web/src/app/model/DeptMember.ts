import { Dept } from "./Dept";
import { Member } from "./Member";

export class DeptMember {

    department: Dept = <Dept>{}
    member: Member = <Member>{}
    startDate: Date = new Date();
    endDate: Date = new Date();
    remark: string = "";

    constructor() {

    }

}