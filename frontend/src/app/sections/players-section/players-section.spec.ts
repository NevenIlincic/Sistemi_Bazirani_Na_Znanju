import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PlayersSection } from './players-section';

describe('PlayersSection', () => {
  let component: PlayersSection;
  let fixture: ComponentFixture<PlayersSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PlayersSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PlayersSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
