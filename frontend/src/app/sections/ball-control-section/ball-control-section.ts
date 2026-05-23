import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-ball-control-section',
  imports: [FormsModule],
  templateUrl: './ball-control-section.html',
  styleUrl: './ball-control-section.css',
})
export class BallControlSection {
  attackerBallControl: string = "YES";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    let hasControll: boolean = true;
    if (this.attackerBallControl == "NO"){
      hasControll = false;
    }
    this.recommendationService.ballControl.next(hasControll)
    console.log(hasControll);
  }
}
