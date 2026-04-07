import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Beneficio } from '../models/beneficio';
import { BeneficioRequest } from '../models/beneficio-request';
import { TransferenciaRequest } from '../models/transferencia-request';

@Injectable({
  providedIn: 'root'
})
export class BeneficioService {

  private api = 'http://localhost:8080/api/beneficios';

   constructor(private http: HttpClient) {}

   listar(): Observable<Beneficio[]> {
    return this.http.get<Beneficio[]>(this.api);
  }

  buscar(id: number): Observable<Beneficio> {
    return this.http.get<Beneficio>(`${this.api}/${id}`);
  }

  criar(payload: BeneficioRequest): Observable<Beneficio> {
    return this.http.post<Beneficio>(this.api, payload);
  }

  atualizar(id: number, payload: BeneficioRequest): Observable<Beneficio> {
    return this.http.put<Beneficio>(`${this.api}/${id}`, payload);
  }

  remover(id: number): Observable<void> {
    return this.http.delete<void>(`${this.api}/${id}`);
  }

  transferir(payload: TransferenciaRequest): Observable<void> {
    return this.http.post<void>(`${this.api}/transferencias`, payload);
  }
  
}
