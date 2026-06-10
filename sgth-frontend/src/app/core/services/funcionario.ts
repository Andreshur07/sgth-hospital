import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
})
export class FuncionarioService {

    private http = inject(HttpClient);

    private apiUrl = 'http://localhost:8080/api/funcionarios';

    listarResumen() {
        return this.http.get(`${this.apiUrl}/resumen`);
    }

    listarTodos() {
        return this.http.get(this.apiUrl);
    }

    buscarPorId(id: number) {
        return this.http.get(`${this.apiUrl}/${id}`);
    }

    guardar(funcionario: any) {
        return this.http.post(this.apiUrl, funcionario);
    }

    actualizar(id: number, funcionario: any) {
        return this.http.put(`${this.apiUrl}/${id}`, funcionario);
    }

    eliminar(id: number) {
        return this.http.delete(`${this.apiUrl}/${id}`);
    }
}