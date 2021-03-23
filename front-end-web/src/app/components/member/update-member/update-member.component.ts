import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbDate, NgbDateStruct, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Dept } from 'src/app/model/Dept';
import { DeptMember } from 'src/app/model/DeptMember';
import { MemberDetails } from 'src/app/model/MemberDetails';
import { Position } from 'src/app/model/Position';
import { Role } from 'src/app/model/Role';
import { MemberService } from 'src/app/services/member.service';

@Component({
  selector: 'app-update-member',
  templateUrl: './update-member.component.html',
  styleUrls: ['./update-member.component.scss']
})
export class UpdateMemberComponent implements OnInit {

  memberDetails: MemberDetails;
  start?: NgbDateStruct;
  end?: NgbDateStruct;
  error?: string;
  role: Role = new Role();
  deptMember: DeptMember = new DeptMember();

  constructor(private route: ActivatedRoute, 
              private memberService: MemberService, 
              private modalService: NgbModal) { 
  }

  ngOnInit(): void {
    this.route.params.subscribe(
      (params) => {
        const id: number = +params['id'];
        this.memberService.getMemberDetails(id).subscribe(
          (data: MemberDetails) => {
            console.log(data);
            this.memberDetails = data;
          },
          (err) => {
            this.error = err;
          }
        );
      }
    )
  }

  updateDept() {
    if(!this.validateDates()) {
      return;
    }
    this.deptMember.member.memberId = this.memberDetails.member.memberId;
    this.deptMember.startDate = new Date(this.start.year + '-' + this.start.month + '-' + this.start.day);
    this.deptMember.endDate = new Date(this.end.year + '-' + this.end.month + '-' + this.end.day);
    console.log(this.deptMember);

    this.memberService.addToDepartment(this.deptMember).subscribe(
      (data: DeptMember) => {
        this.memberDetails.memberDepts.push(data);
        this.filterDepts();
        this.dismissModal();
      },
      (err) => {
        this.error = err;
      }
    );
  }

  dismissModal() {
    this.deptMember = new DeptMember();
    this.role = new Role();
    this.start = null;
    this.end = null;
    this.modalService.dismissAll();
  }

  updatePosition() {
    if(!this.validateDates()) {
      return;
    }
    this.role.member.memberId = this.memberDetails.member.memberId
    this.role.startDate = new Date(this.start.year + '-' + this.start.month + '-' + this.start.day);
    this.role.endDate = new Date(this.end.year + '-' + this.end.month + '-' + this.end.day);
    console.log(this.role);
    this.memberService.assignRole(this.role).subscribe(
      (data: Role) => {
        this.memberDetails.memberPositions.push(data);
        this.filterPositions();
        this.dismissModal();
      },
      (err) => {
        this.error = err;
      }
    );
  }

  open(content) {
    this.error = "";
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }

  validateDates() :boolean {
    let sd: NgbDate = new NgbDate(this.start.year, this.start.month, this.start.day)  ;
    let ed: NgbDate = new NgbDate(this.end.year, this.end.month, this.end.day)  ;
    if(sd.after(ed)) {
      this.error = "Ivalid dates. Start date must be before end date!";
      return false;
    }
    return true;
  }

  filterDepts() {
    this.memberDetails.allDepts = this.memberDetails.allDepts.filter(
      (dept: Dept) => {
        return dept.deptId != this.deptMember.department.deptId;
      }
    );
  }

  filterPositions() {
    this.memberDetails.allPositions = this.memberDetails.allPositions.filter(
      (position: Position) => {
        return position.positionId != this.role.position.positionId;
      }
    );
  }

}
