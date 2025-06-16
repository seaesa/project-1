import { Routes } from '@angular/router';
import { StudentComponent } from './student.component';
import { BankComponent } from './bank.component';

export const routes: Routes = [
  { path: '', redirectTo: '/sinh-vien', pathMatch: 'full' },
  { path: 'sinh-vien', component: StudentComponent },
  { path: 'ngan-hang', component: BankComponent },
  { path: '**', redirectTo: '/sinh-vien' },
];
