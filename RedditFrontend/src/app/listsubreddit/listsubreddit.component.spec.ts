import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListsubredditComponent } from './listsubreddit.component';

describe('ListsubredditComponent', () => {
  let component: ListsubredditComponent;
  let fixture: ComponentFixture<ListsubredditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListsubredditComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListsubredditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
