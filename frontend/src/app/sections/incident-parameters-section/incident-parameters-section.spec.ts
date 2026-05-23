import { ComponentFixture, TestBed } from '@angular/core/testing';

import { IncidentParametersSection } from './incident-parameters-section';

describe('IncidentParametersSection', () => {
  let component: IncidentParametersSection;
  let fixture: ComponentFixture<IncidentParametersSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [IncidentParametersSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(IncidentParametersSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
