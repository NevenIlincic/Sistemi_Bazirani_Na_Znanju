import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-intensity-section',
  imports: [FormsModule],
  templateUrl: './intensity-section.html',
  styleUrl: './intensity-section.css',
})
export class IntensitySection implements OnInit{
  intensity: string | null = "LOW";

  constructor(private recommendationService: RecommendationService){}

  ngOnInit(): void {
    this.recommendationService.watchIntensity$.subscribe({
      next: (value: string | null) => {
        this.intensity = value;
      }
    });
  }

  onOptionChange(){
    this.recommendationService.intensity.next(this.intensity);
  }
}
