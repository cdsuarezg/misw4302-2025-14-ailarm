import { CommonModule } from '@angular/common';
import { Component, ElementRef, Inject, ViewChild, HostListener } from '@angular/core';
import { MatDialogModule, MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatButtonModule } from '@angular/material/button';

export interface TimeResult { time: string; } // "07:00 AM"

@Component({
  selector: 'app-time-picker-dialog',
  standalone: true,
  imports: [CommonModule, MatDialogModule, MatButtonModule],
  templateUrl: 'time-picker-dialog.component.html',
  styleUrls: ['time-picker-dialog.component.scss']
})
export class TimePickerDialogComponent {
  // Estado
  hour = 7;        // 1..12
  minute = 0;      // 0..59 (saltos de 5 por clic)
  period: 'AM' | 'PM' = 'AM';
  picking: 'hour' | 'minute' = 'hour';

  @ViewChild('clock', { static: true }) clockRef!: ElementRef<HTMLDivElement>;
  private dragging = false;

  constructor(
    @Inject(MAT_DIALOG_DATA) data: { defaultTime?: string },
    private ref: MatDialogRef<TimePickerDialogComponent, TimeResult>
  ) {
    // Parse "07:30 PM" si viene
    const m = (data?.defaultTime || '').match(/^(\d{1,2}):(\d{2})\s*(AM|PM)$/i);
    if (m) {
      this.hour = Math.min(12, Math.max(1, +m[1]));
      this.minute = Math.min(59, Math.max(0, +m[2]));
      this.period = (m[3].toUpperCase() as 'AM'|'PM');
    }
  }

  // ----- Display y acciones superiores -----
  selectPicking(mode: 'hour'|'minute') { this.picking = mode; }

  setPeriod(p: 'AM'|'PM') { this.period = p; }

  get displayHour(): string { return String(this.hour).padStart(2, '0'); }
  get displayMinute(): string { return String(this.minute).padStart(2, '0'); }

  private angleFrom12(ev: MouseEvent | TouchEvent): number {
  const rect = this.clockRef.nativeElement.getBoundingClientRect();
  const cx = rect.left + rect.width / 2;
  const cy = rect.top + rect.height / 2;

  let x: number, y: number;
  if (ev instanceof MouseEvent) { x = ev.clientX; y = ev.clientY; }
  else { x = ev.touches[0].clientX; y = ev.touches[0].clientY; }

  const dx = x - cx;
  const dy = (cy - y); // 👈 invertir Y (DOM → matemático)

  const theta = Math.atan2(dy, dx) * 180 / Math.PI; // -180..180, 0°=3 en punto
  // Llevamos el 0° a las 12 y lo hacemos horario: 12=0°, 3=90°, 6=180°, 9=270°
  const from12 = (90 - theta + 360) % 360;
  return from12;
}
  // ----- Geometría del reloj -----
  private angleToHour(angleFrom12: number): number {
   const idx = Math.round(angleFrom12 / 30) % 12; // 30° por hora
   return idx === 0 ? 12 : idx;
}

  private angleToMinute(angleFrom12: number): number {
  let m = Math.round(angleFrom12 / 6) % 60;      // 6° por minuto
  m = Math.round(m / 5) * 5;                      // opcional: saltos de 5
  return (m + 60) % 60;
}

private updateFromPointer(ev: MouseEvent | TouchEvent) {
  const ang = this.angleFrom12(ev);
  if (this.picking === 'hour') this.hour = this.angleToHour(ang);
  else this.minute = this.angleToMinute(ang);
}

  @HostListener('document:mousemove', ['$event'])
  onMove(e: MouseEvent) { if (this.dragging) this.updateFromPointer(e); }

  @HostListener('document:touchmove', ['$event'])
  onTouchMove(e: TouchEvent) { if (this.dragging) this.updateFromPointer(e); }

  startDrag(e: MouseEvent | TouchEvent) { this.dragging = true; this.updateFromPointer(e); }
  endDrag() { this.dragging = false; }

  onClockClick(e: MouseEvent) { this.updateFromPointer(e); if (this.picking === 'hour') this.picking = 'minute'; }

  // Posición del “puntero”
  get handAngle(): number {
    if (this.picking === 'hour') {
      const idx = this.hour % 12; // 0..11 (12->0)
      return ((idx === 0 ? 12 : idx) * 30) - 90;
    } else {
      return (this.minute * 6) - 90;
    }
  }

  // ----- Confirmar / Cancelar -----
  cancel() { this.ref.close(); }
  ok() {
    const time = `${this.displayHour}:${String(this.minute).padStart(2, '0')} ${this.period}`;
    this.ref.close({ time });
  }
}
