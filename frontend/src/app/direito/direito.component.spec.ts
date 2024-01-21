import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DireitoComponent } from './direito.component';

describe('DireitoComponent', () => {
  let component: DireitoComponent;
  let fixture: ComponentFixture<DireitoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DireitoComponent]
    });
    fixture = TestBed.createComponent(DireitoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
