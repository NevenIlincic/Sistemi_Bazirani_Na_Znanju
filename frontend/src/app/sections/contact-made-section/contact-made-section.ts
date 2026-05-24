import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { MatchEventService } from '../../service/match-event-service';

@Component({
  selector: 'app-contact-made-section',
  imports: [FormsModule],
  templateUrl: './contact-made-section.html',
  styleUrl: './contact-made-section.css',
})
export class ContactMadeSection {
  contactMade: string = "NO";

  constructor(private matchEventService: MatchEventService){}

  onOptionChange(): void{
    let isContactMade: boolean = false
    if (this.contactMade == "YES"){
      isContactMade = true;
    }
    this.matchEventService.contactMade.next(isContactMade);
  }
}
