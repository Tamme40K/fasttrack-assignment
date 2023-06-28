import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Holiday } from "./holiday";

@Injectable({
  providedIn: 'root'
})
export class HolidaysService {

  private baseURL = `http://localhost:8080/holidays`
  employeeId: string

  constructor(private http: HttpClient) {
    this.employeeId = ""
  }

  getHolidaysByEmployee(): Observable<Holiday[]> {
    return this.http.get<Holiday[]>(`${this.baseURL}/overview?employeeId=` + this.employeeId)
  }

  scheduleHoliday(data: any): Observable<Holiday> {
    return this.http.post<Holiday>(`${this.baseURL}/schedule`, data)
  }

  cancelHoliday(data: string): Observable<Holiday> {
    return this.http.delete<Holiday>(`${this.baseURL}/cancel?holidayId=` + data)
  }

  setEmployeeId(id: string): void {
    this.employeeId = id
  }

}
