import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GoalScoredSection } from './goal-scored-section';

describe('GoalScoredSection', () => {
  let component: GoalScoredSection;
  let fixture: ComponentFixture<GoalScoredSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GoalScoredSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GoalScoredSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
