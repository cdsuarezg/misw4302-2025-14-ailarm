import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { CustomSnackbarComponent } from '../../components/custom-snackbar/custom-snackbar.component';
import { LoadingSpinnerComponent } from '../../components/loading-spinner/loading-spinner.component';

@Component({
  selector: 'app-alarm-with-ai',
  templateUrl: './alarm-with-ai.component.html',
  styleUrls: ['./alarm-with-ai.component.scss'],
  standalone: true,
  imports: [
    CommonModule,
    MatButtonModule,
    MatIconModule,
    MatSnackBarModule,
    LoadingSpinnerComponent
  ]
})
export class AlarmWithAiComponent implements OnInit {
  private router = inject(Router);
  private snackBar = inject(MatSnackBar);

  isRecording = false;

  constructor() { }

  ngOnInit() {
  }

  goBack(): void {
    this.router.navigate(['/alarms']);
  }

  startRecording(): void {
    this.isRecording = true;

    setTimeout(() => {
      this.isRecording = false;

      const snackBarRef = this.snackBar.openFromComponent(CustomSnackbarComponent, {
        data: {
          message: 'Tu alarma para las 8:30 a.m. esta configurada y compartida con tu grupo',
          action: '',
          showCloseIcon: true,
        },
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        panelClass: ['snackbar-success']
      });

      this.router.navigate(['/alarms']);
    }, 4000);
  }
}
