import { Pipe, PipeTransform } from '@angular/core';


@Pipe({
  name: 'gender',
  pure: true
})

export class GenderPipe implements PipeTransform {
  transform(gender: string, args?: any): any {
    let ret = ''
    switch(gender) { 
        case 'M': { 
           ret = 'Male';
           break; 
        } 
        case 'F': { 
           ret = 'Female';
           break; 
        } 
    } 
    return ret;
  }
}