
import {switchMap} from 'rxjs/operators';

import { Component, OnInit,ElementRef,AfterContentInit,ViewChild,Inject  } from '@angular/core';
import { Headers, Http, RequestOptions } from '@angular/http';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { HtmlPageDto } from '../DTO/HtmlPageDto';
import { TourService } from '../Service/tour.service';
import { AuthenticationService } from '../../Service/authentication.service';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA,MatDialogConfig} from '@angular/material';
@Component({
    selector: 'tour-screen',
    templateUrl: './tourscreen.component.html',
    styleUrls: ['./tourscreen.component.css'],
    providers: [TourService, AuthenticationService]
  
  })

  export class TourScreenComponent implements OnInit  {
    constructor(public dialog: MatDialog,private authenticationService: AuthenticationService,private route: ActivatedRoute,private toursService: TourService,private elementRef:ElementRef,private http: Http) { }
    node: string;
    slideIndex = 1;
    @ViewChild('screen') el:ElementRef;
    animal: string;
    name: string;
   
    
    ngOnInit():void{
      
      this.route.paramMap.pipe(
      switchMap((params: ParamMap) =>{
       
        return  this.toursService.getTour(+params.get('id'))
       
       }))
      .subscribe(data => { 
    
      this.elementRef.nativeElement.querySelector("#screen").innerHTML = data.pageContent;
  
      for(var i = 0;i<data.divImg.length;i++){

        (function(context:any,num:number) {
         for(var j = 0;j<data.divImg[i].images.length;j++){ 
         
        // let containerDiv = document.createElement("div"); 
         let img= document.createElement("img");
         img.setAttribute("src",data.divImg[i].images[j] );
        //  img.setAttribute("width",  "300px");
        //  img.setAttribute("height",  "300px");
         img.setAttribute("class", "slideImg slideNum"+i);
         img.setAttribute("id", "slide"+i+"img"+j);
        //  containerDiv.setAttribute("class","containerWrap");
        //  containerDiv.appendChild(img);
         context.el.nativeElement.querySelector("#"+data.divImg[i].divID).appendChild(img);
           
         }
         let slideLink = document.createElement("a"); 
         let linkText = document.createTextNode("<");
         slideLink.setAttribute("class","prev")
         slideLink.appendChild(linkText);
         slideLink.addEventListener("click",function(this){
          context.plusSlides(num,-1);
       

         } );


         let slideLink2 = document.createElement("a"); 
         let linkText2 = document.createTextNode(">");
         slideLink2.setAttribute("class","next")
         slideLink2.appendChild(linkText2);
         slideLink2.addEventListener("click",function(this){
          context.plusSlides(num,1);
        

         } );
        context.el.nativeElement.querySelector("#"+data.divImg[i].divID).appendChild(slideLink);
         context.el.nativeElement.querySelector("#"+data.divImg[i].divID).appendChild(slideLink2);
        })(this,i);
        this.showSlides(i,this.slideIndex);
      }

     
     // this.el.nativeElement.querySelector("#imgDiv0").innerHTML = JSON.stringify(data.divImg[0].images.length);
    })
    
    }

   



   plusSlides(e,n) {
   //  alert(e+" "+n);
        this.showSlides(e,this.slideIndex += n);
      }
      
     currentSlide(e,n) {
        this.showSlides(e,this.slideIndex = n);
      }
      
        showSlides(e,n) {
        var i;
        var slides = this.elementRef.nativeElement.querySelectorAll(".slideNum"+e);
    //    var dots = document.getElementsByClassName("dot");
        if (n > slides.length) {this.slideIndex = 1}    
        if (n < 1) {this.slideIndex = slides.length}
        for (i = 0; i < slides.length; i++) {
           slides[i].style.display = "none";  
        }
        // for (i = 0; i < dots.length; i++) {
        //     dots[i].className = dots[i].className.replace(" active", "");
        // }
     slides[this.slideIndex-1].style.display = "block";  
     //   dots[slideIndex-1].className += " active";
      }


      openDialog(): void {

        const dialogConfig = new MatDialogConfig();

        dialogConfig.disableClose = true;
        dialogConfig.autoFocus = true;
        dialogConfig.position = {
          'top': '0',
          left: '0'
      }
        let dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
         
          width: '500px',
          height:'500px',
          data: { name: "dasdsa", animal: "this.animal" }
        });
    
        dialogRef.afterClosed().subscribe(result => {
          console.log('The dialog was closed');
         alert (result);
        });
      }

  }


  @Component({
    selector: 'dialogex',
    templateUrl: 'dialogex.html',
  })
  export class DialogOverviewExampleDialog {
  
    constructor(
      public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
      @Inject(MAT_DIALOG_DATA) public data: any) { }
  
    onNoClick(): void {
      this.dialogRef.close();
    }
  
  }