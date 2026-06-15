import { Component, OnInit, inject } from '@angular/core';
import { Router } from '@angular/router';

import { Auth } from '../../core/services/auth';

@Component({
  selector: 'app-navbar',
  imports: [],
  templateUrl: './navbar.html',
  styleUrl: './navbar.scss',
})
export class Navbar implements OnInit {

  private auth = inject(Auth);
  private router = inject(Router);

  nombreUsuario = '';
  nombreRol = '';

  ngOnInit(): void {

    const usuario = this.auth.obtenerUsuario();

    if (usuario) {
      this.nombreUsuario =
        usuario.nombreFuncionario ||
        usuario.username;

      this.nombreRol =
        usuario.nombreRol || '';
    }
  }

  cerrarSesion(): void {

    this.auth.cerrarSesion();

    this.router.navigateByUrl('/login');
  }
}