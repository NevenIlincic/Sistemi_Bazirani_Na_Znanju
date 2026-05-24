import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-tackle-control-section',
  imports: [FormsModule],
  templateUrl: './tackle-control-section.html',
  styleUrl: './tackle-control-section.css',
})
export class TackleControlSection implements OnInit {
  tackleControl: string | null = "CONTROLLED";

  constructor(private recommendationService: RecommendationService) { }

  ngOnInit(): void {
    this.recommendationService.watchTackleControl$.subscribe({
      next: (value: string | null) => {
        this.tackleControl = value;
      }
    });
  }

  onOptionChange() {
    this.recommendationService.tackleControl.next(this.tackleControl);
    console.log(this.tackleControl);
  }
}
