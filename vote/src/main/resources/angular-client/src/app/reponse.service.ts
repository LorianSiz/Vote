import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_URL } from './app.constante';
import { Reponse } from './reponse';

@Injectable({
  providedIn: 'root'
})
export class ReponseService {

  private baseUrl = SERVER_URL + SERVER_API_URL + 'reponses';

  constructor(private http: HttpClient) { }

  creerReponse(reponse: Reponse): Observable<boolean> {
    return this.http.post<boolean>(`${this.baseUrl}/creer`, reponse);
  }

}
