import { Component } from '@angular/core';
import { RecommendationService } from '../service/recommendation-service';

@Component({
  selector: 'app-nav-bar',
  imports: [],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {

  constructor(private recommendationService: RecommendationService){}

  minutes: number = 0;
  seconds: number = 0;

  askRecommendation(){
    this.recommendationService.sendIncidentInformation();
  }
}
