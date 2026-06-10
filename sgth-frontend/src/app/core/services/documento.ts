import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DocumentoService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/documentos';

  listarResumen() {
    return this.http.get(`${this.apiUrl}/resumen`);
  }
}