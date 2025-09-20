import { Routes } from '@angular/router';
import { AlarmsComponent } from './pages/alarms/alarms.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/alarms',
    pathMatch: 'full'
  },
  {
    path: 'alarms',
    component: AlarmsComponent
  }
];
