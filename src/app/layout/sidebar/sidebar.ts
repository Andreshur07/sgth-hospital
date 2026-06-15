import { Component, OnInit, inject } from '@angular/core';
import { RouterLink } from '@angular/router';

import { Auth } from '../../core/services/auth';

@Component({
  selector: 'app-sidebar',
  imports: [RouterLink],
  templateUrl: './sidebar.html',
  styleUrl: './sidebar.scss',
})
export class Sidebar implements OnInit {

  private auth = inject(Auth);

  rol = '';

  ngOnInit(): void {
    this.rol = this.auth.obtenerRol();
  }

  esAdministrador(): boolean {
    return this.rol === 'ADMINISTRADOR';
  }

  esTalentoHumano(): boolean {
    return this.rol === 'TALENTO_HUMANO';
  }

  esCoordinador(): boolean {
    return this.rol === 'COORDINADOR';
  }

  esAuditor(): boolean {
    return this.rol === 'AUDITOR';
  }

  esConsulta(): boolean {
    return this.rol === 'CONSULTA';
  }
}