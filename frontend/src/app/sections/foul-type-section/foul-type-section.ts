import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-foul-type-section',
  imports: [FormsModule],
  templateUrl: './foul-type-section.html',
  styleUrl: './foul-type-section.css',
})
export class FoulTypeSection {
  foulType: string = "NON_EXISTENT";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    this.recommendationService.foulType.next(this.foulType);
    console.log(this.foulType);
  }
}
