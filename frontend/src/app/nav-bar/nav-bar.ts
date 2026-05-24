import { Component } from '@angular/core';
import { RecommendationService } from '../service/recommendation-service';
import { PlayerService } from '../service/player-service';

@Component({
  selector: 'app-nav-bar',
  imports: [],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar {

  constructor(private recommendationService: RecommendationService, private playerService: PlayerService){}

  minutes: number = 0;
  seconds: number = 0;

  askRecommendation(){
    this.recommendationService.sendIncidentInformation();
  }

  insertPlayers(): void{
    this.playerService.insertPlayers().subscribe();
  }

  resetOptions(): void{
    this.recommendationService.resetOptions();
  }
}
