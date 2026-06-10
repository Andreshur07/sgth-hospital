import {
  Component,
  OnInit,
  inject,
  ChangeDetectorRef
} from '@angular/core';

import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FuncionarioService } from '../../core/services/funcionario';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-funcionarios',
  imports: [CommonModule, FormsModule],
  templateUrl: './funcionarios.html',
  styleUrl: './funcionarios.scss',
})
export class Funcionarios implements OnInit {

  private funcionarioService = inject(FuncionarioService);
  private router = inject(Router);
  private cdr = inject(ChangeDetectorRef);

  terminoBusqueda: string = '';

  funcionarios: any[] = [];

  ngOnInit(): void {
    this.cargarFuncionarios();
  }

  cargarFuncionarios(): void {
    this.funcionarioService.listarResumen().subscribe({
      next: (data: any) => {
        console.log('FUNCIONARIOS:', data);

        this.funcionarios = [...data];

        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
      }
    });
  }

  nuevoFuncionario(): void {
    this.router.navigate(['/funcionarios/nuevo']);
  }

  editarFuncionario(id: number): void {
    this.router.navigate(['/funcionarios/editar', id]);
  }

  inactivarFuncionario(id: number): void {

    const confirmar = confirm(
      '¿Está seguro de inactivar este funcionario?'
    );

    if (!confirmar) {
      return;
    }

    this.funcionarioService.eliminar(id).subscribe({
      next: () => {

        alert('Funcionario inactivado correctamente.');

        this.cargarFuncionarios();
      },
      error: (err) => {
        console.error(err);
        alert('No se pudo inactivar el funcionario.');
      }
    });
  }

  funcionariosFiltrados(): any[] {
    const termino = this.terminoBusqueda.toLowerCase().trim();

    if (!termino) {
      return this.funcionarios;
    }

    return this.funcionarios.filter(funcionario =>
      funcionario.numeroDocumento?.toLowerCase().includes(termino) ||
      funcionario.nombreCompleto?.toLowerCase().includes(termino) ||
      funcionario.cargo?.toLowerCase().includes(termino) ||
      funcionario.dependencia?.toLowerCase().includes(termino) ||
      funcionario.estado?.toLowerCase().includes(termino)
    );
  }
}