import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';

export interface SnackBarData {
  message: string;
  action?: string;
  showCloseIcon?: boolean;
}

@Component({
  selector: 'app-custom-snackbar',
  standalone: true,
  imports: [CommonModule, MatButtonModule, MatIconModule],
  template: `
    <div class="custom-snackbar">
      <div class="custom-snackbar__content">
        <span class="custom-snackbar__message">{{ data.message }}</span>
        
        <div class="custom-snackbar__actions">
          <button 
            *ngIf="data.action" 
            mat-button 
            class="custom-snackbar__action-btn"
            (click)="onAction()">
            {{ data.action }}
          </button>
          
          <button 
            *ngIf="data.showCloseIcon"
            mat-icon-button 
            class="custom-snackbar__close-btn"
            (click)="onClose()"
            aria-label="Cerrar">
            <mat-icon>close</mat-icon>
          </button>
        </div>
      </div>
    </div>
  `,
  styles: [`
    .custom-snackbar {
      display: flex;
      align-items: center;
      width: 100%;

      &__content {
        display: flex;
        align-items: center;
        justify-content: space-between;
        width: 100%;
        gap: 1rem;
      }

      &__message {
        flex: 1;
        color: inherit;
        font-size: 0.875rem;
        line-height: 1.25rem;
      }

      &__actions {
        display: flex;
        align-items: center;
        gap: 0.5rem;
      }

      &__action-btn {
        color: inherit !important;
        font-weight: 500;
        text-transform: uppercase;
        font-size: 0.75rem;
        padding: 0 8px;
        min-width: auto;
        height: 32px;
        border: 1px solid currentColor;
        border-radius: 4px;

        &:hover {
          background-color: rgba(255, 255, 255, 0.1);
        }
      }

      &__close-btn {
        color: inherit !important;
        width: 32px;
        height: 32px;
        padding: 4px;

        mat-icon {
          font-size: 18px;
          width: 18px;
          height: 18px;
          color: white;
        }

        &:hover {
          background-color: rgba(255, 255, 255, 0.1);
        }
      }
    }
  `]
})
export class CustomSnackbarComponent {
  constructor(
    public snackBarRef: MatSnackBarRef<CustomSnackbarComponent>,
    @Inject(MAT_SNACK_BAR_DATA) public data: SnackBarData
  ) {}

  onAction(): void {
    this.snackBarRef.dismissWithAction();
  }

  onClose(): void {
    this.snackBarRef.dismiss();
  }
}