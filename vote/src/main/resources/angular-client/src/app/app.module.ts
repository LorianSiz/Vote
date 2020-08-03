import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA  } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CreateVoteComponent } from './create-vote/create-vote.component';
import { ListVoteComponent } from './list-vote/list-vote.component';
import { DetailsVoteComponent } from './details-vote/details-vote.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';
import { HttpInterceptorService } from './http-Interceptor.service';
import { ResultatVoteComponent } from './resultat-vote/resultat-vote.component';

@NgModule({
  declarations: [
    AppComponent,
    CreateVoteComponent,
    ListVoteComponent,
    DetailsVoteComponent,
    ConnexionComponent,
    InscriptionComponent,
    ResultatVoteComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
	  ReactiveFormsModule,
    HttpClientModule,
	  CommonModule
  ],
  providers: [
	{
      provide: HTTP_INTERCEPTORS,
      useClass: HttpInterceptorService,
      multi: true
    }
  ],
  bootstrap: [AppComponent],
  schemas: [ CUSTOM_ELEMENTS_SCHEMA ]
})
export class AppModule { }