import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from '../connexion/auth.service';
import { VoteService } from '../vote.service';
import { Question } from '../question';
import { QuestionService } from '../question.service';

@Component({
  selector: 'app-resultat-vote',
  templateUrl: './resultat-vote.component.html',
  styleUrls: ['./resultat-vote.component.css']
})
export class ResultatVoteComponent implements OnInit {
  voteId: number;
  disponible: boolean;
  nouveauVote: boolean;
  enCours: boolean;
  questionsProfil: Question[];
  questionsVote: Question[];
  reponsesProfil: string[] = [];
  reponsesVote: string[] = [];

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private authService: AuthService,
    private voteService: VoteService,
    private QuestionService: QuestionService
    ) { }

  ngOnInit(): void {
    this.voteId = this.route.snapshot.params['voteId'];
    this.nouveauVote = (this.route.snapshot.params['nouveau'] === "true");
    if(sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME) !== null) {
      this.voteService.getDisponible(this.voteId).subscribe((resultat) => {
        this.enCours = resultat;
        if(!this.enCours) {
          this.QuestionService.getQuestionProfil(this.voteId).subscribe(resultP => {
            this.questionsProfil = resultP;
            this.questionsProfil.forEach((questP) => {
              this.QuestionService.getResultat(questP.id).subscribe((resultatP) => {
                this.reponsesProfil.push(resultatP);
              });
            });
            this.QuestionService.getQuestionVote(this.voteId).subscribe(resultV => {
              this.questionsVote = resultV;
              this.questionsVote.forEach((questV) => {
                this.QuestionService.getResultat(questV.id).subscribe((resultatV) => {
                  this.reponsesVote.push(resultatV);
                });
              });
            });
          });
        }
      });
    } else {
      this.router.navigate(['connexion']);
    }
  }

}
