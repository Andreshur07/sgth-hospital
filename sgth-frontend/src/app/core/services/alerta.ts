import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AlertaService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/alertas';

  listarResumen() {
    return this.http.get(`${this.apiUrl}/resumen`);
  }
}