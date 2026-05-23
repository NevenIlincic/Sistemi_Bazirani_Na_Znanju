import { Component } from '@angular/core';
import { RecommendationService } from '../../service/recommendation-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-num-defenders-ahead-section',
  imports: [FormsModule],
  templateUrl: './num-defenders-ahead-section.html',
  styleUrl: './num-defenders-ahead-section.css',
})
export class NumDefendersAheadSection {
  defendersAhead: string = "YES";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    let areDefendersAhead: boolean = true;
    if (this.defendersAhead == "NO") {
      areDefendersAhead = false;
    }
    this.recommendationService.areDefendersAhead.next(areDefendersAhead)
    console.log(areDefendersAhead);
  }
}
