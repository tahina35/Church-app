import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { CalendarOptions } from '@fullcalendar/angular';
import { NgbDate, NgbDateStruct, NgbModal, NgbTimeStruct } from '@ng-bootstrap/ng-bootstrap';
import { Select2OptionData } from 'ng-select2';
import { Dept } from 'src/app/model/Dept';
import { Event } from 'src/app/model/Event';
import { DepartmentService } from 'src/app/services/department.service';
import { EventService } from 'src/app/services/event.service';
import * as moment from 'moment-timezone'
import { DeleteEventModal } from './delete-event.modal';

@Component({
  selector: 'app-event',
  templateUrl: './event.component.html',
  styleUrls: ['./event.component.scss']
})
export class EventComponent implements OnInit {

  error?: string;
  formErrors: string;
  startDate: NgbDateStruct;
  endDate: NgbDateStruct;
  startTime: NgbTimeStruct;
  endTime: NgbTimeStruct; 
  calendarOptions: CalendarOptions;
  event: Event = new Event()
  isChurchwide: boolean = true;
  selectedDept:string = '';
  allDepts: Array<Select2OptionData> = [];
  colors;
  selectedColor: string = '#1273DE';
  allEvents: Event[];
  @ViewChild('updateEvent') public content: ElementRef;

  constructor(private modalService: NgbModal, private deptService: DepartmentService,private eventService: EventService) { 
    
  }

  ngOnInit(): void {
    this.getDepts();
    this.initializeCalendar();
    this.getEvents();
  }

  initializeCalendar() {
    this.colors = ['#795548', '#FE0118', '#FF5722', '#FCCB00', '#7BDCB5', '#008B02', '#006B76', '#1273DE', '#004DCF', '#5300EB'];
    this.calendarOptions = {
      initialView: 'dayGridMonth',
      headerToolbar: {
        left: 'prev,next,today',
        center: 'title',
        right: 'dayGridMonth,dayGridWeek,dayGridDay,listWeek'
      },
      dayMaxEvents: true,
      displayEventTime: true,
      forceEventDuration: true,
      defaultAllDay: false,
      defaultTimedEventDuration: '23:59',
      eventClick: this.updateEvent.bind(this),
      editable: true
    };
  }

  getEvents() {
    this.eventService.findEvents().subscribe(
      (data: Event[]) => {
        console.log(data)
        this.calendarOptions.events = data.map(
          (e: Event) => {
            
            return {
              id: e.eventId.toString(),
              title: e.department ? e.department.name + ': ' + e.title : e.title,
              start: e.startDate,
              end: e.allDay ? this.formatEndDate(e.endDate) : e.endDate,
              allDay: e.allDay,
              color: e.color,
              department: e.department ? e.department.deptId : null,
              titleVal: e.title
            };
          }
        )
      }
    )
  }

  formatEndDate(date: string) {
    return moment(date).add(1, 'd').toISOString();
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

  updateEvent(info) {
    this.event.eventId = info.event.id;
    this.event.allDay = info.event.allDay;
    this.event.title = info.event.extendedProps.titleVal;
    const start: Date = info.event.start;
    const end: Date = info.event.end;
    this.startDate = new NgbDate(start.getFullYear(), start.getMonth() + 1, start.getDate());
    this.endDate = new NgbDate(end.getFullYear(), end.getMonth() + 1, info.event.allDay ? end.getDate() -1 : end.getDate());
    this.selectedColor = info.event.backgroundColor
    if(!info.event.allDay) {
      this.startTime = { hour: start.getHours(), minute: start.getMinutes(), second: 0 };
      this.endTime = { hour: end.getHours(), minute: end.getMinutes(), second: 0 };
    }
    if(info.event.extendedProps.department) {
      this.isChurchwide = false;
      this.selectedDept = info.event.extendedProps.department;
      this.isChurchwide = false;
    }

    this.modalService.open(this.content, {ariaLabelledBy: 'modal-basic-title'});
  }

  deleteEvent() {
    this.modalService.open(DeleteEventModal, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {
        this.eventService.delete(this.event.eventId).subscribe(
          () => {
            this.getEvents();
            this.resetModal();
            this.modalService.dismissAll();
          },
          (err) => {
            this.error = err;
          }
        )
      }
    )
  }

  add(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {},
      (reason) => {
       this.resetModal();
      }
    )
  }

  saveEvent() {
    if(this.validateForm() && this.validateDate()) {
      let sdate: string = this.startDate.year + '-' + this.startDate.month + '-' + this.startDate.day;
      let edate: string = this.endDate.year + '-' + this.endDate.month + '-' + this.endDate.day;
      if(this.event.allDay) {
        this.event.startDate = this.formatDate(sdate);
        this.event.endDate = this.formatDate(edate);
      } else {
        let stime: string = this.startTime.hour + ':' + this.startTime.minute;
        let etime: string = this.endTime.hour + ':' + this.endTime.minute;
        this.event.startDate = this.formatDate(sdate + ' ' + stime);
        this.event.endDate = this.formatDate(edate + ' ' + etime);
      }
  
      if(!this.isChurchwide) {
        this.event.department = new Dept();
        this.event.department.deptId = +this.selectedDept;
      }

      this.event.color = this.selectedColor;
      console.log(this.event)

      this.eventService.add(this.event).subscribe(
        (res: Event) => {
          this.getEvents();
          this.resetModal();
          this.modalService.dismissAll();
        },
        (err) => {
          this.error = err;
        }
      )
    }
    
  }

  formatDate(date: string) {
    return moment.utc(date).toISOString();
  }

  resetModal() {
    this.isChurchwide = true;
    this.event = new Event();
    this.formErrors = '';
    this.selectedDept = '';
    this.startDate = null;
    this.endDate = null;
    this.startTime = null;
    this.endTime = null;
    this.selectedColor = '#1273DE';
  }

  validateForm() {
    let isValid: boolean = true;
    if(!this.event.allDay) {
      if(!this.startTime || !this.endTime) {
        isValid = false;
      }
    }

    if(!this.isChurchwide) {
      if(!this.selectedDept){
        isValid = false;
      }
    }

    if(!this.startDate || !this.endDate || !this.event.title || !this.selectedColor) {
      isValid = false;
    }

    this.formErrors = isValid ? "" : "Please fill out the required fields";
    return isValid;
  }

  validateDate() {
    let isValid: boolean = true;
    let err = "";
    const sdate = new NgbDate(this.startDate.year, this.startDate.month, this.startDate.day);
    const edate = new NgbDate(this.endDate.year, this.endDate.month, this.endDate.day);
    let stime = '';
    let etime = '';
    if(!this.event.allDay) {
      stime = this.startTime.hour + ':' + this.startTime.minute;
      etime = this.endTime.hour + ':' + this.endTime.minute;
    }
    
    if(edate.before(sdate)) {
      isValid = false;
      err = 'Start Date must be before End Date';
      this.endTime
    } else if(!this.event.allDay && edate.equals(sdate) && etime.localeCompare(stime) === -1 ) {
      isValid = false;
      err = 'Start Time must be before End Time';
    }
    
    this.formErrors = err;
    return isValid;
  }

  handleChange(event) {
    this.selectedColor = event.color.hex;
  }

  onChange() {
    if(this.isChurchwide) {
      this.selectedDept = '';
      this.event.department = null;
    }
  }

}
