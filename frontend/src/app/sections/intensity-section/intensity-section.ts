import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-intensity-section',
  imports: [FormsModule],
  templateUrl: './intensity-section.html',
  styleUrl: './intensity-section.css',
})
export class IntensitySection {
  intensity: string = "LOW";

  constructor(private recommendationService: RecommendationService){}

  onOptionChange(){
    this.recommendationService.intensity.next(this.intensity);
  }
}
