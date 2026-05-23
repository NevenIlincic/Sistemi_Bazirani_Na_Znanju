import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { PlayerInfo } from '../../model/player-info';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PlayerInfoLoad } from '../../model/player-info-load';
import { PlayerService } from '../../service/player-service';

@Component({
  selector: 'app-players-section',
  imports: [],
  templateUrl: './players-section.html',
  styleUrl: './players-section.css',
})
export class PlayersSection implements OnInit {

  @Input() clubA: PlayerInfo[] = []
  @Input() clubB: PlayerInfo[] = []

  selectedClubA: string = "FC Barcelona";
  selectedClubB: string = "FC Arsenal";

  constructor(private playerService: PlayerService ,private httpClient: HttpClient, private cdr: ChangeDetectorRef){}

  ngOnInit(): void {
    this.getTeamA().subscribe({
      next: (info: PlayerInfoLoad[]) => {
        this.clubA = info.map((item: PlayerInfoLoad) => {
          return {
            club: item.club,
            firstName: item.firstName,
            lastName: item.lastName,
            number: item.number,
            imgUrl: item.imageUrl,
            selected: false
          };
        });
        this.cdr.detectChanges();
      }
    });
    this.getTeamB().subscribe({
      next: (info: PlayerInfoLoad[]) => {
        this.clubB = info.map((item: PlayerInfoLoad) => {
          return {
            club: item.club,
            firstName: item.firstName,
            lastName: item.lastName,
            number: item.number,
            imgUrl: item.imageUrl,
            selected: false
          };
        });
        this.cdr.detectChanges();
      }
    });
  }

  getTeamA(): Observable<PlayerInfoLoad[]>{
    return this.httpClient.get<PlayerInfoLoad[]>("barcelona.json");
  }

  getTeamB(): Observable<PlayerInfoLoad[]>{
    return this.httpClient.get<PlayerInfoLoad[]>("arsenal.json");
  }

  selectPlayer(player: PlayerInfo){
    console.log(player);
    this.playerService.setSelectedPlayer(player);
  }
}
