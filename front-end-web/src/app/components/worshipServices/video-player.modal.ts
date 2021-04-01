import { Component, Input } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'video-player-modal',
  template: `
  <div class="modal-header">
    <h4 class="modal-title" id="modal-title">{{title}}</h4>
    <button type="button" class="close" aria-describedby="modal-title" (click)="modal.dismiss('Cross click')">
      <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <youtube-player 
    videoId="{{id}}" 
    suggestedQuality="highres"
    [width]="765"
    [height]="425"  >
    </youtube-player>
  </div>
  `
})
export class VideoPlayerModal {

    @Input() title: string;
    @Input() id: string;

  constructor(public modal: NgbActiveModal) {}
}