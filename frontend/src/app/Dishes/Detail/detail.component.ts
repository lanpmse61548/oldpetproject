
import {switchMap} from 'rxjs/operators';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';
import { Dishes } from '../dishes';
import { DishesService } from '../dishes.service';


import { DishesWrapper } from '../DTO/DishesWrapper';
import { PageElement } from '../DTO/PageElement';
import { AuthenticationService }            from '../../Service/authentication.service';
import { FileWrapper } from '../DTO/DishesWrapper';


@Component({
  selector: 'detail-root',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css'],
  providers: [DishesService,AuthenticationService]

})
export class DishDetailComponent implements OnInit {
 
 
  myName: string = 'Dinh';
  counter = 0;
  fileWrapperList =[];
  url=[];
  removedOldUrls =[];
  img="https://www.popsci.com/sites/popsci.com/files/images/2017/08/depositphotos_3979974_original.jpg";
  title = '';
  dish: Dishes;
  oldImgList =[];
  elementList = [];
  style:string;
  content:string;
  dishWrapper : DishesWrapper;
  constructor(private dishesService: DishesService,
    private route: ActivatedRoute, private location: Location) { }

   addNewElement():void{
    let e2 = new PageElement();
    e2.id = new Date().getTime() / 1000;
    e2.content = this.content;
    var arrayOfLines =this.content.split("\n")
    
   
    e2.style = this.style;
   
    e2.contentArr =arrayOfLines ;
    this.elementList.push(e2);

   }

   addImage():void{
    let e2 = new PageElement();
    e2.id = new Date().getTime() / 1000;
    e2.style = 'image';
   this.elementList.push(e2);
     
   }

   updateFileForPage(files: FileWrapper[],id:number){
     alert(id);
     for(var i = 0;i<this.elementList.length;i++){
      if(this.elementList[i].id ==id){
        this.elementList[i].fileWrapperList=files;
      }
     }
   }
   
  deleteOldFileFileForPage(fileurls:string[]){
  //  this.removedOldUrls = fileurls;
}


  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>

        this.dishesService.getDish(+params.get('id'))))
      .subscribe(dish => {
        
      //  alert(dish.id );
        if (typeof dish !== "undefined") {
          this.dish = dish;
        } else {
          this.dish = new Dishes();
        }

        

        this.dishWrapper = new DishesWrapper();
        this.oldImgList =this.dish.imagePaths;
        this.dish.content = "sadsaasdsa";
         
      //  let e1 = new PageElement();
      //  e1.style = 'bold'
      //  e1.content = 'header'
      //  this.elementList.push(e1);

      //  let e2 = new PageElement();
      //  e2.style = 'gfdg'
      //  e2.content = 'header fdfsdf'
      //  this.elementList.push(e2);

      //  for(let  i = 0;i< this.elementList.length;i++){
      //   let  tmp = this.elementList[i];
      //      if(tmp.style ==  'bold' ){
      //       let  textwrap = document.createElement("text");
      //       textwrap.style.fontWeight = "900";
      //       let  t = document.createTextNode(tmp.content);
      //       textwrap.appendChild(t); 
      //  //    (<HTMLInputElement>document.getElementById("textArea")).appendChild(textwrap);
      //      document.body.appendChild(textwrap);
      //      let  a = document.createElement("button");
      //      a.appendChild(document.createTextNode("but"));
      //      a.onclick =( function (num:number,arr:any) {
      //       return ()=>{
      //         tmp.content="aaaa";
      //         }
      //     })(i,tmp);

      //      document.body.appendChild(a);

      //      let  br = document.createElement("br");
      //      document.body.appendChild(br);
      //      }else{
      //       let textwrap2 = document.createElement("text");
      //       let  t = document.createTextNode(tmp.content);
      //       textwrap2.appendChild(t); 
      //       document.body.appendChild(textwrap2);
      //       let  b = document.createElement("button");
      //      b.appendChild(document.createTextNode("but"));
      //      b.onclick =( function (num:number) {
      //       return ()=>{
      //         alert(i);
      //       }
      //   })(i);
      //     document.body.appendChild(b);
      //   }

      //  }

      }
    );
  }
  save(): void {
    // this.heroService.update(this.hero)
    //   .then(() => this.goBack());
 //   window.alert(this.dish.id);
 this.dishWrapper.fileList=[];
for(var i = 0;i<this.fileWrapperList.length;i++){
  this.dishWrapper.fileList.push(this.fileWrapperList[i].file);
}

   if( this.dish.id == undefined){
    this.dish.id = new Date().getTime() / 1000;
   }
  this.dishWrapper.dishes = this.dish;
  this.dishWrapper.dishes.oldUrls= this.removedOldUrls;
  this.dishesService.createDish(this.dishWrapper).then(
    dish => this.dish = dish);
}


 
  goBack(): void {
    this.location.back();
  }

  deleteOldFile(fileurls:string[]){
      this.removedOldUrls = fileurls;
  }

  updateFile(files: FileWrapper[],name:string):void {
    this.fileWrapperList =files;
  
  }

  
}
