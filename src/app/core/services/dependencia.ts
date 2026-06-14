import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DependenciaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/dependencias';

  listarTodos() {
    return this.http.get<any[]>(this.apiUrl);
  }
}