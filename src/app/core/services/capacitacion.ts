import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CapacitacionService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/capacitaciones';

  listarPorFuncionario(funcionarioId: number) {
    return this.http.get<any[]>(`${this.apiUrl}/funcionario/${funcionarioId}`);
  }

  subirCapacitacion(formData: FormData) {
    return this.http.post<any>(`${this.apiUrl}/subir`, formData);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  verCapacitacion(id: number) {
    return `${this.apiUrl}/ver/${id}`;
  }
}