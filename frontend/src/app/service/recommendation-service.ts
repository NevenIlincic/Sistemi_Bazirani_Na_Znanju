import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PlayerService } from './player-service';
import { IncidentDTO } from '../dto/IncidentDTO';
import { PlayerInfo } from '../model/player-info';
import { GameStateDTO } from '../dto/GameStateDTO';
import { VarRequestDTO } from '../dto/VarRequestDTO';
import { RecommendationDTO } from '../dto/RecommendationDTO';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environment/environment';

@Injectable({
  providedIn: 'root',
})
export class RecommendationService {

  //INCIDENT INFO
  private incidentId: number = 1;

  intensity: BehaviorSubject<string | null> = new BehaviorSubject<string | null>("LOW");
  watchIntensity$ = this.intensity.asObservable();

  contactPoint: BehaviorSubject<string | null> = new BehaviorSubject<string | null>("FOOT");
  watchContactPoint$ = this.contactPoint.asObservable();

  foulType: BehaviorSubject<string | null> = new BehaviorSubject<string | null>("NON_EXISTENT");
  watchFoulType$ = this.foulType.asObservable();

  tackleControl: BehaviorSubject<string | null> = new BehaviorSubject<string | null>("CONTROLLED");
  watchTackleControl$ = this.tackleControl.asObservable();

  handPosition: BehaviorSubject<string | null> = new BehaviorSubject<string | null>("NATURAL");
  watchHandPosition$ = this.handPosition.asObservable();

  contact: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchContact$ = this.contact.asObservable();

  isPlayerDown: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchIsPlayerDown$ = this.isPlayerDown.asObservable();

  isBallContactFirst: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  wathcIsBallContactFirst$ = this.isBallContactFirst.asObservable();

  opponentHandTouch: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchOpponentHandTouch$ = this.opponentHandTouch.asObservable();

  handEnlargesBody: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchHandEnlargesBody$ = this.handEnlargesBody.asObservable();

  openFoot: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchOpenFoot$ = this.openFoot.asObservable();

  contactFromBehind: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchContactFromBehind$ = this.contactFromBehind.asObservable();

  ballIntention: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchBallIntention$ = this.ballIntention.asObservable();

  dangerousPlay: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchDangerousPlay$ = this.dangerousPlay.asObservable();

  //GAME STATE INFO
  location: BehaviorSubject<string> = new BehaviorSubject<string>("OUTSIDE_PENALTY_AREA");
  watchLocation$ = this.location.asObservable();

  distance: BehaviorSubject<string> = new BehaviorSubject<string>("FAR");
  watchDistance$ = this.distance.asObservable();

  movingDirection: BehaviorSubject<string> = new BehaviorSubject<string>("TOWARDS_GOAL");
  watchMovingDirection$ = this.movingDirection.asObservable();

  ballControl: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchBallControl$ = this.ballControl.asObservable();

  areDefendersAhead: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchAreDefendersAhead$ = this.areDefendersAhead.asObservable();

  goalScored: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchGoalScored$ = this.goalScored.asObservable();

  constructor(private playerService: PlayerService, private httpClient: HttpClient) { }

  resetOptions() {
    //INCIDENT INFO
    this.playerService.resetSelectedPlayer();
    this.intensity.next("LOW");
    this.contactPoint.next("FOOT");
    this.foulType.next("NON_EXISTENT");
    this.tackleControl.next("CONTROLLED");
    this.handPosition.next("NATURAL");
    this.contact.next(false);
    this.isPlayerDown.next(false);
    this.isBallContactFirst.next(false);
    this.opponentHandTouch.next(false);
    this.handEnlargesBody.next(false);
    this.openFoot.next(false);
    this.contactFromBehind.next(false);
    this.ballIntention.next(false);
    this.dangerousPlay.next(false);
    //GAME STATE INFO
    this.location.next("OUTSIDE_PENALTY_AREA");
    this.distance.next("FAR");
    this.movingDirection.next("TOWARDS_GOAL");
    this.ballControl.next(false);
    this.areDefendersAhead.next(false);
    this.goalScored.next(false);
  }

  sendIncidentInformation() {
    const selectedPlayer: PlayerInfo | null = this.playerService.getSelectedPlayer();
    let numDefendersAhead: number = 0;
    if (this.areDefendersAhead){
      numDefendersAhead = 1;
    }
    if (selectedPlayer != null) {
      const playerId: string = `${selectedPlayer.club}-${selectedPlayer.number}`;
      const incidentDTO: IncidentDTO = {
        incidentId: this.incidentId,
        playerId: playerId,
        contact: this.contact.getValue(),
        playerDown: this.isPlayerDown.getValue(),
        ballContactFirst: this.isBallContactFirst.getValue(),
        attackerHandContact: this.opponentHandTouch.getValue(),
        handEnlargesBody: this.handEnlargesBody.getValue(),
        openFoot: this.openFoot.getValue(),
        contactFromBehind: this.contactFromBehind.getValue(),
        ballIntention: this.ballIntention.getValue(),
        dangerousPlay: this.dangerousPlay.getValue(),
        handPosition: this.handPosition.getValue(),
        contactPoint: this.contactPoint.getValue(),
        tackleControl: this.tackleControl.getValue(),
        intensity: this.intensity.getValue(),
        foulType: this.foulType.getValue()
      };
      const gameStateDTO: GameStateDTO = {
        location: this.location.getValue(),
        distanceFromGoal: this.distance.getValue(),
        movingDirection: this.movingDirection.getValue(),
        ballControl: this.ballControl.getValue(),
        numDefendersAhead: numDefendersAhead,
        goalScored: this.goalScored.getValue()
      };
      const varRequestDTO: VarRequestDTO = {
        gameState: gameStateDTO,
        incident: incidentDTO
      };
      console.log(varRequestDTO);
      this.sendVarRequest(varRequestDTO).subscribe({
        next: (recommendations: RecommendationDTO[]) =>{
          console.log(recommendations);
          this.incidentId += 1;
          this.resetOptions();
        }
      });
    }
  }

  sendVarRequest(varRequest: VarRequestDTO): Observable<RecommendationDTO[]>{
    return this.httpClient.post<RecommendationDTO[]>(`${environment.apiUrl}/api/var/recommendations`, varRequest);
  }
}
