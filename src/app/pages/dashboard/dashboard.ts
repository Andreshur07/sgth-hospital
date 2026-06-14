import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DashboardService } from '../../core/services/dashboard';

@Component({
  selector: 'app-dashboard',
  imports: [CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard implements OnInit {

  private dashboardService = inject(DashboardService);
  private cdr = inject(ChangeDetectorRef);

  resumen: any = {};

  ngOnInit(): void {
    this.cargarDashboard();
  }

  cargarDashboard(): void {
    this.dashboardService.obtenerResumen().subscribe({
      next: (data: any) => {
        this.resumen = { ...data };
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error('Error cargando dashboard', err);
      }
    });
  }
}