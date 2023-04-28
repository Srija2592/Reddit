import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewsubredditComponent } from './viewsubreddit.component';

describe('ViewsubredditComponent', () => {
  let component: ViewsubredditComponent;
  let fixture: ComponentFixture<ViewsubredditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ViewsubredditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ViewsubredditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
