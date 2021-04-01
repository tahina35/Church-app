import { Pipe, PipeTransform } from '@angular/core';
import { environment } from 'src/environments/environment';


@Pipe({
  name: 'servicetype',
  pure: true
})

export class ServiceTypePipe implements PipeTransform {
  transform(serviceType: number, args?: any): any {
    const type = environment.sundayServiceType;
    const val  = type.find(
        (element) => {
            return element.id == serviceType;
        }
    );
    return val.name;
  }
}