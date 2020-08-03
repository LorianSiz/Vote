import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SERVER_API_URL, SERVER_URL } from './app.constante';
import { Formulaire } from './formulaire';

@Injectable({
  providedIn: 'root'
})
export class FormulaireService {

  private baseUrl = SERVER_URL + SERVER_API_URL + "formulaires";

  constructor(private http: HttpClient) { }
  
  creerFormulaire(formulaire: Formulaire): Observable<string> {
    return this.http.post<string>(`${this.baseUrl}/creer`, formulaire);
  }

}
