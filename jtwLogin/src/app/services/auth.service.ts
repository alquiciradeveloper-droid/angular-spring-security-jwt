import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { AuthRequest, AuthResponse } from '../models/auth.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly API_URL = 'http://localhost:8080/api/v1/auth';
  private tokenKey = 'access_token';
  
  // OWASP: Uso de señales o estado reactivo para evitar persistencia insegura si aplica
  currentUser = signal<string | null>(null);

  constructor(private http: HttpClient) {}

  login(credentials: AuthRequest): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.API_URL}/login`, credentials).pipe(
      tap(response => {
        // Para máxima seguridad OWASP, se recomienda almacenar el Access Token en memoria
        // y usar cookies HttpOnly para Refresh Tokens. Si se usa sessionStorage/localStorage:
        sessionStorage.setItem(this.tokenKey, response.accessToken);
      })
    );
  }

  getToken(): string | null {
    return sessionStorage.getItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    const token = this.getToken();
    return !!token; // Idealmente verificar expiración decodificando el payload
  }

  logout(): void {
    sessionStorage.removeItem(this.tokenKey);
  }
}