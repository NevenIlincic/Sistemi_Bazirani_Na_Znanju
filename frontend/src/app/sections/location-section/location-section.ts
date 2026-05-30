import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-location-section',
  imports: [FormsModule],
  templateUrl: './location-section.html',
  styleUrl: './location-section.css',
})
export class LocationSection implements OnInit {
  location: string = "OUTSIDE_PENALTY_AREA";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchLocation$.subscribe({
        next: (value: string) => {
          this.location = value;
        }
      });
  }

  onOptionChange() {
    this.recommendationService.location.next(this.location);
    console.log(this.location);
  }
}
