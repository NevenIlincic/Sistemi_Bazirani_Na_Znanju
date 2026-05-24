import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { RecommendationDTO } from '../../dto/RecommendationDTO';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-recommendations-section',
  imports: [],
  templateUrl: './recommendations-section.html',
  styleUrl: './recommendations-section.css',
})
export class RecommendationsSection implements OnInit {
  newRecommendations: RecommendationDTO[] = []
  
  constructor(private recommendationService: RecommendationService, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    this.recommendationService.watchRecommendations$.subscribe({
      next: (recs: RecommendationDTO[]) => {
        this.newRecommendations = recs;
        this.cdr.detectChanges();
      }
    });
  }
}
