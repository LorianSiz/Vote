import { Component, OnInit } from "@angular/core";
import { Router } from '@angular/router';
import {  FormGroup, FormBuilder, Validators } from '@angular/forms';
import { AuthService } from './auth.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css']
})
export class ConnexionComponent implements OnInit {
  submitTest = false;
  pseudo: string;
  mdp : string;
  errorMessage = 'Erreur d\'authentifcation';
  successMessage: string;
  invalidLogin = false;
  loginSuccess = false;

  constructor(private formBuilder: FormBuilder,
    private authService: AuthService,
    private router: Router) {}
  
  formCreateUser: FormGroup;
  
  userFormGroup(): FormGroup {
    return this.formBuilder.group({
      pseudo: ['', Validators.required],
	    mdp: ['', Validators.required],
    });
  }

  ngOnInit(): void {
	  this.formCreateUser = this.formBuilder.group([
		  this.userFormGroup()
	  ]);
  }
  
  onSubmit() {
    if(this.formCreateUser.valid) {
      this.submitTest = true;
      this.authService.authService(this.pseudo, this.mdp).subscribe((resultat)=> {
        this.invalidLogin = false;
        this.loginSuccess = true;
        this.successMessage = 'Authentification réussie.';
        this.authService.getUserId(this.pseudo).subscribe((id) => {
          sessionStorage.setItem(this.authService.USER_ID_SESSION_ATTRIBUTE_NAME, id);
          this.authService.getUserRole(id).subscribe((role) => {
            sessionStorage.setItem(this.authService.USER_ROLE_SESSION_ATTRIBUTE_NAME, role);
            this.router.navigate(['/votes']);
          });
        });
      }, () => {
        this.invalidLogin = true;
        this.loginSuccess = false;
      });
    } else {
      this.submitTest = true;
    }
  }
  
  testPseudo() {
	return ((!this.formCreateUser.get("0").get('pseudo').valid && this.formCreateUser.get("0").get('pseudo').touched) || (this.submitTest && !this.formCreateUser.get("0").get('pseudo').valid));
  }
  
  testMdp() {
	return ((!this.formCreateUser.get("0").get('mdp').valid && this.formCreateUser.get("0").get('mdp').touched) || (this.submitTest && !this.formCreateUser.get("0").get('mdp').valid));
  }
}
