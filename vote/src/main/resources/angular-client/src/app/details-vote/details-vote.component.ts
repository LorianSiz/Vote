import { Component, OnInit } from "@angular/core";
import { Router , ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';
import { Reponse } from '../reponse';
import { ReponseService } from '../reponse.service';
import { AuthService } from '../connexion/auth.service';
import { Question, TYPE } from "../question";
import { QuestionService } from "../question.service";
import { VoteService } from '../vote.service';

@Component({
  selector: 'app-details-vote',
  templateUrl: './details-vote.component.html',
  styleUrls: ['./details-vote.component.css']
})
export class DetailsVoteComponent implements OnInit {
	questionsProfil: Question[];
	questionsVote: Question[];
	voteId: number;
	idVotant: number;
	pseudo: string;
	submitTest = false;
	jauge = [];
	math = Math;
	formCreateReponse: FormGroup;

  constructor(
	private QuestionService: QuestionService,
	private router: Router, 
	private route: ActivatedRoute, 
	private formBuilder: FormBuilder, 
	private authService: AuthService,
	private reponseService: ReponseService,
	private voteService: VoteService
	) {}

  ngOnInit() {
	this.voteId = this.route.snapshot.params['voteId'];
	this.pseudo = this.route.snapshot.params['pseudo'];
	if(sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME) === null) {
		this.authService.authService(this.pseudo, "123456").subscribe(result => {
			this.authService.getUserId(this.pseudo).subscribe((id) => {
				sessionStorage.setItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME, id);
				this.authService.getUserRole(id).subscribe((role) => {
					sessionStorage.setItem(this.authService.USER_ROLE_SESSION_ATTRIBUTE_NAME, role);
				});
				this.voteService.verifVotant(this.voteId, sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME)).subscribe(verifVotant => {
					if(!verifVotant) {
						this.QuestionService.getQuestionProfil(this.voteId).subscribe(resultP => {
							this.questionsProfil = resultP;
							this.initializeJauge(this.questionsProfil);
							this.QuestionService.getQuestionVote(this.voteId).subscribe(resultV => {
								this.questionsVote = resultV;
								this.initializeJauge(this.questionsVote);
								this.initializeFormArray(this.questionsVote, this.questionsProfil);
							});
						});
					} else {
						this.router.navigate(['resultat', this.voteId, false]);
					}
				});
			});
		});
	} else {
		this.voteService.verifVotant(this.voteId, sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME)).subscribe(verifVotant => {
			if(!verifVotant) {
				this.QuestionService.getQuestionProfil(this.voteId).subscribe(resultP => {
					this.questionsProfil = resultP;
					this.initializeJauge(this.questionsProfil);
					this.QuestionService.getQuestionVote(this.voteId).subscribe(resultV => {
						this.questionsVote = resultV;
						this.initializeJauge(this.questionsVote);
						this.initializeFormArray(this.questionsVote, this.questionsProfil);
					});
				});
			} else {
				this.router.navigate(['resultat', this.voteId, false]);
			}
		});
	}
  }

  initializeJauge(questList: Question[]) {
	questList.forEach(element => {
		if(element.type === TYPE.JAUGE) {
			this.jauge[element.id] = this.milieu(element);
		}
	});
  }

  initializeFormArray(questionsVote: Question[], questionsProfil: Question[]) {
	this.formCreateReponse = this.formBuilder.group({
		reponsesVote: this.formBuilder.array([]),
		reponsesProfil: this.formBuilder.array([])
	});
	questionsVote.forEach(element => {
		if(element.type.toString() === "MULTICHOIX") {
			(<FormArray>this.formCreateReponse.get('reponsesVote')).push(this.reponseMultiFormGroup(element));
		} else {
			(<FormArray>this.formCreateReponse.get('reponsesVote')).push(this.reponseFormGroup());
		}
	});
	questionsProfil.forEach(element => {
		if(element.type.toString() === "MULTICHOIX") {
			(<FormArray>this.formCreateReponse.get('reponsesProfil')).push(this.reponseMultiFormGroup(element));
		} else {
			(<FormArray>this.formCreateReponse.get('reponsesProfil')).push(this.reponseFormGroup());
		}
	});
  }
  
  reponseFormGroup(): FormGroup {
    return this.formBuilder.group({
      repVotant: ['', Validators.required],
    });
  }

  reponseMultiFormGroup(quest: Question): FormArray {
	let formResult = this.formBuilder.array([]);
	quest.reponses.forEach(element => {
		formResult.push(this.formBuilder.control(false, Validators.required));
	});
	return formResult;
  }
  
  onSubmit() {
	this.submitTest = true;
	if(this.formCreateReponse.valid) {
		(<FormArray>this.formCreateReponse.get("reponsesProfil")).value.forEach((repProfil, id: number) => {
			let questProfil = this.questionsProfil[id];
			if(questProfil.type.toString() !== "MULTICHOIX") {
				let repQuestProfil  = new Reponse();
				repQuestProfil.questionId = questProfil.id;
				repQuestProfil.userId = sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME);
				repQuestProfil.contenu = repProfil.repVotant;
				this.reponseService.creerReponse(repQuestProfil).subscribe((result)=> {
					this.voteService.verifTerminer(this.voteId).subscribe();
				});
			} else {
				repProfil.forEach((repProfilMulti, idRep: number) => {
					if(repProfilMulti === true) {
						let repQuestProfilMulti  = new Reponse();
						repQuestProfilMulti.questionId = questProfil.id;
						repQuestProfilMulti.userId = sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME);
						repQuestProfilMulti.contenu = questProfil.reponses[idRep];
						this.reponseService.creerReponse(repQuestProfilMulti).subscribe((result)=> {
							this.voteService.verifTerminer(this.voteId).subscribe();
						});
					}
				})
			}
		});
		(<FormArray>this.formCreateReponse.get("reponsesVote")).value.forEach((repVote, id: number) => {
			let questVote = this.questionsVote[id];
			if(questVote.type.toString() !== "MULTICHOIX") {
				let repQuestVote  = new Reponse();
				repQuestVote.questionId = questVote.id;
				repQuestVote.userId = sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME);
				repQuestVote.contenu = repVote.repVotant;
				this.reponseService.creerReponse(repQuestVote).subscribe((result)=> {
					this.voteService.verifTerminer(this.voteId).subscribe();
				});
			} else {
				repVote.forEach((repVoteMulti, idRep: number) => {
					if(repVoteMulti === true) {
						let repQuestVoteMulti  = new Reponse();
						repQuestVoteMulti.questionId = questVote.id;
						repQuestVoteMulti.userId = sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME);
						repQuestVoteMulti.contenu = questVote.reponses[idRep];
						this.reponseService.creerReponse(repQuestVoteMulti).subscribe((result)=> {
							this.voteService.verifTerminer(this.voteId).subscribe();
						});
					}
				})
			}
		});
		this.router.navigate(['resultat', this.voteId, true]);
	}
  }
  
  CondSC(type: string): boolean {	
	if (type === "SIMPLECHOIX") {
		return true;
	} else {
		return false;
	}
  }
  
  CondMC(type: string): boolean {
	if (type === "MULTICHOIX") {
		return true;
	} else {
		return false;
	}
  }
  
   CondNIV(type: string): boolean {
	if (type === "JAUGE") {
		return true;
	} else {
		return false;
	}
  }
  
  milieu(quest: Question): number {
	if(!this.jauge[quest.id]) {
		var value = Math.trunc((parseFloat(quest.reponses[0]) + parseFloat(quest.reponses[1]))/2);
		this.jauge[quest.id] = value;
		return value;
	}
  }
  
  valueChanger(value: number, id: number) {
	this.jauge[id] = value;
  }

  min(quest: Question): string {
	  if(parseInt(quest.reponses[0]) <= parseInt(quest.reponses[1])) {
		return quest.reponses[0];
	  } else {
		return quest.reponses[1];
	  }
  }

  max(quest: Question): string {
	if(parseInt(quest.reponses[0]) >= parseInt(quest.reponses[1])) {
	  return quest.reponses[0];
	} else {
	  return quest.reponses[1];
	}
  }

  testReponseProfil(id: number) {
  	return ((!this.formCreateReponse.get('reponsesProfil').get(id.toString()).valid && (this.formCreateReponse.get('reponsesProfil').get(id.toString()).touched || this.submitTest)));
  }
  
  testReponseVote(id: number) {
	return ((!this.formCreateReponse.get('reponsesVote').get(id.toString()).valid && (this.formCreateReponse.get('reponsesVote').get(id.toString()).touched || this.submitTest)));
  }

}