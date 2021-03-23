import { Component, OnInit } from '@angular/core';
import { Member } from 'src/app/model/Member';
import { Page } from 'src/app/model/Page';
import { MemberService } from 'src/app/services/member.service';
import { ActivatedRoute } from '@angular/router';
import { MemberFilters } from 'src/app/model/MemberFilters';
import { Select2OptionData } from 'ng-select2';
import { Dept } from 'src/app/model/Dept';
import { Position } from 'src/app/model/Position';


@Component({
  selector: 'app-display-member',
  templateUrl: './display-member.component.html',
  styleUrls: ['./display-member.component.scss']
})
export class DisplayMemberComponent implements OnInit {

  totalPages:number;
  members: Member[];
  allMembers: Member[];
  error?: string;
  currentPage: number = 1;
  searchValue:string = '';
  totalItems:number = 0;
  deptFilterValue: string = '';
  positionFilterValue: string = '';
  deptFilterData: Array<Select2OptionData> = [];
  positionFilterData: Array<Select2OptionData> = [];
  

  constructor(private memberService: MemberService) { }

  ngOnInit(): void {
    this.findAllMembers();
    this.getFiltersData();
  }

  findAllMembers() {
    this.memberService.findPaginated(this.currentPage).subscribe(
      (data: Page<Member>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.members = data.content;
        this.allMembers = data.content;;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  findAllMembersPaginated() {
    this.memberService.findPaginated(this.currentPage).subscribe(
      (data: Page<Member>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.members = data.content;
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
    this.memberService.search(this.searchValue, this.currentPage).subscribe(
      (data: Page<Member>) => {
        this.totalPages = data.totalPages;
        this.totalItems = data.totalItems;
        this.members = data.content;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  onPageChange() {
    if(this.searchValue === '') {
      this.findAllMembersPaginated();
    } else {
      this.search();
    }
  }

  getFiltersData() {
    this.memberService.getFiltersData().subscribe(
      (data: MemberFilters) => {
        const filters: MemberFilters = data;
        console.log(filters)
        this.setFilters(filters);
      },
      (err) => {
        this.error = err;
      }
    );
  }

  setFilters(filters: MemberFilters) {
    this.deptFilterData = filters.depts.map(
      (dept: Dept) => {
        return ({id: dept.deptId.toString(), text: dept.name});
      }
    );

    this.positionFilterData = filters.positions.map(
      (position: Position) => {
        return ({id: position.positionId.toString(), text: position.name});
      }
    );
  }

}
