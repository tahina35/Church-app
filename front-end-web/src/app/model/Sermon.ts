import { Member } from "./Member";

export class Sermon {

    sermonId:number
    mainVerse:string
    thumbnail:string
    url:string
    isUploaded: boolean = false;
    preacher:Member = <Member>{}

}