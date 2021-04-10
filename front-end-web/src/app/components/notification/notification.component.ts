import { Component, OnInit } from '@angular/core';
import { NgbDateParserFormatter, NgbModal } from '@ng-bootstrap/ng-bootstrap';
import * as moment from 'moment';
import { Select2OptionData } from 'ng-select2';
import { Dept } from 'src/app/model/Dept';
import { Notification } from 'src/app/model/Notification';
import { DepartmentService } from 'src/app/services/department.service';
import { NotificationService } from 'src/app/services/notification.service';
import { DeleteNotificationModal } from './Delete-notification.modal';

@Component({
  selector: 'app-notification',
  templateUrl: './notification.component.html',
  styleUrls: ['./notification.component.scss']
})
export class NotificationComponent implements OnInit {

  formErrors: string;
  isChurchwide: boolean = true;
  error?: string;
  allDepts: Array<Select2OptionData> = [];
  selectedDept:string = '';
  notifications: Notification[];
  notification: Notification = new Notification();

  constructor(private modalService: NgbModal, 
              private deptService: DepartmentService,
              public formatter: NgbDateParserFormatter,
              private notificationService: NotificationService) { }

  ngOnInit(): void {
    this.getDepts();
    this.getNotifications();
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
    this.isChurchwide = true;
    this.notification = new Notification();
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

  getNotifications() {
    this.notificationService.findLast30Days().subscribe(
      (data: Notification[]) => {
        console.log(data)
        this.notifications = data;
      },
      (err) => {
        this.error = err;
      }
    )
  }

  saveNotification() {
    if(!this.notification.creationDate) {
      let now = moment().local(true);
      this.notification.creationDate = now.format('YYYY-MM-DDTHH:mm:ss');
      console.log(this.notification.creationDate)
    }
    
    if(!this.isChurchwide) {
      this.notification.department = new Dept();
      this.notification.department.deptId = +this.selectedDept;
    }

    this.notificationService.add(this.notification).subscribe(
      (res: Notification) => {
        this.getNotifications();
        this.resetModal();
        this.modalService.dismissAll();
      },
      (err) => {
        this.error = err;
      }
    )
  }

  update(content, data) {
    this.notification.notificationId = data.notificationId;
    this.notification.creationDate = data.creationDate;
    if(data.department) {
      this.selectedDept = data.department.deptId;
      this.isChurchwide = false;
    }
    this.notification.content = data.content;
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }

  deleteNotification(notification: Notification) {
    this.modalService.open(DeleteNotificationModal, {ariaLabelledBy: 'modal-basic-title'}).result.then(
      () => {
        this.notificationService.delete(notification.notificationId).subscribe(
          () => {
            this.getNotifications();
          },
          (err) => {
            this.error = err;
          }
        )
      }
    )
  }

  onChange() {
    if(this.isChurchwide) {
      this.selectedDept = '';
      this.notification.department = null;
    }
  }

}
