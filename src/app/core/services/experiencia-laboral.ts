import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ExperienciaLaboralService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/experiencias-laborales';

  listarPorFuncionario(funcionarioId: number) {
    return this.http.get<any[]>(`${this.apiUrl}/funcionario/${funcionarioId}`);
  }

  subirExperiencia(formData: FormData) {
    return this.http.post<any>(`${this.apiUrl}/subir`, formData);
  }

  eliminar(id: number) {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  verExperiencia(id: number) {
    return `${this.apiUrl}/ver/${id}`;
  }
}