import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment-timezone'

@Pipe({
  name: 'weekDay',
  pure: true
})

export class WeekDayPipe implements PipeTransform {
  transform(date: string, args?: any): any {
    var tempDate = moment(date, "YYYY-MM-DD")  
    var weekday =  tempDate.weekday();
    return moment.weekdays(weekday) + ' (' + (tempDate.month() + 1) + '/' + tempDate.date() + ')'
  }
}