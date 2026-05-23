import { Component } from '@angular/core';
import { RecommendationService } from '../../service/recommendation-service';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-contact-point-section',
  imports: [FormsModule],
  templateUrl: './contact-point-section.html',
  styleUrl: './contact-point-section.css',
})
export class ContactPointSection {
  contactPoint: string = "FOOT";

  constructor(private recommendationService: RecommendationService) { }

  onOptionChange() {
    this.recommendationService.contactPoint.next(this.contactPoint);
    console.log(this.contactPoint);
  }
}
