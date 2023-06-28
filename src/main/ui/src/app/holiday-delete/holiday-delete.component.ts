import { Component } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HolidaysService} from "../holidays.service";

@Component({
  selector: 'app-holiday-delete',
  templateUrl: './holiday-delete.component.html',
  styleUrls: ['./holiday-delete.component.css']
})
export class HolidayDeleteComponent {

  holidayId: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private holidaysService: HolidaysService) {
    this.holidayId = ""
  }

  onSubmit() {
    this.holidaysService.cancelHoliday(this.holidayId).subscribe(() => this.goToHolidaysList());
  }

  private goToHolidaysList() {
    this.router.navigate(['/holidays']);
  }
}
