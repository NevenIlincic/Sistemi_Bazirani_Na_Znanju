import { ChangeDetectorRef, Component, OnDestroy, OnInit } from '@angular/core';
import { RecommendationService } from '../service/recommendation-service';
import { PlayerService } from '../service/player-service';
import { MatchEventService } from '../service/match-event-service';
import { PlayerInfo } from '../model/player-info';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-nav-bar',
  imports: [],
  templateUrl: './nav-bar.html',
  styleUrl: './nav-bar.css',
})
export class NavBar implements OnInit, OnDestroy {

  constructor(private recommendationService: RecommendationService, private playerService: PlayerService,
    private cdr: ChangeDetectorRef, private matcheEventService: MatchEventService, private snackBar: MatSnackBar) { }

  minutes: number = 0;
  seconds: number = 0;

  private intervalId: any;

  ngOnInit(): void {
    this.startTimer();
  }

  startTimer(): void {
    this.intervalId = setInterval(() => {
      this.seconds += 1;

      if (this.seconds === 60) {
        this.seconds = 0;
        this.minutes += 1;
      }
      this.cdr.detectChanges();
    }, 1000);
  }
  ngOnDestroy(): void {
    if (this.intervalId) {
      clearInterval(this.intervalId);
    }
  }

  askRecommendation() {
    if (this.matcheEventService.isSmallFoul.getValue()) {
      const selectedPlayer: PlayerInfo | null = this.playerService.getSelectedPlayer();
      if (selectedPlayer != null){
        const playerId: string = `${selectedPlayer.club}-${selectedPlayer.firstName}-${selectedPlayer.lastName}-${selectedPlayer.number}`;
        this.matcheEventService.noteSmallFoul(playerId).subscribe({
          next: (value: void) => {
            this.snackBar.open("FOUL RECORDED!", "CLOSE", {
            duration: 3000,
            horizontalPosition: "center",
            verticalPosition: "bottom",
            panelClass: ["complete-snackbar"]
          })
          }
        });
      }
    } else {
      console.log("INCIDENT!");
      this.recommendationService.sendIncidentInformation();
    }
  }

  insertPlayers(): void {
    this.playerService.insertPlayers().subscribe();
  }

  resetOptions(): void {
    this.recommendationService.resetOptions();
  }

  advanceTime(): void {
    this.matcheEventService.advanceClockTime().subscribe({
      next: (value: void) => {
        this.minutes += 2;
      }
    });
  }
}
