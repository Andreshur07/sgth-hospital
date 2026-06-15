import { Component, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { Auth } from '../../core/services/auth';

@Component({
  selector: 'app-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.scss',
})
export class Login {

  private auth = inject(Auth);
  private router = inject(Router);
  private cdr = inject(ChangeDetectorRef);

  username = '';
  password = '';

  mensajeError = '';

  ingresar(): void {
    this.mensajeError = '';

    if (!this.username) {
      this.mensajeError = 'Ingrese el usuario.';
      return;
    }

    this.auth.login({
      username: this.username,
      password: this.password
    }).subscribe({
      next: (usuario: any) => {
        this.auth.guardarUsuario(usuario);
        this.router.navigateByUrl('/dashboard');
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'Usuario o contraseña incorrectos.';
        this.cdr.detectChanges();
      }
    });
  }
}