import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-hand-position-section',
  imports: [FormsModule],
  templateUrl: './hand-position-section.html',
  styleUrl: './hand-position-section.css',
})
export class HandPositionSection implements OnInit {
  handPosition: string | null = "NATURAL";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchHandPosition$.subscribe({
      next: (value: string | null) =>{
        this.handPosition = value;
      }
    });
  }

  onOptionChange() {
    this.recommendationService.handPosition.next(this.handPosition);
    console.log(this.handPosition);
  }
}
