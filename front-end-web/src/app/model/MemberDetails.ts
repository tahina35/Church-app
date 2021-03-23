
import { Dept } from "./Dept";
import { DeptMember } from "./DeptMember";
import { Member } from "./Member";
import { Position } from "./Position";
import { Role } from "./Role";

export interface MemberDetails {

   member: Member
   allDepts: Dept[]
   allPositions: Position[]
   memberDepts: DeptMember[]
   memberPositions: Role[]

}