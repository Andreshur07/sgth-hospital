import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/dashboard';

  obtenerResumen() {
    return this.http.get(`${this.apiUrl}/resumen`);
  }
}