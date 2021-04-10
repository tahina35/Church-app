import { Component, OnInit } from '@angular/core';
import { NgbCalendar, NgbDate, NgbDateParserFormatter, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';
import { Dept } from 'src/app/model/Dept';
import { Select2OptionData } from 'ng-select2';
import { DepartmentService } from 'src/app/services/department.service';
import { News } from 'src/app/model/News';
import { NewsService } from 'src/app/services/news.service';
import { DeleteNewsModal } from './delete-news.modal';

@Component({
  selector: 'app-news',
  templateUrl: './news.component.html',
  styleUrls: ['./news.component.scss']
})
export class NewsComponent implements OnInit {

  // date range
  hoveredDate: NgbDate | null = null;
  fromDate: NgbDate | null;
  toDate: NgbDate | null;

  Editor = ClassicEditor;
  formErrors: string;
  isChurchwide: boolean = true;
  error?: string;
  allDepts: Array<Select2OptionData> = [];
  selectedDept:string = '';
  newsList: News[];
  news: News = new News();
  timeRange: number = 1;
  
  constructor(private modalService: NgbModal, 
              private calendar: NgbCalendar, 
              public formatter: NgbDateParserFormatter,
              private deptService: DepartmentService,
              private newsService: NewsService) { 
    this.resetDates();
  }

  ngOnInit(): void {
    this.getDepts();
    this.getNews();
  }

  resetDates() {
    this.fromDate = this.calendar.getToday();
    this.toDate = this.calendar.getNext(this.calendar.getToday(), 'd', 7);
  }

  open(content) {
    this.modalService.open(content, {size: 'xl'}).result.then(
      () => {},
      (reason) => {
       this.resetModal();
      }
    )
  }

  resetModal() {
    this.resetDates();
    this.isChurchwide = true;
    this.news = new News();
    this.formErrors = '';
    this.selectedDept = '';
  }

  getDepts() {
    this.deptService.findAll().subscribe(
      (depts: Dept[]) => {
        this.allDepts = depts.map(
          (dept: Dept) => {
            return ({id: dept.deptId.toString(), text: dept.name});
          }
        );
      }
    )
  }

  getNews() {
    this.newsService.findNews(this.timeRange).subscribe(
      (data: News[]) => {
        console.log(data)
        this.newsList = data;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  saveNews() {
    if(!this.validateForm()) {
      this.formErrors = "Please fill out the required fields";
    } else {
      this.news.startDate = new Date(this.formatter.format(this.fromDate));
      this.news.endDate = new Date(this.formatter.format(this.toDate));

      if(!this.isChurchwide) {
        this.news.department = new Dept();
        this.news.department.deptId = +this.selectedDept;
      }

      this.newsService.add(this.news).subscribe(
        (res: News) => {
          this.timeRange = 1;
          this.getNews();
          this.resetModal();
          this.modalService.dismissAll();
        },
        (err) => {
          this.error = err;
        }
      )
    }
  }

  update(content, data) {
    this.news.newsId = data.newsId;
    console.log(data.startDate);
    const from = this.formatter.parse(data.startDate);
    const to = this.formatter.parse(data.endDate);
    this.fromDate = new NgbDate(from.year, from.month, from.day);
    this.toDate = new NgbDate(to.year, to.month, to.day);
    if(data.department) {
      this.selectedDept = data.department.deptId;
      this.isChurchwide = false;
    }
    this.news.content = data.content;
    this.modalService.open(content, {size: 'xl'});
  }

  deleteNews(news: News) {
    this.modalService.open(DeleteNewsModal, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {
        this.newsService.delete(news.newsId).subscribe(
          () => {
            this.timeRange = 1;
            this.getNews();
          },
          (err) => {
            this.error = err;
          }
        )
      }
    )
  }

  validateForm(): boolean {
    let isValid: boolean = true;
    if(!this.isChurchwide) {
      if(!this.selectedDept){
        isValid = false;
      }
    }

    if(!this.fromDate || !this.toDate || !this.news.content) {
      isValid = false;
    }

    return isValid;
  }

  onChange() {
    if(this.isChurchwide) {
      this.selectedDept = '';
      this.news.department = null;
    }
  }

  onTimeRangeChange() {
    this.getNews();
  }

  // date range 
  onDateSelection(date: NgbDate) {
    if (!this.fromDate && !this.toDate) {
      this.fromDate = date;
    } else if (this.fromDate && !this.toDate && date && date.after(this.fromDate)) {
      this.toDate = date;
    } else {
      this.toDate = null;
      this.fromDate = date;
    }
  }

  isHovered(date: NgbDate) {
    return this.fromDate && !this.toDate && this.hoveredDate && date.after(this.fromDate) && date.before(this.hoveredDate);
  }

  isInside(date: NgbDate) {
    return this.toDate && date.after(this.fromDate) && date.before(this.toDate);
  }

  isRange(date: NgbDate) {
    return date.equals(this.fromDate) || (this.toDate && date.equals(this.toDate)) || this.isInside(date) || this.isHovered(date);
  }

  validateInput(currentValue: NgbDate | null, input: string): NgbDate | null {
    const parsed = this.formatter.parse(input);
    return parsed && this.calendar.isValid(NgbDate.from(parsed)) ? NgbDate.from(parsed) : currentValue;
  }

 

}
