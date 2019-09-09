import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';
import { AuthenticationService }            from '../../Service/authentication.service';
import { RequestOptions } from '@angular/http';
import { HtmlPageDto } from '../DTO/HtmlPageDto';
import { Observable } from "rxjs";



@Injectable()
export class TourService {
    constructor(private http: Http,private authenticationService: AuthenticationService) { }

    getTour(id:Number) :Promise<HtmlPageDto>{

        let headers = new Headers();
  headers.append('Accept', 'application/json');
  headers.append("Authorization",this.authenticationService.getToken());
  console.log("shit"+headers.get('Authorization'));
  let options = new RequestOptions({ headers: headers });
  let body: string = JSON.stringify({username: 'admin', password: 'password'})
  
        return this.http.post("http://localhost:8080/petproject/getTour/" +id,body,options)
         .toPromise()
         .then(
          response => 
          response.json() as HtmlPageDto
          )
         .catch();          
         }
       
     getNewTour() :Promise<HtmlPageDto>{

        let headers = new Headers();
        headers.append('Accept', 'application/json');
        headers.append("Authorization",this.authenticationService.getToken());
        console.log("shit"+headers.get('Authorization'));
        let options = new RequestOptions({ headers: headers });
        let body: string = JSON.stringify({username: 'admin', password: 'password'})

          return this.http.post("http://localhost:8080/petproject/getNewTour/",body,options)
           .toPromise()
           .then(
             response => 
             response.json() as HtmlPageDto
             )
           .catch();          
       }
     

}