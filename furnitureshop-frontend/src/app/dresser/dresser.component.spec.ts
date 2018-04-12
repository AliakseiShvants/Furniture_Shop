import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DresserComponent } from './dresser.component';

describe('DresserComponent', () => {
  let component: DresserComponent;
  let fixture: ComponentFixture<DresserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DresserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DresserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
