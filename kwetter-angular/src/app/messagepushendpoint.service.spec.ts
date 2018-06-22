import { TestBed, inject } from '@angular/core/testing';

import { MessagepushendpointService } from './messagepushendpoint.service';

describe('MessagepushendpointService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [MessagepushendpointService]
    });
  });

  it('should be created', inject([MessagepushendpointService], (service: MessagepushendpointService) => {
    expect(service).toBeTruthy();
  }));
});
