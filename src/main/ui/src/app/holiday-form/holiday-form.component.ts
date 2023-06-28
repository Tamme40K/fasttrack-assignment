import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Holiday } from "../holiday";
import { HolidaysService } from "../holidays.service";

@Component({
  selector: 'app-holiday-form',
  templateUrl: './holiday-form.component.html',
  styleUrls: ['./holiday-form.component.css']
})
export class HolidayFormComponent {

  holiday: Holiday;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private holidaysService: HolidaysService) {
    this.holiday = new Holiday();
  }

  onSubmit() {
    this.holidaysService.scheduleHoliday(this.holiday).subscribe(result => this.goToHolidaysList());
  }

  goToHolidaysList() {
    this.router.navigate(['/holidays']);
  }
}
