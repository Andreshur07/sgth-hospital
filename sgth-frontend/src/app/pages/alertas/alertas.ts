import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AlertaService } from '../../core/services/alerta';

@Component({
  selector: 'app-alertas',
  imports: [CommonModule, FormsModule],
  templateUrl: './alertas.html',
  styleUrl: './alertas.scss',
})
export class Alertas implements OnInit {
  private alertaService = inject(AlertaService);
  private cdr = inject(ChangeDetectorRef);

  alertas: any[] = [];
  terminoBusqueda: string = '';

  mensajeExito = '';
  mensajeError = '';

  ngOnInit(): void {
    this.cargarAlertas();
  }

  cargarAlertas(): void {
    this.alertaService.listarResumen().subscribe({
      next: (data: any[]) => {
        this.alertas = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudieron cargar las alertas.';
        this.cdr.detectChanges();
      }
    });
  }

  alertasFiltradas(): any[] {
    const termino = this.terminoBusqueda.toLowerCase().trim();

    if (!termino) {
      return this.alertas;
    }

    return this.alertas.filter(alerta =>
      String(alerta.funcionario ?? '').toLowerCase().includes(termino) ||
      String(alerta.tipo ?? '').toLowerCase().includes(termino) ||
      String(alerta.fechaAlerta ?? '').toLowerCase().includes(termino) ||
      String(alerta.estado ?? '').toLowerCase().includes(termino)
    );
  }

  marcarAtendida(id: number): void {
    this.alertaService.buscarPorId(id).subscribe({
      next: (alerta: any) => {
        alerta.estado = 'ATENDIDA';

        this.alertaService.actualizar(id, alerta).subscribe({
          next: () => {
            this.mensajeExito = 'Alerta marcada como atendida.';
            this.cargarAlertas();

            setTimeout(() => {
              this.mensajeExito = '';
              this.cdr.detectChanges();
            }, 3000);
          },
          error: (err) => {
            console.error(err);
            this.mensajeError = 'No se pudo actualizar la alerta.';
            this.cdr.detectChanges();
          }
        });
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudo consultar la alerta.';
        this.cdr.detectChanges();
      }
    });
  }
}