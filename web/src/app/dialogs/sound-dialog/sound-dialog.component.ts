import { Component, Inject } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-sound-dialog',
  standalone: true,
  imports: [CommonModule, FormsModule, MatDialogModule, MatRadioModule, MatButtonModule],
  templateUrl: './sound-dialog.component.html',
  styleUrls: ['./sound-dialog.component.scss']
})

export class SoundDialogComponent {
  sounds = ['Sound 1','Sound 2','Sound 3','Sound 4','Sound 5','Sound 6','Sound 7'];
  selected: string;

  constructor(
    @Inject(MAT_DIALOG_DATA) data: { selected?: string },
    private ref: MatDialogRef<SoundDialogComponent>
  ) {
    this.selected = data?.selected || 'Sound 1';
  }

  /** Permite seleccionar la fila completa */
  onRowClick(s: string) { this.selected = s; }

  confirm() { this.ref.close(this.selected); }
}