import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { DeptMember } from 'src/app/model/DeptMember';

@Component({
  selector: 'remove-from-dept-modal',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">Remove from department</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <p><strong>Are you sure you want to remove <i>{{dept.member.fname}} {{dept.member.lname}}</i> from 
    <span class="text-primary">"{{dept.department.name}}"</span>?</strong></p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="modal.close('Ok click')">Ok</button>
  </div>
  `
})
export class RemoveFromDeptModal {

  @Input() dept: DeptMember;

  
  constructor(public modal: NgbActiveModal) {}
}