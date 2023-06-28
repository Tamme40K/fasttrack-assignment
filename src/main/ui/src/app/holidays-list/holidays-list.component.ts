import {Component, OnInit} from '@angular/core';
import {Holiday} from "../holiday";
import {HolidaysService} from "../holidays.service";

@Component({
  selector: 'app-holidays-list',
  templateUrl: './holidays-list.component.html',
  styleUrls: ['./holidays-list.component.css']
})
export class HolidaysListComponent implements OnInit {

  holidays: Holiday[] = [];

  constructor(private holidaysService: HolidaysService) {  }

  ngOnInit() {
    this.holidaysService.getHolidaysByEmployee().subscribe(data => {
      this.holidays = data;
    });
  }
}
