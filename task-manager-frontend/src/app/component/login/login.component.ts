import { Component } from '@angular/core';
import {FormsModule} from "@angular/forms";
import {RouterLink} from "@angular/router";
import {AuthService} from "../../services/auth.service";
import {Router} from "@angular/router";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    FormsModule,
    RouterLink
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email: string ='';
  password: string = '';

  constructor(private authService: AuthService, private router: Router) {
  }

  onLogin(){
    this.authService.login({email: this.email, motDePasse: this.password}).subscribe({
      next: ()=> this.router.navigate(['/login']),
      error: (err)=>console.error('Registration failed', err)
    });
  }

}
