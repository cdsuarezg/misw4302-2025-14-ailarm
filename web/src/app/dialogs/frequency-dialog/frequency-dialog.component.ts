import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-frequency-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatCheckboxModule, MatButtonModule],
  templateUrl: './frequency-dialog.component.html',
  styleUrls: ['./frequency-dialog.component.scss']
})
export class FrequencyDialogComponent {
  weekdays = ['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'];
  selected: Set<number>;

  constructor(
    @Inject(MAT_DIALOG_DATA) data: { selected: number[] },
    private ref: MatDialogRef<FrequencyDialogComponent>
  ) {
    this.selected = new Set(data?.selected ?? []);
  }

  toggle(i: number) {
    this.selected.has(i) ? this.selected.delete(i) : this.selected.add(i);
  }

  confirm() {
    this.ref.close(Array.from(this.selected).sort());
  }

  all() {
    this.selected = new Set([0,1,2,3,4,5,6]);
  }
}
