import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecommendationsSection } from './recommendations-section';

describe('RecommendationsSection', () => {
  let component: RecommendationsSection;
  let fixture: ComponentFixture<RecommendationsSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecommendationsSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecommendationsSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
