import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SundayServiceComponent } from './sunday-service.component';

describe('SundayServiceComponent', () => {
  let component: SundayServiceComponent;
  let fixture: ComponentFixture<SundayServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SundayServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SundayServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
