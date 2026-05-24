import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-incident-parameters-section',
  imports: [FormsModule],
  templateUrl: './incident-parameters-section.html',
  styleUrl: './incident-parameters-section.css',
})
export class IncidentParametersSection {
    isContactMade: boolean = false;
    isPlayerTackledDown: boolean = false;
    ballTouchedFirst: boolean = false;
    opponentHandTouch: boolean = false;
    handEnlargesBody: boolean = false;
    openFoot: boolean = false;
    contactFromBehind: boolean = false;
    ballIntention: boolean = false;
    dangerousPlay: boolean = false;

    constructor(private recommendationService: RecommendationService){}

    onCheckBoxChange(){
      this.recommendationService.contact.next(this.isContactMade);
      this.recommendationService.isPlayerDown.next(this.isPlayerTackledDown);
      this.recommendationService.isBallContactFirst.next(this.ballTouchedFirst);
      this.recommendationService.opponentHandTouch.next(this.opponentHandTouch);
      this.recommendationService.handEnlargesBody.next(this.handEnlargesBody);
      this.recommendationService.openFoot.next(this.openFoot);
      this.recommendationService.contactFromBehind.next(this.contactFromBehind);
      this.recommendationService.ballIntention.next(this.ballIntention);
      this.recommendationService.dangerousPlay.next(this.dangerousPlay);
    }
}
