import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MorningServiceComponent } from './morning-service.component';

describe('MorningServiceComponent', () => {
  let component: MorningServiceComponent;
  let fixture: ComponentFixture<MorningServiceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MorningServiceComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MorningServiceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
