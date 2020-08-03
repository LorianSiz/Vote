import { Component } from '@angular/core';
import { AuthService } from './connexion/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'VoteApp';

  constructor(private authService: AuthService) {}

  isLogin() {
    return this.authService.isUserLoggedIn();
  }

  isCreator() {
    return this.authService.isCreator();
  }

  handleLogout() {
    this.authService.logout();
  }
}
