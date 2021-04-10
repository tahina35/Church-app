import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment-timezone'

@Pipe({
  name: 'fromNow',
  pure: true
})

export class FromNowPipe implements PipeTransform {
    transform(date: string, args?: any): any {
        return moment(date, "YYYY-MM-DD HH:mm").fromNow();
    }
}