import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_URL } from './app.constante';
import { Vote } from './vote';

@Injectable({
  providedIn: 'root'
})
export class VoteService {
	
  private baseUrl = SERVER_URL + SERVER_API_URL + 'votes';

  constructor(private http: HttpClient) { }
  
  getVote(idVote: number): Observable<Vote> {
    return this.http.get<Vote>(`${this.baseUrl}/${idVote}`);
  }
  
  getVotesList(idVote: string): Observable<Vote[]> {
    return this.http.post<Vote[]>(`${this.baseUrl}`, idVote);
  }

  creerVote(vote: Vote): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/creer`, vote);
  }

  verifVotant(idVote: number, idVotant: string): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/verifvotant/${idVote}/${idVotant}`);
  }

  verifTerminer(idVote: number): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/verifterminer`, idVote);
  }

  getDisponible(idVote: number): Observable<boolean> {
    return this.http.get<boolean>(`${this.baseUrl}/disponible/${idVote}`);
  }
}