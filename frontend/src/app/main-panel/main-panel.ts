import { Component } from '@angular/core';
import { IntensitySection } from '../sections/intensity-section/intensity-section';
import { ContactPointSection } from '../sections/contact-point-section/contact-point-section';
import { FoulTypeSection } from '../sections/foul-type-section/foul-type-section';
import { TackleControlSection } from '../sections/tackle-control-section/tackle-control-section';
import { HandPositionSection } from '../sections/hand-position-section/hand-position-section';

@Component({
  selector: 'app-main-panel',
  imports: [IntensitySection, ContactPointSection, FoulTypeSection, TackleControlSection, HandPositionSection],
  templateUrl: './main-panel.html',
  styleUrl: './main-panel.css',
})
export class MainPanel {

}
