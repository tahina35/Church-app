import { Member } from "./Member";
import { Sermon } from "./Sermon";

export class SundayService {
    
    serviceType:number
    date:Date
    prayer: Member = new Member()
    hymn:string
    sermon: Sermon = new Sermon()
}