
import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatDialog, MatDialogModule } from '@angular/material/dialog';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';
import { FrequencyDialogComponent } from '../../dialogs/frequency-dialog/frequency-dialog.component';
import { SoundDialogComponent } from '../../dialogs/sound-dialog/sound-dialog.component';
import { AlarmsService } from '../../services/alarms.service';
import { CustomSnackbarComponent } from '../../components/custom-snackbar/custom-snackbar.component';
import { TimePickerDialogComponent, TimeResult } from '../../dialogs/time-picker-dialog/time-picker-dialog.component';


@Component({
  selector: 'app-alarm-create',
  standalone: true,
  imports: [
    CommonModule, FormsModule,
    MatFormFieldModule, MatInputModule, MatSelectModule,
    MatButtonModule, MatIconModule, MatDialogModule, MatSnackBarModule
  ],
  templateUrl: './alarm-create.component.html',
  styleUrls: ['./alarm-create.component.scss']
})
export class AlarmCreateComponent {
  time = '07:00';
  meridiem: 'AM'|'PM' = 'AM';
  frequencyLabel = 'Diaria';
  weekdays: number[] = [1,2,3,4,5]; 
  label = '';
  sound = 'Sound 2';
  private snackBar = inject(MatSnackBar)
  constructor(
    private router: Router,
    private dialog: MatDialog,
    private alarms: AlarmsService
  ) {}

  pickFrequency() {
    const ref = this.dialog.open(FrequencyDialogComponent, { data: { selected: this.weekdays },
      width: '350px',
      panelClass: 'ailarm-dialog' });
    ref.afterClosed().subscribe((w: number[]|undefined) => {
      if (!w) return;
      this.weekdays = w;
      this.frequencyLabel =
        (w.length === 7) ? 'Diaria'
        : (w.length === 5 && w.join(',')==='1,2,3,4,5' ? 'Entre semana' : `${w.length} días`);
    });
  }

  pickSound() {
    const ref = this.dialog.open(SoundDialogComponent, { data: { selected: this.sound }, 
      width: '350px',
      maxWidth: '92vw',
      panelClass: 'ailarm-dialog' });
    ref.afterClosed().subscribe((s?: string) => { if (s) this.sound = s; });
  }

  openTimePicker() {
    console.log('[timepicker] abrir');
    const ref = this.dialog.open<TimePickerDialogComponent, { defaultTime: string }, TimeResult>(
      TimePickerDialogComponent,
      { data: { defaultTime: this.time },
        panelClass: 'ailarm-dialog'}
    );
    ref.afterClosed().subscribe(res => { if (res?.time) this.time = res.time; });
  }

  save() {
    this.alarms.add({
      time: this.time,
      meridiem: this.meridiem,
      weekdays: this.weekdays,
      label: this.label || 'Alarma',
      sound: this.sound,
    });
    
    this.snackBar.openFromComponent(CustomSnackbarComponent, {
        data: {
          message: `Tu alarma para las ${this.time} ${this.meridiem} está configurada`,
          action: '',
          showCloseIcon: true,
        },
        duration: 5000,
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        panelClass: ['snackbar-success']
      });
    this.router.navigateByUrl('/alarms');
  }

  cancel() {
    this.router.navigateByUrl('/alarms');
  }

  get isFormComplete(): boolean {
  return Boolean(
    this.time &&
    this.frequencyLabel &&
    this.sound &&
    this.label?.trim().length // etiqueta no vacía ni solo espacios
  );
}
}
