import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HolidaysListComponent } from './holidays-list/holidays-list.component';
import { HolidayFormComponent } from './holiday-form/holiday-form.component';
import { HolidayDeleteComponent } from "./holiday-delete/holiday-delete.component";
import { LoginComponent } from "./login/login.component";

const routes: Routes = [
  { path: 'holidays', component: HolidaysListComponent },
  { path: 'add-holiday', component: HolidayFormComponent },
  { path: 'delete-holiday', component: HolidayDeleteComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
