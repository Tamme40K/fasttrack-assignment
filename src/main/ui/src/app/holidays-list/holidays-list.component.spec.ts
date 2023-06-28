import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HolidaysListComponent } from './holidays-list.component';

describe('HolidaysListComponent', () => {
  let component: HolidaysListComponent;
  let fixture: ComponentFixture<HolidaysListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HolidaysListComponent]
    });
    fixture = TestBed.createComponent(HolidaysListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
