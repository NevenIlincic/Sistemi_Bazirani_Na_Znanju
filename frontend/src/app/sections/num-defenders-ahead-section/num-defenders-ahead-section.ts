import { Component, OnInit } from '@angular/core';
import { RecommendationService } from '../../service/recommendation-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-num-defenders-ahead-section',
  imports: [FormsModule],
  templateUrl: './num-defenders-ahead-section.html',
  styleUrl: './num-defenders-ahead-section.css',
})
export class NumDefendersAheadSection implements OnInit{
  defendersAhead: string = "YES";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchAreDefendersAhead$.subscribe({
      next: (value: boolean) => {
        if (value){
          this.defendersAhead = "YES";
        }else{
          this.defendersAhead = "NO";
        }
      }
    });
  }

  onOptionChange() {
    let areDefendersAhead: boolean = true;
    if (this.defendersAhead == "NO") {
      areDefendersAhead = false;
    }
    this.recommendationService.areDefendersAhead.next(areDefendersAhead);
    console.log(areDefendersAhead);
  }
}
