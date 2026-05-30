import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { environment } from '../../environment/environment';
import { PlayerFoulEventDTO } from '../dto/PlayerFoulEventDTO';

@Injectable({
  providedIn: 'root',
})
export class MatchEventService {
  
  constructor(private httpClient: HttpClient){}

  contactMade: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
  watchContactMade$ = this.contactMade.asObservable();

  isSmallFoul: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  advanceClockTime(): Observable<void>{
    return this.httpClient.get<void>(`${environment.apiUrl}api/match-event/advance-time`);
  }

  noteSmallFoul(playerId: string): Observable<void>{
    const playerFoulEvent: PlayerFoulEventDTO = {
      playerId: playerId,
      isContact: this.contactMade.getValue()
    };
    console.log(playerFoulEvent);
    return this.httpClient.post<void>(`${environment.apiUrl}api/match-event/small-foul`, playerFoulEvent);
  }
}
