import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TackleControlSection } from './tackle-control-section';

describe('TackleControlSection', () => {
  let component: TackleControlSection;
  let fixture: ComponentFixture<TackleControlSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TackleControlSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(TackleControlSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
