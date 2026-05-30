import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatchEventService } from '../../service/match-event-service';

@Component({
  selector: 'app-contact-made-section',
  imports: [FormsModule],
  templateUrl: './contact-made-section.html',
  styleUrl: './contact-made-section.css',
})
export class ContactMadeSection implements OnInit {
  contactMade: string = "NO";

  constructor(private matchEventService: MatchEventService) { }

  ngOnInit(): void {
    this.matchEventService.watchContactMade$.subscribe({
      next: (value: boolean) => {
        if (value) {
          this.contactMade = "YES";
        } else {
          this.contactMade = "NO";
        }
      }
    });
  }

  onOptionChange(): void {
    let isContactMade: boolean = false
    if (this.contactMade == "YES") {
      isContactMade = true;
    }
    this.matchEventService.contactMade.next(isContactMade);
  }
}
