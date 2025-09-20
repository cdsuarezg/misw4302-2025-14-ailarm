import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-alarm-with-ai',
  templateUrl: './alarm-with-ai.component.html',
  styleUrls: ['./alarm-with-ai.component.scss'],
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatIconModule, MatProgressSpinnerModule]
})
export class AlarmWithAiComponent implements OnInit {
  private router = inject(Router);
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
      this.router.navigate(['/alarms']);
    }, 6000);
  }
}
