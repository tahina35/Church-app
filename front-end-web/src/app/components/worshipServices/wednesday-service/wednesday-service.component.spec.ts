import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WednesdayServiceComponent } from './wednesday-service.component';

describe('WednesdayServiceComponent', () => {
  let component: WednesdayServiceComponent;
  let fixture: ComponentFixture<WednesdayServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ WednesdayServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WednesdayServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
