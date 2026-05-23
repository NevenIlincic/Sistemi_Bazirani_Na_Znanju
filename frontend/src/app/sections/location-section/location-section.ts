import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-location-section',
  imports: [FormsModule],
  templateUrl: './location-section.html',
  styleUrl: './location-section.css',
})
export class LocationSection {
  location: string = "OUTSIDE_PENALTY_AREA";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    this.recommendationService.location.next(this.location);
    console.log(this.location);
  }
}
