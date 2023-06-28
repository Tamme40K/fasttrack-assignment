import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { AppComponent } from './app.component';
import { HttpClientModule } from '@angular/common/http';
import { HolidaysListComponent } from './holidays-list/holidays-list.component';
import {RouterLink} from "@angular/router";
import {RouterModule} from "@angular/router";
import { AppRoutingModule } from './app-routing.module';
import { HolidayFormComponent } from './holiday-form/holiday-form.component';
import {HolidaysService} from "./holidays.service";
import { HolidayDeleteComponent } from './holiday-delete/holiday-delete.component';
import { LoginComponent } from './login/login.component';

@NgModule({
  declarations: [
    AppComponent,
    HolidaysListComponent,
    HolidayFormComponent,
    HolidayDeleteComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    RouterLink,
    RouterModule,
    AppRoutingModule
  ],
  providers: [HolidaysService],
  bootstrap: [AppComponent]
})
export class AppModule { }
