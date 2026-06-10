import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AlertaService } from '../../core/services/alerta';

@Component({
  selector: 'app-alertas',
  imports: [CommonModule],
  templateUrl: './alertas.html',
  styleUrl: './alertas.scss',
})
export class Alertas implements OnInit {
  private alertaService = inject(AlertaService);
  private cdr = inject(ChangeDetectorRef);

  alertas: any[] = [];

  ngOnInit(): void {
    this.alertaService.listarResumen().subscribe({
      next: (data: any) => {
        this.alertas = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }
}