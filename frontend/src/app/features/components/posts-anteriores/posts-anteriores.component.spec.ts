import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PostsAnterioresComponent } from './posts-anteriores.component';

describe('PostsAnterioresComponent', () => {
  let component: PostsAnterioresComponent;
  let fixture: ComponentFixture<PostsAnterioresComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PostsAnterioresComponent]
    });
    fixture = TestBed.createComponent(PostsAnterioresComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
