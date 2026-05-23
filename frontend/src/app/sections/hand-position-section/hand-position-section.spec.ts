import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HandPositionSection } from './hand-position-section';

describe('HandPositionSection', () => {
  let component: HandPositionSection;
  let fixture: ComponentFixture<HandPositionSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HandPositionSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HandPositionSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
