import { Component, OnInit } from '@angular/core';
import { Router}            from '@angular/router';
import { Dishes } from './dishes';

import { DishesService }            from './dishes.service';
import { AuthenticationService }            from '../Service/authentication.service';
import { Observable ,  Subject }        from 'rxjs';

// Observable class extensions


// Observable operators




@Component({
  selector: 'list-root',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css'],
  providers: [DishesService,AuthenticationService]
  
})
export class ListComponent implements OnInit {
  title = '';
  dishes: Dishes[];
  selectedDish: Dishes;
  username:string;
  password:string;
 constructor( 
    private router: Router,private dishesService: DishesService,private authService:AuthenticationService) { }
  
    getDishes(): void {
      this.dishesService.getDishes().then(
        dishes => this.dishes = dishes);  
     
    }
   
    ngOnInit(): void {
     // this.getDishes();
    }
   
    goDetail(dish: Dishes): void {
      
      this.selectedDish = dish;
      window.alert(dish.id);
      let link = ['/detail', dish.id];
      this.router.navigate(link);
    }

    newDish(): void {
      let link = ['/detail'];
      this.router.navigate(link);
    }
   

    delete(dish: Dishes): void {
      
      this.selectedDish = dish;
      this.dishesService.deleteDish(this.selectedDish);
      this.getDishes();
    }

    search():void{
      this.getDishes();
    }

    logout():void{
     console.log("before"+ this.authService.getToken());
      this.authService.logout();
      console.log("after"+ this.authService.getToken());
    }


    login() {
     this.logout();
       this.authService.login(this.username,this.password).subscribe(result => {
        if (result === true) {
            // login successful
           
           this.router.navigate(['/tourdetail']);
        } else {
            // login failed
        //    this.error = 'Username or password is incorrect';
           // this.loading = false;
           alert("login failed, you suck")
        }
    }, error => {
      alert("login failed, you suck")
   //   this.loading = false;
   //   this.error = error;
    });

 
    }
}
