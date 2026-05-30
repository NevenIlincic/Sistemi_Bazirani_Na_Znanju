import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DirectionSection } from './direction-section';

describe('DirectionSection', () => {
  let component: DirectionSection;
  let fixture: ComponentFixture<DirectionSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DirectionSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DirectionSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
