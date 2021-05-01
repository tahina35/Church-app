import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Position } from 'src/app/model/Position';

@Component({
  selector: 'remove-position-modal',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">Remove Position</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <p><strong>Are you sure you want to delete <span class="text-primary">"{{position.name}}"</span> position?</strong></p>
  </div>
  <div class="modal-footer">
    <button type="button" class="btn btn-outline-secondary" (click)="modal.dismiss('cancel click')">Cancel</button>
    <button type="button" class="btn btn-danger" (click)="modal.close('Ok click')">Ok</button>
  </div>
  `
})
export class DeletePositionModal {

  @Input() position: Position;

  constructor(public modal: NgbActiveModal) {}
}