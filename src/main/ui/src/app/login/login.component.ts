import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HolidaysService} from "../holidays.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  employeeId: string

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private holidaysService: HolidaysService) {
    this.employeeId = this.holidaysService.employeeId
  }

  onSubmit() {
    this.holidaysService.setEmployeeId(this.employeeId)
    this.router.navigate(['/holidays']);
  }
}
