import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { HistorialEstadoService } from '../../core/services/historial-estado';

@Component({
  selector: 'app-historial-estados',
  imports: [CommonModule],
  templateUrl: './historial-estados.html',
  styleUrl: './historial-estados.scss',
})
export class HistorialEstados implements OnInit {
  private historialService = inject(HistorialEstadoService);
  private cdr = inject(ChangeDetectorRef);

  historial: any[] = [];

  ngOnInit(): void {
    this.historialService.listarResumen().subscribe({
      next: (data: any) => {
        this.historial = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }
}