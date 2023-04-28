import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PosttileComponent } from './posttile.component';

describe('PosttileComponent', () => {
  let component: PosttileComponent;
  let fixture: ComponentFixture<PosttileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PosttileComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PosttileComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
