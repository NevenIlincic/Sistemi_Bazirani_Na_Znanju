import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { PlayerInfo } from '../model/player-info';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environment/environment';
import { PlayerDTO } from '../dto/PlayerDTO';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {

  private selectedPlayer: BehaviorSubject<PlayerInfo | null> = new BehaviorSubject<PlayerInfo | null>(null);
  watchSelectedPlayer$ = this.selectedPlayer.asObservable();

  playersId: BehaviorSubject<PlayerDTO[]> = new BehaviorSubject<PlayerDTO[]>([]);

  constructor(private httpClient: HttpClient){}

  resetSelectedPlayer(){
    this.selectedPlayer.next(null);
  }

  setSelectedPlayer(player: PlayerInfo) {
    this.selectedPlayer.next(player);
  }

  getSelectedPlayer(): PlayerInfo | null{
    return this.selectedPlayer.getValue();
  }

  insertPlayers(): Observable<void> {
    console.log(this.playersId.getValue());
    return this.httpClient.post<void>(`${environment.apiUrl}api/match-event/players`, this.playersId.getValue());
  }
}
