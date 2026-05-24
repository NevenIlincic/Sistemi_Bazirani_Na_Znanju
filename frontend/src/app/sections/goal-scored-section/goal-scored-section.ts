import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-goal-scored-section',
  imports: [FormsModule],
  templateUrl: './goal-scored-section.html',
  styleUrl: './goal-scored-section.css',
})
export class GoalScoredSection implements OnInit{
  goalScored: string = "NO";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchGoalScored$.subscribe({
      next: (value: boolean) =>{
        if (value){
          this.goalScored = "YES";
        }else{
          this.goalScored = "NO";
        }
      }
    });
  }

  onOptionChange() {
    let isGoalScored: boolean = true;
    if (this.goalScored == "NO") {
      isGoalScored = false;
    }
    this.recommendationService.goalScored.next(isGoalScored)
    console.log(isGoalScored);
  }
}
