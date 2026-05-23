import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-tackle-control-section',
  imports: [FormsModule],
  templateUrl: './tackle-control-section.html',
  styleUrl: './tackle-control-section.css',
})
export class TackleControlSection {
  tackleControl: string = "CONTROLLED";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    this.recommendationService.tackleControl.next(this.tackleControl);
    console.log(this.tackleControl);
  }
}
