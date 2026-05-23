import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BallControlSection } from './ball-control-section';

describe('BallControlSection', () => {
  let component: BallControlSection;
  let fixture: ComponentFixture<BallControlSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BallControlSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BallControlSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
