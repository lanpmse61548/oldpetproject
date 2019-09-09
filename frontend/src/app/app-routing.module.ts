import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ListComponent }   from './Dishes/list.component';
import { AppComponent }   from './app.component';
import { DishDetailComponent }   from './Dishes/Detail/detail.component';
import { EagerComponent } from './eager.component';
import { DashBoardComponent }         from './dashboard.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { TourDetailComponent }         from './Tour/TourDetail/tourdetail.component';
import { TourScreenComponent }         from './Tour/TourScreen/tourscreen.component';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import { DialogOverviewExampleDialog} from './/Tour/TourScreen/tourscreen.component';
import { MatDialogModule } from '@angular/material';
const routes: Routes = [
  { path: '', redirectTo: '/app', pathMatch: 'full' },
  { path: 'list',  loadChildren: 'app/Dishes/list.module#ListModule', data: { title: 'u suck' } },
  { path: 'app',     component: DashBoardComponent },
  { path: 'detail/:id', component: DishDetailComponent },
  { path: 'detail', component: DishDetailComponent },
  { path: 'eager', component: EagerComponent },
  { path: 'lazy', loadChildren: 'app/lazy/lazy.module#LazyModule', data: { title: 'u suck' } },
  { path: 'tourdetail', component: TourDetailComponent },
  { path: 'tourdetail/:id', component: TourDetailComponent },
  { path: 'tourscreen/:id', component: TourScreenComponent },
];

@NgModule({
  imports: [ RouterModule.forRoot(routes), MatDialogModule,BrowserAnimationsModule ],
  declarations: [ DialogOverviewExampleDialog],
  entryComponents: [TourScreenComponent,DialogOverviewExampleDialog],
  exports: [ RouterModule ]
})
export class AppRoutingModule {}
