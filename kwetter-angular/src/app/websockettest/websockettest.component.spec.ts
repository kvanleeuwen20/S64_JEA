import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WebsockettestComponent } from './websockettest.component';

describe('WebsockettestComponent', () => {
  let component: WebsockettestComponent;
  let fixture: ComponentFixture<WebsockettestComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WebsockettestComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WebsockettestComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
