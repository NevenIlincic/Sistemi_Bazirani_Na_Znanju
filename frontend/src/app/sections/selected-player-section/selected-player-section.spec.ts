import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SelectedPlayerSection } from './selected-player-section';

describe('SelectedPlayerSection', () => {
  let component: SelectedPlayerSection;
  let fixture: ComponentFixture<SelectedPlayerSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SelectedPlayerSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SelectedPlayerSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
