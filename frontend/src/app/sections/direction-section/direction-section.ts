import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-direction-section',
  imports: [FormsModule],
  templateUrl: './direction-section.html',
  styleUrl: './direction-section.css',
})
export class DirectionSection implements OnInit {
  movingDirection: string = "TOWARDS_GOAL";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchMovingDirection$.subscribe({
      next: (value: string) => {
        this.movingDirection = value;
      }
    });
  }

  onOptionChange() {
    this.recommendationService.movingDirection.next(this.movingDirection);
    console.log(this.movingDirection);
  }
}
