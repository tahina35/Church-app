import { Time } from "@angular/common";
import { Dept } from "./Dept";

export class Event {

    eventId:number
    title:string = ''
    startDate
    endDate
    allDay:boolean = false;
    color:string;
    department:Dept

}