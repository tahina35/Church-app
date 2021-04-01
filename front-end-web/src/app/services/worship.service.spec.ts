import { TestBed } from '@angular/core/testing';

import { WorshipService } from './worship.service';

describe('WorshipService', () => {
  let service: WorshipService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorshipService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
