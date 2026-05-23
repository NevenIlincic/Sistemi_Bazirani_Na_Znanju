import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-direction-section',
  imports: [FormsModule],
  templateUrl: './direction-section.html',
  styleUrl: './direction-section.css',
})
export class DirectionSection {
  movingDirection: string = "TOWARDS_GOAL";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    this.recommendationService.movingDirection.next(this.movingDirection);
    console.log(this.movingDirection);
  }
}
