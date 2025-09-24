import { Routes } from '@angular/router';
import { AlarmsComponent } from './pages/alarms/alarms.component';
import { AlarmWithAiComponent } from './pages/alarm-with-ai/alarm-with-ai.component';
import { AlarmCreateComponent } from './pages/alarm-create/alarm-create.component';

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
  },
  { path: 'alarms/new', component: AlarmCreateComponent }
];
