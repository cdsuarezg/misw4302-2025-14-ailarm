
import { Component } from '@angular/core';
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

  constructor(
    private router: Router,
    private dialog: MatDialog,
    private snack: MatSnackBar,
    private alarms: AlarmsService
  ) {}

  pickFrequency() {
    const ref = this.dialog.open(FrequencyDialogComponent, { data: { selected: this.weekdays } });
    ref.afterClosed().subscribe((w: number[]|undefined) => {
      if (!w) return;
      this.weekdays = w;
      this.frequencyLabel =
        (w.length === 7) ? 'Diaria'
        : (w.length === 5 && w.join(',')==='1,2,3,4,5' ? 'Entre semana' : `${w.length} días`);
    });
  }

  pickSound() {
    const ref = this.dialog.open(SoundDialogComponent, { data: { selected: this.sound } });
    ref.afterClosed().subscribe((s?: string) => { if (s) this.sound = s; });
  }

  save() {
    this.alarms.add({
      time: this.time,
      meridiem: this.meridiem,
      weekdays: this.weekdays,
      label: this.label || 'Alarma',
      sound: this.sound,
    });
    this.snack.open('Tu alarma acaba de quedar configurada de forma exitosa.', 'OK', { duration: 2500 });
    this.router.navigateByUrl('/alarms');
  }

  cancel() {
    this.router.navigateByUrl('/alarms');
  }
}
