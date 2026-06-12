import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { UsuarioService } from '../../core/services/usuario';
import { FuncionarioService } from '../../core/services/funcionario';

@Component({
  selector: 'app-usuario-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './usuario-form.html',
  styleUrl: './usuario-form.scss',
})
export class UsuarioForm implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);

  private usuarioService = inject(UsuarioService);
  private funcionarioService = inject(FuncionarioService);

  modoEdicion = false;
  usuarioId!: number;

  mensajeExito = '';
  mensajeError = '';

  funcionarios: any[] = [];

  usuario: any = {
    username: '',
    password: '',
    email: '',
    activo: true,
    funcionario: null
  };

  ngOnInit(): void {
    this.cargarFuncionarios();

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicion = true;
      this.usuarioId = Number(id);
      this.cargarUsuario(this.usuarioId);
    }
  }

  cargarFuncionarios(): void {
    this.funcionarioService.listarTodos().subscribe({
      next: (data: any) => {
        this.funcionarios = data as any[];
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudieron cargar los funcionarios.';
        this.cdr.detectChanges();
      }
    });
  }

  cargarUsuario(id: number): void {
    this.usuarioService.buscarPorId(id).subscribe({
      next: (data: any) => {
        this.usuario = { ...this.usuario, ...data };
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudo cargar la información del usuario.';
        this.cdr.detectChanges();
      }
    });
  }

  guardar(): void {
    this.mensajeExito = '';
    this.mensajeError = '';

    if (this.modoEdicion) {
      this.usuarioService.actualizar(this.usuarioId, this.usuario).subscribe({
        next: () => {
          this.mensajeExito = 'Usuario actualizado correctamente.';
          this.cdr.detectChanges();

          setTimeout(() => {
            this.router.navigateByUrl('/usuarios');
          }, 2000);
        },
        error: (err) => {
          console.error(err);
          this.mensajeError = 'No se pudo actualizar el usuario.';
          this.cdr.detectChanges();
        }
      });
    } else {
      this.usuarioService.guardar(this.usuario).subscribe({
        next: () => {
          this.mensajeExito = 'Usuario registrado correctamente.';
          this.cdr.detectChanges();

          setTimeout(() => {
            this.router.navigateByUrl('/usuarios');
          }, 2000);
        },
        error: (err) => {
          console.error(err);
          this.mensajeError = 'No se pudo guardar el usuario.';
          this.cdr.detectChanges();
        }
      });
    }
  }

  cancelar(): void {
    this.router.navigateByUrl('/usuarios');
  }
}