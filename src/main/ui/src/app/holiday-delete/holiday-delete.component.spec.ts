import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidayDeleteComponent } from './holiday-delete.component';

describe('HolidayDeleteComponent', () => {
  let component: HolidayDeleteComponent;
  let fixture: ComponentFixture<HolidayDeleteComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HolidayDeleteComponent]
    });
    fixture = TestBed.createComponent(HolidayDeleteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
