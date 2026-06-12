import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/alertas';

  listarResumen() {
    return this.http.get<any[]>(`${this.apiUrl}/resumen`);
  }

  listarTodos() {
    return this.http.get<any[]>(this.apiUrl);
  }

  buscarPorId(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  guardar(alerta: any) {
    return this.http.post<any>(this.apiUrl, alerta);
  }

  actualizar(id: number, alerta: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, alerta);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
}