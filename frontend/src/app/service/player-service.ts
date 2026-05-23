import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { PlayerInfo } from '../model/player-info';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {

  private selectedPlayer: BehaviorSubject<PlayerInfo | null> = new BehaviorSubject<PlayerInfo | null>(null);
  watchSelectedPlayer$ = this.selectedPlayer.asObservable();

  setSelectedPlayer(player: PlayerInfo) {
    this.selectedPlayer.next(player);
  }

  getSelectedPlayer(): PlayerInfo | null{
    return this.selectedPlayer.getValue();
  }
}
