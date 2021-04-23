import { Member } from "./Member";
import { Sermon } from "./Sermon";

export class SundayService {
    
    serviceType:number
    date:Date
    prayer: Member = new Member()
    worshipAndPraise:string = "Together"
    confessionOfFaith:string = "Apostle's Creed"
    churchNews:string = "Video"
    offering:string = "Together"
    hymn:string
    sermon: Sermon = new Sermon()
}