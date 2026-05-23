import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-distance-section',
  imports: [FormsModule],
  templateUrl: './distance-section.html',
  styleUrl: './distance-section.css',
})
export class DistanceSection {
  distance: string = "CLOSE";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    this.recommendationService.distance.next(this.distance);
    console.log(this.distance);
  }
}
