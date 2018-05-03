import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GoodPriceComponent } from './good-price.component';

describe('GoodPriceComponent', () => {
  let component: GoodPriceComponent;
  let fixture: ComponentFixture<GoodPriceComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GoodPriceComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GoodPriceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
