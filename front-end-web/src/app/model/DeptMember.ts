import { Dept } from "./Dept";
import { Member } from "./Member";

export class DeptMember {

    department: Dept = <Dept>{}
    member: Member = <Member>{}
    startDate: Date
    endDate: Date
    remark: string

    constructor() {

    }

}