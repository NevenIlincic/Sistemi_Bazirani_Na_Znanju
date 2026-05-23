import { Component, OnInit } from '@angular/core';
import { PlayerInfo } from '../../model/player-info';
import { PlayerService } from '../../service/player-service';

@Component({
  selector: 'app-selected-player-section',
  imports: [],
  templateUrl: './selected-player-section.html',
  styleUrl: './selected-player-section.css',
})
export class SelectedPlayerSection implements OnInit {

  selectedPlayer: PlayerInfo | null = null;

  constructor(private playerService: PlayerService){}

  ngOnInit(): void {
    this.playerService.watchSelectedPlayer$.subscribe({
      next: (player: PlayerInfo | null) => {
        this.selectedPlayer = player;
      }
    });
  }
}
