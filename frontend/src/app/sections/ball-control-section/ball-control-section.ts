import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-ball-control-section',
  imports: [FormsModule],
  templateUrl: './ball-control-section.html',
  styleUrl: './ball-control-section.css',
})
export class BallControlSection implements OnInit{
  attackerBallControl: string = "YES";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchBallControl$.subscribe({
      next: (value: boolean) => {
        if (value){
          this.attackerBallControl = "YES";
        }else{
          this.attackerBallControl = "NO";
        }
      }
    });
  }

  onOptionChange() {
    let hasControll: boolean = true;
    if (this.attackerBallControl == "NO"){
      hasControll = false;
    }
    this.recommendationService.ballControl.next(hasControll)
    console.log(hasControll);
  }
}
