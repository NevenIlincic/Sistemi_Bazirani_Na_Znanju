import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistanceSection } from './distance-section';

describe('DistanceSection', () => {
  let component: DistanceSection;
  let fixture: ComponentFixture<DistanceSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DistanceSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DistanceSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
