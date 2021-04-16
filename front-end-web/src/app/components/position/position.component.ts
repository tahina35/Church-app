import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Position } from 'src/app/model/Position';
import { Page } from 'src/app/model/Page';
import { PositionService } from 'src/app/services/position.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DeletePositionModal } from './delete-position.modal';

@Component({
  selector: 'app-position',
  templateUrl: './position.component.html',
  styleUrls: ['./position.component.scss']
})
export class PositionComponent implements OnInit {

  totalPages:number;
  positions: Position[];
  AllPositions: Position[];
  error?: string;
  currentPage: number = 1;
  searchValue:string = '';
  position: Position = new Position();
  totalItems:number = 0;
  selectedPosition:number = 0;

  constructor(private positionService: PositionService, private route: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.findAllPositions();
  }

  findAllPositions() {
    this.positionService.findPaginated(this.currentPage).subscribe(
      (data: Page<Position>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.positions = data.content;
        this.AllPositions = data.content;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  findAllPositionsPaginated() {
    this.positionService.findPaginated(this.currentPage).subscribe(
      (data: Page<Position>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.positions = data.content;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  onSubmit() {
    this.currentPage = 1;
    this.search();
  }

  search() {
    this.positionService.search(this.searchValue, this.currentPage).subscribe(
      (data: Page<Position>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.positions = data.content;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  onPageChange() {
    if(this.searchValue === '') {
      this.findAllPositionsPaginated();
    } else {
      this.search();
    }
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {},
      (reason) => {
        this.position = new Position();
      }
    )
  }

  addPosition() {
    this.positionService.add(this.position).subscribe(
      (data) => {
        this.currentPage = 1;
        this.findAllPositions();
        this.position = new Position();
        this.modalService.dismissAll();
      },
      (err) => {
        this.error = err;
      }
    )
  }

  updatePosition(content, pos: Position) {
    this.position.positionId = pos.positionId;
    this.position.name = pos.name;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {},
      (reason) => {
        this.position = new Position();
      }
    )
  }

  deletePosition(position: Position) {
    const modalRef  = this.modalService.open(DeletePositionModal, {ariaLabelledBy: 'modal-basic-title'});
    modalRef.componentInstance.position = position;
    modalRef.result.then(
      () => {
        this.positionService.delete(position.positionId).subscribe(
          () => {
            this.currentPage = 1;
            this.findAllPositions();
          },
          (err) => {
            this.error = err;
            setTimeout(()=>{                           //<<<---using ()=> syntax
              this.error = '';
            }, 8000);
          }
        )
      }
    )
  }

}
