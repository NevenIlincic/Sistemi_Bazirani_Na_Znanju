import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IntensitySection } from './intensity-section';

describe('IntensitySection', () => {
  let component: IntensitySection;
  let fixture: ComponentFixture<IntensitySection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IntensitySection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IntensitySection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
