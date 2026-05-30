import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ContactPointSection } from './contact-point-section';

describe('ContactPointSection', () => {
  let component: ContactPointSection;
  let fixture: ComponentFixture<ContactPointSection>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ContactPointSection]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ContactPointSection);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
