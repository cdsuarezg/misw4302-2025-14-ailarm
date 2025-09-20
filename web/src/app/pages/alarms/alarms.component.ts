import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';

interface Alarm {
  id: number;
  time: string;
  labels: string[];
  isActive?: boolean;
}

@Component({
  selector: 'app-alarms',
  standalone: true,
  imports: [CommonModule, MatCardModule, MatIconModule, MatButtonModule, MatChipsModule],
  templateUrl: './alarms.component.html',
  styleUrls: ['./alarms.component.scss']
})
export class AlarmsComponent {
  private router = inject(Router);
  
  alarms: Alarm[] = [
    {
      id: 1,
      time: '7:00 a.m.',
      labels: ['Desayuno', 'Medicamento'],
      isActive: true
    },
    {
      id: 2,
      time: '08:00 a.m.',
      labels: ['Entrenamiento', 'Sábado'],
      isActive: true
    },
    {
      id: 3,
      time: '09:00 a.m.',
      labels: ['Medicamento', 'Jueves'],
      isActive: false
    },
    {
      id: 4,
      time: '10:00 a.m.',
      labels: ['Clase', 'Martes'],
      isActive: true
    },
  ];

  onEditAlarm(alarm: Alarm): void {
    console.log('Editando alarma:', alarm);
  }

  onShareAlarm(alarm: Alarm): void {
    console.log('Compartiendo alarma:', alarm);
  }

  onDeleteAlarm(alarm: Alarm): void {
    console.log('Eliminando alarma:', alarm);
  }

  onAddAlarm(): void {
    // Navegar a la página de agregar alarma
  }

  onAddAlarmWithAI(): void {
    this.router.navigate(['/alarm-with-ai']);
  }

  trackByAlarmId(index: number, alarm: Alarm): number {
    return alarm.id;
  }
}