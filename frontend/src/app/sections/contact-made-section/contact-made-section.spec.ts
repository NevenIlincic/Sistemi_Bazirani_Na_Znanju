import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactMadeSection } from './contact-made-section';

describe('ContactMadeSection', () => {
  let component: ContactMadeSection;
  let fixture: ComponentFixture<ContactMadeSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactMadeSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactMadeSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
