import { ComponentFixture, TestBed } from '@angular/core/testing';

import { YouthServiceComponent } from './youth-service.component';

describe('YouthServiceComponent', () => {
  let component: YouthServiceComponent;
  let fixture: ComponentFixture<YouthServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ YouthServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(YouthServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
