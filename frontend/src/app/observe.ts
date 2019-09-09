
import {throwError as observableThrowError,  Observable ,  Observer } from 'rxjs';
import { OnInit, Component } from '@angular/core';


export class Shit implements OnInit {
    observable: Observable<string>;
    observer: Observer<string>;
  
    ngOnInit() {
      this.observable = new Observable((observer: Observer<string>) => {
        this.observer = observer;
        
      });
      this.observable.subscribe(this.handleData, this.handleError , this.handleComplete);
      
        this.observer.next('12');
        this.observer.next('15');
        this.observer.complete();
        this.observer.next('16');
        this.observer.error("shit");
    }
     
  handleData(data) {
    console.log('Here are the usable data', data);
    // Insert Business logic here
  }

  handleComplete() {
    console.log('Complete');
  }

  handleError(error) {
    console.log('error:', error)
    return observableThrowError(error);
  }
  }