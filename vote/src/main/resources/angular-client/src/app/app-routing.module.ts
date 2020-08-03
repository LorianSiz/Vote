import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListVoteComponent } from './list-vote/list-vote.component';
import { DetailsVoteComponent } from './details-vote/details-vote.component';
import { CreateVoteComponent } from './create-vote/create-vote.component';
import { ResultatVoteComponent } from './resultat-vote/resultat-vote.component';
import { ConnexionComponent } from './connexion/connexion.component';
import { InscriptionComponent } from './inscription/inscription.component';


const routes: Routes = [
  { path: '', redirectTo: 'connexion', pathMatch: 'full' },
  { path: 'votes', component: ListVoteComponent },
  { path: 'details/:voteId/:pseudo', component: DetailsVoteComponent },
  { path: 'details/:voteId', redirectTo: 'connexion', pathMatch: 'full' },
  { path: 'details', redirectTo: 'connexion', pathMatch: 'full' },
  { path: 'creer', component: CreateVoteComponent },
  { path: 'resultat/:voteId/:nouveau', component: ResultatVoteComponent },
  { path: 'resultat/:voteId', redirectTo: 'connexion', pathMatch: 'full' },
  { path: 'resultat', redirectTo: 'connexion', pathMatch: 'full' },
  { path: 'connexion', component: ConnexionComponent },
  { path: 'inscription', component: InscriptionComponent },
  { path: 'deconnecter', component: ConnexionComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }