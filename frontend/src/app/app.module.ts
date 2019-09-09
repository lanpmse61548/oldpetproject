import { NgModule }      from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule }   from '@angular/forms';
import { HttpModule }    from '@angular/http';
import {HttpClientModule} from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';


import { EagerComponent } from './eager.component';
import { DashBoardComponent }         from './dashboard.component';
import { AppComponent }         from './app.component';
//import { ListComponent }   from './Dishes/list.component';
import { DishDetailComponent }   from './Dishes/Detail/detail.component';
import { FileUploaderComponent }   from './Widget/fileuploader.component';
//import { ListModule }   from './Dishes/list.module';

import { TourDetailComponent }         from './Tour/TourDetail/tourdetail.component';
import { TourScreenComponent }         from './Tour/TourScreen/tourscreen.component';
@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    HttpClientModule,
    AppRoutingModule,
   
 //   ListModule,
  ],
  declarations: [
    AppComponent,
    DashBoardComponent,
  //  ListComponent,
    DishDetailComponent,
    EagerComponent,
    FileUploaderComponent,
    TourDetailComponent,
    TourScreenComponent,
  ],

  bootstrap: [ AppComponent ]
})
export class AppModule { }
