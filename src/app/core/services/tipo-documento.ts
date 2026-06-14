import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TipoDocumentoService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/tipos-documento';

  listarTodos() {
    return this.http.get<any[]>(this.apiUrl);
  }

  listarObligatorios() {
    return this.http.get<any[]>(`${this.apiUrl}/obligatorios`);
  }
}