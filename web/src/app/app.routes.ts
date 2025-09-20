import { Routes } from '@angular/router';
import { AlarmsComponent } from './pages/alarms/alarms.component';
import { AlarmWithAiComponent } from './pages/alarm-with-ai/alarm-with-ai.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: '/alarms',
    pathMatch: 'full'
  },
  {
    path: 'alarms',
    component: AlarmsComponent
  },
  {
    path: 'alarm-with-ai',
    component: AlarmWithAiComponent
  }
];
