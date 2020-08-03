import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UserService } from "../user.service";
import { User } from "../user";

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css']
})
export class InscriptionComponent implements OnInit {
  submitTest = false;
  user: User = new User();
  errorMessage: string;
  successMessage = "Inscription réussite";
  invalidInscription = false;
  inscriptionSuccess = false;

  constructor(private formBuilder: FormBuilder, private userService: UserService, private router: Router) {}
  
  formCreateUser: FormGroup;
  
  userFormGroup(): FormGroup {
    return this.formBuilder.group({
      pseudo: ['', Validators.required],
	    password: ['', Validators.required],
	    confirmation: ['', Validators.required],
    });
  }

  ngOnInit(): void {
	  this.formCreateUser = this.formBuilder.group([
		  this.userFormGroup()
	  ]);
  }
  
  onSubmit() {
    if(this.formCreateUser.valid) {
      this.submitTest = false;
      this.user.role = "CREATOR";
      this.userService.creerUser(this.user).subscribe((result)=> {
        if(!result) {
          this.invalidInscription = true;
          this.inscriptionSuccess = false;
          this.errorMessage = 'Utilisateur déjà existant';
        } else {
          this.invalidInscription = false;
          this.inscriptionSuccess = true;
          this.router.navigate(['/connexion']);
        }
      });
    } else {
      this.submitTest = true;
      this.invalidInscription = true;
      this.errorMessage = 'Erreur inscription';
    }
  }
  
  testPseudo() {
	  return ((!this.formCreateUser.get("0").get('pseudo').valid && this.formCreateUser.get("0").get('pseudo').touched) || (this.submitTest && !this.formCreateUser.get("0").get('pseudo').valid));
  }
  
  testPassword() {
	  return ((!this.formCreateUser.get("0").get('password').valid && this.formCreateUser.get("0").get('password').touched) || (this.submitTest && !this.formCreateUser.get("0").get('password').valid));
  }
  
  testConfirmation() {
	  return ((this.submitTest && !this.formCreateUser.get("0").get('confirmation').valid) || 
	  ((this.formCreateUser.get("0").get('confirmation').value !== this.formCreateUser.get("0").get('password').value) && this.formCreateUser.get("0").get('confirmation').touched));
  }
}
