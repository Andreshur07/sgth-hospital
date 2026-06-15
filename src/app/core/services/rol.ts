import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RolService {

  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/roles';

  listarTodos() {
    return this.http.get<any[]>(this.apiUrl);
  }
}