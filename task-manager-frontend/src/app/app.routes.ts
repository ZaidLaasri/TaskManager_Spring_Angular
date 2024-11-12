import { Routes } from '@angular/router';
import path from "path";
import {LoginComponent} from "./component/login/login.component";

export const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path:'', redirectTo:'/login', pathMatch:'full'}
];
