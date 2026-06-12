import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/usuarios';

  listarResumen() {
    return this.http.get<any[]>(`${this.apiUrl}/resumen`);
  }

  listarTodos() {
    return this.http.get<any[]>(this.apiUrl);
  }

  buscarPorId(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  guardar(usuario: any) {
    return this.http.post<any>(this.apiUrl, usuario);
  }

  actualizar(id: number, usuario: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, usuario);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}