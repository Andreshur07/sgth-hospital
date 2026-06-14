import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DocumentoService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/documentos';

  listarResumen() {
    return this.http.get<any[]>(`${this.apiUrl}/resumen`);
  }

  listarTodos() {
    return this.http.get<any[]>(this.apiUrl);
  }

  buscarPorId(id: number) {
    return this.http.get<any>(`${this.apiUrl}/${id}`);
  }

  guardar(documento: any) {
    return this.http.post<any>(this.apiUrl, documento);
  }

  actualizar(id: number, documento: any) {
    return this.http.put<any>(`${this.apiUrl}/${id}`, documento);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }
  listarPorFuncionario(funcionarioId: number) {
    return this.http.get<any[]>(`${this.apiUrl}/funcionario/${funcionarioId}`);
  }

  subirDocumento(formData: FormData) {
    return this.http.post<any>(`${this.apiUrl}/subir`, formData);
  }

  verDocumento(id: number) {
  return `${this.apiUrl}/ver/${id}`;
  }
}