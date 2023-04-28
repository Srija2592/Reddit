import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubredditsidebarComponent } from './subredditsidebar.component';

describe('SubredditsidebarComponent', () => {
  let component: SubredditsidebarComponent;
  let fixture: ComponentFixture<SubredditsidebarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubredditsidebarComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SubredditsidebarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
