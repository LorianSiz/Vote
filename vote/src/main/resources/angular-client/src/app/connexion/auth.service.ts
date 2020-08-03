import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SERVER_API_URL, SERVER_URL } from '../app.constante';
import { map } from 'rxjs/operators';
import { UserService } from '../user.service';

@Injectable({
  providedIn: 'root'
})

export class AuthService {

	private baseUrl = SERVER_URL + SERVER_API_URL;
	
	USER_NAME_SESSION_ATTRIBUTE_NAME = 'authenticatedUser'
	USER_ID_SESSION_ATTRIBUTE_NAME = 'idUser'
	USER_ROLE_SESSION_ATTRIBUTE_NAME = 'role'
	
	public pseudo: String;
	public mdp: String;

	constructor(private http: HttpClient, private userService: UserService) { }
	
	authService(pseudo: string, mdp: string) {
    return this.http.get(`${this.baseUrl}basicauth`,
      { headers: { authorization: this.createBasicAuthToken(pseudo, mdp) } }).pipe(map((res) => {
        this.pseudo = pseudo;
		this.mdp = mdp;
		this.registerSuccessfulLogin(pseudo, mdp);
      }));
    }
  
	createBasicAuthToken(pseudo: string, mdp: string) {
		return 'Basic ' + window.btoa(pseudo + ":" + mdp)
	}

	registerSuccessfulLogin(pseudo: string, password: string) {
		sessionStorage.setItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME, this.createBasicAuthToken(pseudo, password));
	}

	getUserId(pseudo: String) {
		return this.http.post<string>(`${this.baseUrl}users/pseudo`, pseudo);
	}

	getUserRole(id: String) {
		return this.http.post<string>(`${this.baseUrl}users/role`, id);
	}

	logout() {
		sessionStorage.removeItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME);
		sessionStorage.removeItem(this.USER_ID_SESSION_ATTRIBUTE_NAME);
		this.pseudo = null;
		this.mdp = null;
	}

	isUserLoggedIn() {
		let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
		if (user === null) return false
		return true
	}

	isCreator() {
		let role = sessionStorage.getItem(this.USER_ROLE_SESSION_ATTRIBUTE_NAME)
		if (role === "false") return false
		return true
	}

	getLoggedInUserName() {
		let user = sessionStorage.getItem(this.USER_NAME_SESSION_ATTRIBUTE_NAME)
		if (user === null) return ''
		return user
	}
}
