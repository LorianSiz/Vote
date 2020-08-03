import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_URL } from './app.constante';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = SERVER_URL + SERVER_API_URL + "users";

  constructor(private http: HttpClient) { }
  
  creerUser(user: User): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/creer`, user);
  }

  creerVotant(user: User): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/votant`, user);
  }

}
