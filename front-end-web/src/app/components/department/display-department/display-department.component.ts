import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Dept } from 'src/app/model/Dept';
import { Page } from 'src/app/model/Page';
import { DepartmentService } from 'src/app/services/department.service';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { DeleteDeptModal } from './delete-department.modal';

@Component({
  selector: 'app-display-department',
  templateUrl: './display-department.component.html',
  styleUrls: ['./display-department.component.scss']
})
export class DisplayDepartmentComponent implements OnInit {

  totalPages:number;
  departments: Dept[];
  AllDepts: Dept[];
  error?: string;
  currentPage: number = 1;
  searchValue:string = '';
  dept: Dept = {} as Dept;
  totalItems:number = 0;
  selectedDept:number = 0;

  constructor(private deptService: DepartmentService, private route: ActivatedRoute, private modalService: NgbModal) { }

  ngOnInit(): void {
    this.findAllDepts();
  }

  findAllDepts() {
    this.deptService.findPaginated(this.currentPage).subscribe(
      (data: Page<Dept>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.departments = data.content;
        this.AllDepts = data.content;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  findAllDeptsPaginated() {
    this.deptService.findPaginated(this.currentPage).subscribe(
      (data: Page<Dept>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.departments = data.content;
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
    this.deptService.search(this.searchValue, this.currentPage).subscribe(
      (data: Page<Dept>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.departments = data.content;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  onPageChange() {
    if(this.searchValue === '') {
      this.findAllDeptsPaginated();
    } else {
      this.search();
    }
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {},
      (reason) => {
        this.resetModal();
      }
    )
  }

  resetModal() {
    this.selectedDept = 0;
    this.dept = {} as Dept;
  }

  addDept() {
    if(this.selectedDept) {
      this.dept.parentDept = { deptId: this.selectedDept} as Dept;
    } else {
      this.dept.parentDept = null;
    }

    if(!this.dept.creationDate) {
      this.dept.creationDate = new Date();
    }

    this.deptService.add(this.dept).subscribe(
      (data) => {
        this.currentPage = 1;
        this.findAllDepts();
        this.resetModal
        this.modalService.dismissAll();
      },
      (err) => {
        this.error = err;
      }
    )
  }

  updateDepartment(content, department: Dept) {
    
    if(department.parentDept) {
      this.selectedDept = department.parentDept.deptId;
    } else {
      this.selectedDept = 0;
    }
    this.dept.deptId = department.deptId;
    this.dept.creationDate = department.creationDate;
    this.dept.name = department.name;
    console.log(this.dept)
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {},
      (reason) => {
        this.resetModal();
      }
    )
  }

  deleteDepartment(dept: Dept) {
    const modalRef  = this.modalService.open(DeleteDeptModal, {ariaLabelledBy: 'modal-basic-title'});
    modalRef.componentInstance.dept = dept;
    modalRef.result.then(
      () => {
        this.deptService.delete(dept.deptId).subscribe(
          () => {
            this.currentPage = 1;
            this.findAllDepts();
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
