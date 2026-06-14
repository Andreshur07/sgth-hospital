import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { UsuarioService } from '../../core/services/usuario';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuarios',
  imports: [CommonModule, FormsModule],
  templateUrl: './usuarios.html',
  styleUrl: './usuarios.scss',
})
export class Usuarios implements OnInit {

  private usuarioService = inject(UsuarioService);
  private cdr = inject(ChangeDetectorRef);
  private router = inject(Router);

  usuarios: any[] = [];
  terminoBusqueda = '';

  mensajeExito = '';
  mensajeError = '';

  ngOnInit(): void {
    this.cargarUsuarios();
  }

  cargarUsuarios(): void {
    this.usuarioService.listarResumen().subscribe({
      next: (data: any[]) => {
        this.usuarios = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudieron cargar los usuarios.';
        this.cdr.detectChanges();
      }
    });
  }

  usuariosFiltrados(): any[] {
    const termino = this.terminoBusqueda.toLowerCase().trim();

    if (!termino) {
      return this.usuarios;
    }

    return this.usuarios.filter(usuario =>
      String(usuario.username ?? '').toLowerCase().includes(termino) ||
      String(usuario.email ?? '').toLowerCase().includes(termino) ||
      String(usuario.nombreFuncionario ?? '').toLowerCase().includes(termino) ||
      String(usuario.activo ? 'activo' : 'inactivo').toLowerCase().includes(termino)
    );
  }

  nuevoUsuario(): void {
    this.router.navigate(['/usuarios/nuevo']);
  }

  editarUsuario(id: number): void {
    this.router.navigate(['/usuarios/editar', id]);
  }

  inactivarUsuario(id: number): void {
    const confirmar = confirm('¿Está seguro de inactivar este usuario?');

    if (!confirmar) {
      return;
    }

    this.usuarioService.eliminar(id).subscribe({
      next: () => {
        this.mensajeExito = 'Usuario inactivado correctamente.';
        this.cargarUsuarios();

        setTimeout(() => {
          this.mensajeExito = '';
          this.cdr.detectChanges();
        }, 3000);
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudo inactivar el usuario.';
        this.cdr.detectChanges();
      }
    });
  }
}