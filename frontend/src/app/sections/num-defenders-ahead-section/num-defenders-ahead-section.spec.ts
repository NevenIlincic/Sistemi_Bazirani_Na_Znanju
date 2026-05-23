import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NumDefendersAheadSection } from './num-defenders-ahead-section';

describe('NumDefendersAheadSection', () => {
  let component: NumDefendersAheadSection;
  let fixture: ComponentFixture<NumDefendersAheadSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [NumDefendersAheadSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NumDefendersAheadSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
