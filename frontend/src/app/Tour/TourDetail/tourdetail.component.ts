
import {switchMap} from 'rxjs/operators';
import { Component, OnInit, ElementRef, AfterContentInit, ViewChild } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { Location } from '@angular/common';
import { PageElement } from '../DTO/PageElement';
import { FileWrapper } from '../DTO/PageElement';
import { CreatePageDto } from '../DTO/PageElement';
import { TourService } from '../Service/tour.service';
import { Headers, Http, RequestOptions } from '@angular/http';
import { AuthenticationService } from '../../Service/authentication.service';
import { ImgDivDto } from '../DTO/ImgDivDto';

@Component({
  selector: 'tour-detail',
  templateUrl: './tourdetail.component.html',
  styleUrls: ['./tourdetail.component.css'],
  providers: [TourService, AuthenticationService]

})

export class TourDetailComponent implements OnInit {
  private createUrl2 = 'http://localhost:8080/petproject/createtour';
  currentDiv: string;
  fileList = [];
  elementList = [];
  style: string;
  content: string;
  slideIndex = 1;
  currentID: number;
  deletedUrl = [];
  deletedDiv = [];

  constructor(private route: ActivatedRoute, private toursService: TourService, private elementRef: ElementRef, private http: Http, private authenticationService: AuthenticationService) {

  }
  node: string;
  @ViewChild('content') el: ElementRef;
  id: number;
  private sub: any;

  ngOnInit(): void {

    //    this.authenticationService.login('admin','111');
    console.log("tok " + this.authenticationService.getToken());
    //this.authenticationService.logout();

    this.route.paramMap.pipe(
      switchMap((params: ParamMap) => {
        if (+params.get('id') > 0) {
          return this.toursService.getTour(+params.get('id'))
        } else {
          return this.toursService.getNewTour();
        }
      }))
      .subscribe(data => {

        this.elementRef.nativeElement.querySelector("#content").innerHTML = data.pageContent;
        this.currentID = data.id;
        for (var i = 0; i < data.divImg.length; i++) {

          (function (context: any, num: number) {
            for (var j = 0; j < data.divImg[i].images.length; j++) {

              let img = document.createElement("img");
              img.setAttribute("src", data.divImg[i].images[j]);

              img.setAttribute("class", "gg");

              var imgWrap = document.createElement("div");

              var imgWrapHeader = document.createElement("div");
              imgWrapHeader.setAttribute("class", "imgWrapHeader");
              var delLink = document.createElement("a");
              delLink.text = "X";
              delLink.setAttribute("class", "delLink");

              imgWrapHeader.appendChild(delLink);


              imgWrap.setAttribute("class", "imgWrap");
              imgWrap.appendChild(imgWrapHeader);
              imgWrap.appendChild(img);
              var parentNode = context.el.nativeElement.querySelector("#" + data.divImg[i].divID);
              var firstChild = parentNode.firstChild;
              if (firstChild != null) {
                firstChild.setAttribute("contenteditable", "false");
              }
              parentNode.insertBefore(imgWrap, firstChild);

              delLink.addEventListener("click", function () {
                // alert(img.getAttribute("src"))
                parentNode.removeChild(img.parentElement);
                context.deletedUrl.push(img.getAttribute("src"));
              });

            }

            var btn = document.createElement("button");
            btn.setAttribute("id", "btnID" + i);
            btn.setAttribute("class", "imgbtn");
            btn.setAttribute("contenteditable", "false");
            btn.addEventListener('click', context.imgBtnOnClick.bind(context));
            context.el.nativeElement.querySelector("#" + data.divImg[i].divID).appendChild(btn);


            var btnDel = document.createElement("button");
            btnDel.setAttribute("id", "btnID" + i);
            btnDel.setAttribute("class", "imgbtn");
            btnDel.setAttribute("contenteditable", "false");
            btnDel.addEventListener('click', context.imgDivDel.bind(context));
            context.el.nativeElement.querySelector("#" + data.divImg[i].divID).appendChild(btnDel);

          })(this, i);
        }
      }
      );

    //parse only plain text when copy parse
    this.elementRef.nativeElement.querySelector("#content")
      .addEventListener("paste", function (e: ClipboardEvent) {
        e.preventDefault();
        var text = e.clipboardData.getData("text/plain");
        document.execCommand("insertHTML", false, text);
      });

  }



  bold() {
    document.execCommand('bold');
  }


  addImg() {
    //https://jsfiddle.net/Xeoncross/4tUDk/
    //get the  numer of div to set div id
    var sel, range;
    // IE9 and non-IE
    sel = window.getSelection();
    if (sel.getRangeAt && sel.rangeCount) {
      range = sel.getRangeAt(0);
      range.deleteContents();
    }
// get last node to get id
    var nodes = this.el.nativeElement.querySelectorAll(".imgContainerDiv");
    var last = nodes[nodes.length - 1];
    var countDiv = 0;
    if (last !== undefined) {
      countDiv = parseInt(last.id.replace('imgDiv', "")) + 1;
    }
    //add new imgDiv
    var a = document.createElement("div");
    a.setAttribute("id", "imgDiv" + countDiv);
    a.setAttribute("class", "imgContainerDiv");
    a.setAttribute("contenteditable", "false");
    //  this.el.nativeElement.appendChild(a);
    if (sel.getRangeAt && sel.rangeCount) {
      range.insertNode(a);
      if (a) {
        range = range.cloneRange();
        range.setStartAfter(a);
        range.collapse(true);
        sel.removeAllRanges();
        sel.addRange(range);
    }
    } else {
      this.el.nativeElement.appendChild(a);
    }
    //add new button to add img
    var btn = document.createElement("button");
    btn.setAttribute("id", "btnID" + countDiv);
    btn.setAttribute("class", "imgbtn");
    btn.setAttribute("contenteditable", "false");

    this.el.nativeElement.querySelector('#imgDiv' + countDiv).appendChild(btn);

    btn.addEventListener('click', this.imgBtnOnClick.bind(this));
    var breakLine = document.createElement('br');

    this.el.nativeElement.appendChild(breakLine);

    var btnDel = document.createElement("button");
    btnDel.setAttribute("id", "btnIDdel" + countDiv);
    btnDel.setAttribute("class", "imgbtn");
    btnDel.setAttribute("contenteditable", "false");
    btnDel.addEventListener('click', this.imgDivDel.bind(this));
    this.el.nativeElement.querySelector('#imgDiv' + countDiv).appendChild(btnDel);


    btn.click()

  }




  imgBtnOnClick(event) {

//    this.currentDiv = this.el.nativeElement.querySelector("#" + event.target.id).parentNode.id;
    document.getElementById('ful2').click();

  }

  imgDivDel(event) {
    // alert(this.el.nativeElement.querySelector("#"+event.target.id).parentNode.id);
    var divID = this.el.nativeElement.querySelector("#" + event.target.id).parentNode
    //  console.log(JSON.stringify(this.elementList));

    // var found = this.elementList.find(function(element) {
    //   alert(element.url);
    //   return element.divImg =="imgDiv2";
    // });

    var found = this.elementList.find(x => {
      alert(divID.id);
      if (x !== undefined) {
        alert(divID.id);
        return x['id'] == divID.id

      }
      return null;
    });
    if (found != undefined) {
      console.log("fff " + found.id);
      console.log("found " + this.elementList.indexOf(found, 0));
      console.log("legth " + this.elementList.length);
      this.elementList.splice(this.elementList[this.elementList.indexOf(found, 0)], -1);
    }



    // console.log(JSON.stringify(this.elementList));
    this.deletedDiv.push(divID.id);
    divID.parentNode.removeChild(divID);
    console.log(this.deletedDiv)
  }


  fileChange(event) {

    //get current image div that we are working with
    var tmpDiv = null;
    for (var i = 0; i < this.elementList.length; i++) {
      if (this.elementList[i].id == this.currentDiv) {
        tmpDiv = this.elementList[i];
        break;
      }
    }

    // if div = null,it mean we create new div
    if (tmpDiv == null) {
      tmpDiv = new PageElement();
      tmpDiv.id = this.currentDiv;
      this.elementList.push(tmpDiv);
    }


    //get all inputed file
    let fileList: FileList = event.target.files;


    //check number of file
    if (event.target.files.length > 100) {
      alert("boo you sucks");
      (<HTMLInputElement>document.getElementById("ful2")).value = "";

      return;
    }


    //for each input file, create an img 
    for (var i = 0; i <= event.target.files.length - 1; i++) {
      var reader = new FileReader();
      reader.onload =
        (function (file: File, fileList: any, el: ElementRef, divID: string) {
          return (event: any) => {

            //add file to list for later upload to server
            var tmp = new FileWrapper();

            tmp.file = file;
            tmp.url = event.target.result;
            tmp.num = new Date().getTime() / 1000;;
            tmp.divID = divID;

            fileList.push(tmp);

            //add chosen img
            var imgWrap = document.createElement("div");
            var img = document.createElement("img");
            img.setAttribute("src", tmp.url.toString());
            img.setAttribute("class", "gg added");
            var imgWrapHeader = document.createElement("div");
            imgWrapHeader.setAttribute("class", "imgWrapHeader");
            var delLink = document.createElement("a");
            delLink.text = "X";
            delLink.setAttribute("class", "delLink");
            imgWrapHeader.appendChild(delLink);

            //craeate remove X link
            delLink.addEventListener("click", function () {
              var index = fileList.indexOf(tmp);
              if (index > -1) {
                fileList.splice(index, 1);
                el.nativeElement.querySelector("#" + divID).removeChild(imgWrap);
              }
            });

            imgWrap.setAttribute("class", "imgWrap");
            imgWrap.appendChild(imgWrapHeader);
            imgWrap.appendChild(img);
            var parentNode = el.nativeElement.querySelector("#" + divID);
            var firstChild = parentNode.firstChild;
            parentNode.insertBefore(imgWrap, firstChild);

          }
        })(event.target.files[i], tmpDiv.fileList, this.el, tmpDiv.id);

      reader.readAsDataURL(event.target.files[i])
    }
  }

  //create/update img
  create() {
    this.elementList;
    var newPage = new CreatePageDto;
    let divcount = 0;
    //clone a node from content to use
    var clone = this.elementRef.nativeElement.querySelector("#content").cloneNode(true);
    // while(clone.querySelector("#imgDiv" +divcount) !==null){

    //  let newImgDivNum = new ImgDivDto();
    //  newImgDivNum.divID = "imgDiv" +divcount;
    //  newImgDivNum.imgNum = clone.querySelector("#imgDiv" +divcount).querySelectorAll(".added").length;

    //  newPage.imgNumInDiv.push(newImgDivNum);
    //  var tmpNode= clone.querySelector("#imgDiv" +divcount);
    //  while (tmpNode.hasChildNodes()) {
    //   tmpNode.removeChild(tmpNode.lastChild);
    // }
    //  divcount++;
    // }

    var imgDivNodes = clone.getElementsByClassName("imgContainerDiv");

    for (var i = 0; i < imgDivNodes.length; i++) {
      let newImgDivNum = new ImgDivDto();
      newImgDivNum.divID = imgDivNodes[i].id;
      newImgDivNum.imgNum = imgDivNodes[i].querySelectorAll(".added").length;
      newPage.imgNumInDiv.push(newImgDivNum);
      while (imgDivNodes[i].hasChildNodes()) {
        imgDivNodes[i].removeChild(imgDivNodes[i].lastChild);
      }
    }

    for (var i = 0; i < this.elementList.length; i++) {
      for (var j = 0; j < this.elementList[i].fileList.length; j++) {
        this.fileList.push(this.elementList[i].fileList[j].file);
      }
    }




    newPage.content = clone.innerHTML;
    let formData: FormData = new FormData();

    let fileCount: number = this.fileList.length
    if (fileCount > 0) { // a file was selected
      for (let i = 0; i < fileCount; i++) {

        //this.fileList[i].num =1321;
        formData.append('uploadFile', this.fileList[i]);
      }
    }

    newPage.id = this.currentID;
    newPage.removedImg = this.deletedUrl;
    newPage.removedDiv = this.deletedDiv;

    formData.append('info', new Blob([JSON.stringify(newPage)],
      {
        type: "application/json"
      }));

      let headers = new Headers();
      headers.append('Accept', 'application/json');
      headers.append("Authorization",this.authenticationService.getToken());
      console.log("shit"+headers.get('Authorization'));
      let options = new RequestOptions({ headers: headers });

    return this.http.post(this.createUrl2, formData, this.createOption()).toPromise()
      .then(response => {
        this.handleResponse(response);
      }
      )
      .catch(this.handleError);
  }


  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  private createOption(): RequestOptions {
    let headers = new Headers();


    headers.append('Accept', 'application/json');
    headers.append("Authorization",this.authenticationService.getToken());
    console.log("shit"+headers.get('Authorization'));
    let options = new RequestOptions({ headers: headers });
    // console.log("shit"+headers.get('Authorization'));

    // let body: string = JSON.stringify({username: 'admin', password: 'password'})

    return  options;
  }

  private handleResponse(response: any) {
    if (response.status == 200) {
      alert("success")
    } else {
      alert("fail")
    }
  }

}