import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FoulTypeSection } from './foul-type-section';

describe('FoulTypeSection', () => {
  let component: FoulTypeSection;
  let fixture: ComponentFixture<FoulTypeSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FoulTypeSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FoulTypeSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
