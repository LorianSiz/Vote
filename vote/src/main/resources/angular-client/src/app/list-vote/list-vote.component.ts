import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { AuthService } from '../connexion/auth.service';
import { Vote } from "../vote";
import { VoteService } from "../vote.service";

@Component({
  selector: 'app-list-vote',
  templateUrl: './list-vote.component.html',
  styleUrls: ['./list-vote.component.css']
})
export class ListVoteComponent implements OnInit {
  votes: Vote[];

  constructor(private voteService: VoteService,
    private router: Router,
    private authService: AuthService) {}

  ngOnInit() {
    if(sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME) === null) {
      this.router.navigate(['connexion']);
    } else {
      let id = sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME);
      this.voteService.getVotesList(id).subscribe(result => {
        this.votes = result;
      });
    }
  }
  
  Voir(id: number) {
	  this.router.navigate(['resultat', id, false]);
  }
}