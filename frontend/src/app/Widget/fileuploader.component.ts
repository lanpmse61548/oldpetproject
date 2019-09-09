import { Component, OnInit, Input,Output,EventEmitter } from '@angular/core';
import { Location } from '@angular/common';

import { FileWrapper } from '../Dishes/DTO/DishesWrapper';


@Component({
  selector: 'file-uploader',
  templateUrl: './fileuploader.component.html',
  styleUrls: ['./fileuploader.component.css']


})

export class FileUploaderComponent implements OnInit{
  @Input() fatherName: string;
  @Input()elementId:number;
  @Input('oldImgList') imgList =[];
   imgViewList =[];
  @Output() onFileUpload : EventEmitter<FileWrapper[]> = new EventEmitter<FileWrapper[]>();
  @Output() onOldFileDelete : EventEmitter<string[]> = new EventEmitter<string[]>();

  isMulti = true;
  urls =[];
  oldFileUrls =[];
  counter = 0;

  ngOnInit(): void {
    if( this.imgList!= undefined)
    this.imgViewList = this.imgList.slice();;
  }

  abort():void{
   
    this.imgViewList = this.imgList.slice();
    this.urls =[];
    this.onFileUpload.emit(this.urls);
    this.oldFileUrls=[];
    this.onOldFileDelete.emit( this.oldFileUrls);
  }
  removeImg(num:Number){

    for(var i=0;i<=this.urls.length-1;i++){
      if(this.urls[i].num==num){
        this.urls.splice(i, 1);
      }
    }  
    this.onFileUpload.emit(this.urls);
    
 if(this.urls.length==0){
  (<HTMLInputElement>document.getElementById("ful2")).value ="";
 }

}

removeOldImg(img:string){
  this.oldFileUrls.push(img)
  this.onOldFileDelete.emit( this.oldFileUrls);
  for(var i =0;i<this.imgViewList.length;i++){
    if(this.imgViewList[i]==img){
      this.imgViewList.splice(i, 1);
    }
  }

}

  fileChange(event) {
    let fileList: FileList = event.target.files;
    if(event.target.files.length>100){
      alert("boo you sucks");
      (<HTMLInputElement>document.getElementById("ful2")).value ="";

      return;
    }

    for(var i=0;i<=event.target.files.length-1;i++){
      var reader = new FileReader();
    //  var file =event.target.files[i];
      reader.onload = 
      (function(file :File,num :Number,fileList:any,outPutFile:EventEmitter<FileWrapper[]>,elementId:number) {
     return (event:any) => {
       
        var tmp = new FileWrapper();
  
        tmp.file = file;
        tmp.url= event.target.result;
        tmp.num=num;
        tmp.elementid = elementId;
     //   tmp.num=this.counter
        fileList.push(tmp);
        outPutFile.emit(fileList);
       
        
      }
    })(event.target.files[i],this.counter,this.urls,this.onFileUpload,this.elementId);
    this.counter++;
     // alert(i+"  "+event.target.files[i]);
      reader.readAsDataURL(event.target.files[i])
    }


   // this.onFileUpload.emit(fileList);
  
    
  }
}
   