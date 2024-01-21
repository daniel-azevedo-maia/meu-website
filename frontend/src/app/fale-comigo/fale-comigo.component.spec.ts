import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaleComigoComponent } from './fale-comigo.component';

describe('FaleComigoComponent', () => {
  let component: FaleComigoComponent;
  let fixture: ComponentFixture<FaleComigoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [FaleComigoComponent]
    });
    fixture = TestBed.createComponent(FaleComigoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
