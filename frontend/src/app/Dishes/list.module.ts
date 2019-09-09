import { NgModule } from '@angular/core';
import { CommonModule }   from '@angular/common';
import { ListComponent }   from './list.component';
import { routing } from './list.routing';
import { FormsModule } from '@angular/forms';
@NgModule({
  imports: [ CommonModule,routing,FormsModule],
  declarations: [ListComponent]
})
export class ListModule {}