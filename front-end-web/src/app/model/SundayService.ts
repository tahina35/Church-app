import { Sermon } from "./Sermon";

export class SundayService {
    
    serviceType:number
    date:Date
    benediction:string
    confessionOfFaith:string
    hymn:string
    
    sermon: Sermon = new Sermon()
}