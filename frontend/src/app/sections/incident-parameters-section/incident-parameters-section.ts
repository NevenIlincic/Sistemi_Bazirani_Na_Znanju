import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RecommendationService } from '../../service/recommendation-service';

@Component({
  selector: 'app-incident-parameters-section',
  imports: [FormsModule],
  templateUrl: './incident-parameters-section.html',
  styleUrl: './incident-parameters-section.css',
})
export class IncidentParametersSection implements OnInit{
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

    ngOnInit(): void {
      //CONTACT
      this.recommendationService.watchContact$.subscribe({
        next: (value: boolean) => {
          this.isContactMade = value;
        }
      });
      //PLAYER DOWN
      this.recommendationService.watchIsPlayerDown$.subscribe({
        next: (value: boolean) => {
          this.isPlayerTackledDown = value;
        }
      });
      //BALL TOUCHED FIRST
      this.recommendationService.wathcIsBallContactFirst$.subscribe({
        next: (value: boolean) => {
          this.ballTouchedFirst = value;
        }
      });
      //OPPONENT HAND
      this.recommendationService.watchOpponentHandTouch$.subscribe({
        next: (value: boolean) => {
          this.opponentHandTouch = value;
        }
      });
      //HAND ENLARGES BODY
      this.recommendationService.watchHandEnlargesBody$.subscribe({
        next: (value: boolean) => {
          this.handEnlargesBody = value;
        }
      });
      //OPEN FOOT
      this.recommendationService.watchOpenFoot$.subscribe({
        next: (value: boolean) => {
          this.openFoot = value;
        }
      });
      //CONTACT FROM BEHIND
      this.recommendationService.watchContactFromBehind$.subscribe({
        next: (value: boolean) => {
          this.contactFromBehind = value;
        }
      });
      //BALL INTENTION
      this.recommendationService.watchBallIntention$.subscribe({
        next: (value: boolean) => {
          this.ballIntention = value;
        }
      });
      //DANGEROUS PLAY
      this.recommendationService.watchDangerousPlay$.subscribe({
        next: (value: boolean) => {
          this.dangerousPlay = value;
        }
      });
    }

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
