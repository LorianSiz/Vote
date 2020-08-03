import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsVoteComponent } from './details-vote.component';

describe('DetailsVoteComponent', () => {
  let component: DetailsVoteComponent;
  let fixture: ComponentFixture<DetailsVoteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailsVoteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailsVoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
