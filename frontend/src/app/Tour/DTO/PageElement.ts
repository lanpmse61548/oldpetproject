import { ImgDivDto } from "./ImgDivDto";

export class PageElement{
    id:string;
    style:string;
    content:string;
    contentArr=[];
    fileList=[];
}


export class FileWrapper {
    url:String;
    file : File;
    num : Number;
    name :String;
    divID: String;
  }
  export class CreatePageDto{
    id:number;
    content:string;
   
    removedDiv=[];
    removedImg = [];
    imgNumInDiv =[];
}