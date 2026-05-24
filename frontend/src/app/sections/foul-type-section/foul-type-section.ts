import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-foul-type-section',
  imports: [FormsModule],
  templateUrl: './foul-type-section.html',
  styleUrl: './foul-type-section.css',
})
export class FoulTypeSection implements OnInit {
  foulType: string | null = "NON_EXISTENT";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchFoulType$.subscribe({
      next: (value: string | null) => {
        this.foulType = value;
      }
    });
  }

  onOptionChange() {
    this.recommendationService.foulType.next(this.foulType);
    console.log(this.foulType);
  }
}
