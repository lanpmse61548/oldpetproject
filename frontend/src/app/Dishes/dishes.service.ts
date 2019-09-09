import { Injectable }    from '@angular/core';
import { Headers, Http } from '@angular/http';



import { Observable } from "rxjs";


import { Dishes } from './dishes';

import { RequestOptions } from '@angular/http';
import { DishesWrapper } from './DTO/DishesWrapper';
import { AuthenticationService }            from '../Service/authentication.service';
@Injectable()
export class DishesService {
public body = {id:10,name:'fdsfd'};
 // private headers = new Headers({'Content-Type': 'application/json'});
  private dishesUrl = 'http://localhost:8080/petproject/dish';  // URL to web api

  private createUrl = 'http://localhost:8080/petproject/createdish'; 
  private createUrl2 = 'http://localhost:8080/petproject/createdish2'; 

  private deleteUrl = 'http://localhost:8080/petproject/deletedish'; 
  private headers = new Headers({
   // 'Content-Type': 'application/json',
    'Authorization': this.authenticationService.getToken()
    });
  constructor(private http: Http,private authenticationService: AuthenticationService) { }
  
  
  getDishes(): Promise<Dishes[]> {
    
 console.log("ath "+this.authenticationService.getToken())
    
  //  return Promise.resolve(DISHES);
  let headers = new Headers();
  headers.append('Accept', 'application/json');
  headers.append("Authorization",this.authenticationService.getToken());
  console.log("shit"+headers.get('Authorization'));
  let options = new RequestOptions({ headers: headers });
  let body: string = JSON.stringify({username: 'username', password: 'password'})
  // this.http
  // .get(this.dishesUrl,options)
  // .map(res => console.log(res)).subscribe();


  return this.http.post(this.dishesUrl,body,options)
  .toPromise()
  .then(
    
    response => 
   // window.alert(JSON.stringify(response.json()));

    response.json() as Dishes[]
    
  )
  .catch(this.handleError);          
  }
 
    private handleError(error: any): Promise<any> {
      console.error('An error occurred', error); // for demo purposes only
      return Promise.reject(error.message || error);
    }

    getDish(id: number): Promise<Dishes> {
      return this.getDishes()
      .then(dishes => dishes.find(dish => dish.id === id));
                  
      }

      createDish( data: DishesWrapper): Promise<Dishes> {
       
        // this.http.post(
        //   this.createUrl,
        //   JSON.stringify(data),
        //   {headers: this.headers}
        // ).subscribe();

        let formData:FormData = new FormData();
      //  window.alert(data.fileList.length);
        
        let fileCount: number = data.fileList.length
        if (fileCount > 0) { // a file was selected
          for (let i = 0; i < fileCount; i++) {
           
              data.fileList[i].num =1321;
              formData.append('uploadFile', data.fileList[i]);
          }
        }

        // for(var i = 0;i<5;i++){
        //   data.dishes.somefile.push("");

        // }

        formData.append('info', new Blob([JSON.stringify(data.dishes)],
        {
            type: "application/json"
        }));
        let headers = new Headers();
        /** No need to include Content-Type in Angular 4 */
        headers.append('enctype', 'multipart/form-data');
        headers.append('Accept', 'application/json');
        let options = new RequestOptions({ headers: headers });
        return this.http.post(this.createUrl2, formData, options).toPromise()
        .then(
          
          response => 
         // window.alert(JSON.stringify(response.json()));
      
          response.json() as Dishes
          
        )
        .catch(this.handleError);          
        
            
      }

      deleteDish( data: any): void {
        
        this.http.post(
          this.deleteUrl,
          JSON.stringify(data),
          {headers: this.headers}
        ).subscribe();
      }


      fileChange(file : File) {
        
              let formData:FormData = new FormData();
              formData.append('uploadFile', file, file.name);
              let headers = new Headers();
              /** No need to include Content-Type in Angular 4 */
              headers.append('enctype', 'multipart/form-data');
              headers.append('Accept', 'application/json');
              let options = new RequestOptions({ headers: headers });
              this.http.post(this.createUrl2, formData, options).subscribe(
                      data => console.log('success'),
                      error => console.log(error)
                  )
          
      }
/*

 getDishes(): Promise<Dishes[]> {
  
    return this.http.get(this.heroesUrl)
               .toPromise()
               .then(response => response.json().data as Hero[])
               .catch(this.handleError);
              
  }
  getHero(id: number): Promise<Hero> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http.get(url)
      .toPromise()
      .then(response => response.json().data as Hero)
      .catch(this.handleError);
  }

  delete(id: number): Promise<void> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http.delete(url, {headers: this.headers})
      .toPromise()
      .then(() => null)
      .catch(this.handleError);
  }

  create(name: string): Promise<Hero> {
    return this.http
      .post(this.heroesUrl, JSON.stringify({name: name}), {headers: this.headers})
      .toPromise()
      .then(res => res.json().data as Hero)
      .catch(this.handleError);
  }

  update(hero: Hero): Promise<Hero> {
    const url = `${this.heroesUrl}/${hero.id}`;
    return this.http
      .put(url, JSON.stringify(hero), {headers: this.headers})
      .toPromise()
      .then(() => hero)
      .catch(this.handleError);
  }

  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }
  */
}

