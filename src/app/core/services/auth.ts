import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class Auth {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/auth';

  login(credenciales: any) {
    return this.http.post<any>(`${this.apiUrl}/login`, credenciales);
  }

  guardarUsuario(usuario: any): void {
    localStorage.setItem('usuario', JSON.stringify(usuario));
  }

  obtenerUsuario(): any {
    const usuario = localStorage.getItem('usuario');
    return usuario ? JSON.parse(usuario) : null;
  }

  obtenerRol(): string {
    const usuario = this.obtenerUsuario();
    return usuario?.nombreRol || '';
  }

  estaLogueado(): boolean {
    return !!this.obtenerUsuario();
  }

  cerrarSesion(): void {
    localStorage.removeItem('usuario');
  }
}