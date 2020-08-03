import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_URL } from './app.constante';
import { Question } from './question';

@Injectable({
  providedIn: 'root'
})
export class QuestionService {

  private baseUrl = SERVER_URL + SERVER_API_URL + 'questions';

  constructor(private http: HttpClient) { }
  
  getQuestionProfil(id: number): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.baseUrl}/${id}/profil`);
  }
  
  getQuestionVote(id: number): Observable<Question[]> {
    return this.http.get<Question[]>(`${this.baseUrl}/${id}/vote`);
  }

  creerQuestion(question: Question): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/creer`, question);
  }

  getResultat(id: string): Observable<string> {
    return this.http.get<string>(`${this.baseUrl}/${id}/resultat`);
  }
}
