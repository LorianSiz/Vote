import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators, FormArray, FormControl } from '@angular/forms';
import {formatDate} from '@angular/common';
import { Question, TYPE } from "../question";
import { QuestionService } from '../question.service';
import { User } from "../user";
import { UserService } from "../user.service";
import { Formulaire } from "../formulaire";
import { FormulaireService } from "../formulaire.service";
import { Vote } from '../vote';
import { VoteService } from '../vote.service';
import { AuthService } from '../connexion/auth.service';

@Component({
  selector: 'app-create-vote',
  templateUrl: './create-vote.component.html',
  styleUrls: ['./create-vote.component.css']
})
export class CreateVoteComponent implements OnInit {
  dateActuelle = formatDate(new Date(), 'yyyy-MM-dd', 'en');
  submitTest = false;
  formCreateVote: FormGroup;

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    private formulaireService: FormulaireService,
    private questionService: QuestionService,
    private voteService: VoteService,
    private router: Router,
    private authService: AuthService) {}

  ngOnInit(): void {
    if(!this.authService.isCreator()) {
      this.router.navigate(['votes']);
    }
    if(sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME) === null) {
      this.router.navigate(['connexion']);
    } else {
      this.formCreateVote = this.formBuilder.group({
        descVoteForm: this.formBuilder.array([
          this.addDescFormGroup()
        ]),
        profilForm: this.formBuilder.array([
          this.addQuestionFormGroup()
        ]),
        voteForm: this.formBuilder.array([
          this.addQuestionFormGroup()
        ]),
        votantForm: this.formBuilder.array([
          this.addVotantFormGroup()
        ])
      });
    }
  }

  validRep(c: FormControl) {
    let REP_REGEXP = /^(([a-z0-9A-Z!"#$%&'()*+ ç,./:;<=>?éèàù@^_`{|}~-]{1,}))\\\\(([a-z0-9A-Z!"#$%&'()*+ ç,./\\:;<=>?@éèàù^_`{|}~-]{1,}))$/;
    return REP_REGEXP.test(c.value) ? null : {
      validRep: {
        valid: false
      }
    }
  }

  validEmail(c: FormControl) {
    let EMAIL_REGEXP = /^(([a-z0-9!"#$%&'()*+,./:;<=>?@\^_`{|}~-]{2,}))@(([a-z0-9]{4,})).(([a-z]{2,3}))$/;
    return EMAIL_REGEXP.test(c.value) ? null : {
      validEmail: {
        valid: false
      }
    }
  }

  addDescFormGroup(): FormGroup {
    return this.formBuilder.group({
      description: ['', Validators.required],
      dateFin: ['', Validators.required]
    });
  }

  addQuestionFormGroup(): FormGroup {
    return this.formBuilder.group({
      contenu: ['', Validators.required],
      type: ['', Validators.required],
      reponses: ['', [Validators.required, this.validRep]]
    });
  }
  
  addVotantFormGroup(): FormGroup {
    return this.formBuilder.group({
      mail: ['', [Validators.required, this.validEmail]]
    });
  }
  
  get ProfilFormData() { 
	  return (<FormArray>this.formCreateVote.get('profilForm')); 
  }
  
  get VoteFormData() { 
	  return (<FormArray>this.formCreateVote.get('voteForm')); 
  }
  
  get VotantFormData() { 
	  return (<FormArray>this.formCreateVote.get('votantForm')); 
  }

  AjoutLigneP() {
	  (<FormArray>this.formCreateVote.get("profilForm")).push(this.addQuestionFormGroup());
  }
  
  AjoutLigneV() {
	  (<FormArray>this.formCreateVote.get("voteForm")).push(this.addQuestionFormGroup());
  }
  
  AjoutVotant() {
	  (<FormArray>this.formCreateVote.get("votantForm")).push(this.addVotantFormGroup());
  }
  
  SupprP(id: number) {
    (<FormArray>this.formCreateVote.get("profilForm")).removeAt(id);
    if((<FormArray>this.formCreateVote.get("profilForm")).length === 0) {
      this.AjoutLigneP();
    }
  }
  
  SupprV(id: number) {
    (<FormArray>this.formCreateVote.get("voteForm")).removeAt(id);
    if((<FormArray>this.formCreateVote.get("voteForm")).length === 0) {
      this.AjoutLigneV();
    }
  }
  
  SupprVotant(id: number) {
    (<FormArray>this.formCreateVote.get("votantForm")).removeAt(id);
    if((<FormArray>this.formCreateVote.get("votantForm")).length === 0) {
      this.AjoutVotant();
    }
  }
  
  onSubmit() {
    this.submitTest = true;
    if(this.formCreateVote.valid) {
      let profilForm = new Formulaire();
      let profilVote = new Formulaire();
      profilForm.nom = "Formulaire Profil";
      profilVote.nom = "Formulaire Vote";
      this.formulaireService.creerFormulaire(profilForm).subscribe((idFormProfil)=> {
        (<FormArray>this.formCreateVote.get("profilForm")).value.forEach(element => {
          let questProfil = new Question();
          questProfil.contenu = element.contenu;
          if(element.type === "Jauge") {
            questProfil.type = TYPE.JAUGE;
          } else if(element.type === "Choix unique") {
            questProfil.type = TYPE.SIMPLECHOIX;
          } else {
            questProfil.type = TYPE.MULTICHOIX;
          }
          questProfil.reponses = element.reponses.split("\\\\");
          questProfil.formulaireId = idFormProfil;
          this.questionService.creerQuestion(questProfil).subscribe((result)=> {
          });
        });
        this.formulaireService.creerFormulaire(profilVote).subscribe((idFormVote)=> {
          (<FormArray>this.formCreateVote.get("voteForm")).value.forEach(element => {
            let questVote = new Question();
            questVote.contenu = element.contenu;
            if(element.type === "Jauge") {
              questVote.type = TYPE.JAUGE;
            } else if(element.type === "Choix unique") {
              questVote.type = TYPE.SIMPLECHOIX;
            } else {
              questVote.type = TYPE.MULTICHOIX;
            }
            questVote.reponses = element.reponses.split("\\\\");
            questVote.formulaireId = idFormVote;
            this.questionService.creerQuestion(questVote).subscribe((result)=> {
            });
          });
          let vote = new Vote();
          vote.description = this.formCreateVote.get('descVoteForm').get('0').get('description').value;
          vote.dateFin = this.formCreateVote.get('descVoteForm').get('0').get('dateFin').value;
          vote.profilFormId = idFormProfil;
          vote.voteFormId = idFormVote;
          vote.userId = sessionStorage.getItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME);
          this.voteService.creerVote(vote).subscribe((voteId)=> {
            (<FormArray>this.formCreateVote.get("votantForm")).value.forEach(element => {
              let userVotant = new User();
              userVotant.pseudo = element.mail;
              userVotant.password = "123456";
              userVotant.role = "USER";
              userVotant.voteId = voteId;
              this.userService.creerVotant(userVotant).subscribe((result)=> {
              });
            });
            this.router.navigate(['/votes']);
          });
        });
      });
    }
  }
  
  testDesc() {
    return ((!this.formCreateVote.get('descVoteForm').get('0').get('description').valid && this.formCreateVote.get('descVoteForm').get('0').get('description').touched) || 
    (this.submitTest && !this.formCreateVote.get('descVoteForm').get('0').get('description').valid));
  }

  testDateFin() {
	  return ((!this.formCreateVote.get('descVoteForm').get('0').get('dateFin').valid && this.formCreateVote.get('descVoteForm').get('0').get('dateFin').touched) || 
    (this.submitTest && !this.formCreateVote.get('descVoteForm').get('0').get('dateFin').valid));
  }

  testDates() {
	  return ((this.formCreateVote.get('descVoteForm').get('0').get('dateFin').value <= this.dateActuelle) && this.formCreateVote.get('descVoteForm').get('0').get('dateFin').touched);
  }
  
  testContenu(quest: FormGroup) {
	  return ((!quest.get('contenu').valid && (quest.get('contenu').touched || this.submitTest)));
  }
  
  testType(quest: FormGroup) {
	  return ((!quest.get('type').valid && (quest.get('type').touched || this.submitTest)));
  }
  
  testReponse(quest: FormGroup) {
	  return ((!quest.get('reponses').valid && (quest.get('reponses').touched || this.submitTest)));
  }
  
  testMail(votant: FormGroup) {
	  return ((!votant.get('mail').valid && (votant.get('mail').touched || this.submitTest)));
  }
}
