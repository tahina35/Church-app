import { componentFactoryName } from '@angular/compiler';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { Dept } from 'src/app/model/Dept';
import { DeptMember } from 'src/app/model/DeptMember';
import { MemberDetails } from 'src/app/model/MemberDetails';
import { Position } from 'src/app/model/Position';
import { Role } from 'src/app/model/Role';
import { MemberService } from 'src/app/services/member.service';
import { RemoveFromDeptModal } from './remove-from-dept.modal';
import { RemovePosition } from './remove-position.modal';

@Component({
  selector: 'app-update-member',
  templateUrl: './update-member.component.html',
  styleUrls: ['./update-member.component.scss']
})
export class UpdateMemberComponent implements OnInit {

  memberId: number;
  memberDetails: MemberDetails;
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
        this.memberId = +params['id'];
        this.getMemberDetails(this.memberId);
      }
    )
  }

  getMemberDetails(id: number) {
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

  updateDept() {
    this.deptMember.member.memberId = this.memberDetails.member.memberId;
    this.deptMember.startDate = new Date();
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
    this.modalService.dismissAll();
  }

  updatePosition() {
    this.role.member.memberId = this.memberDetails.member.memberId
    this.role.startDate = new Date();
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
    console.log(content);
    this.error = "";
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
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

  removeFromDept(dept: DeptMember) {
    const modalRef = this.modalService.open(RemoveFromDeptModal, {ariaLabelledBy: 'modal-basic-title'})
    modalRef.componentInstance.dept = dept;
    modalRef.result.then(
      () => {
        this.memberService.removeFromDepartment(dept).subscribe(
          () => {
            this.getMemberDetails(this.memberId);
          },
          (err) => {
            this.error = err;
          }
        )
      }
    )
  }

  removePosition(role: Role) {
    const modalRef  = this.modalService.open(RemovePosition, {ariaLabelledBy: 'modal-basic-title'});
    modalRef.componentInstance.role = role;
    modalRef.result.then(
      () => {
        this.memberService.removePosition(role).subscribe(
          () => {
            this.getMemberDetails(this.memberId);
          },
          (err) => {
            this.error = err;
          }
        )
      }
    )
  }

}
