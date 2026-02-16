import { Injectable } from '@angular/core';
import {
  HttpClient,
  HttpErrorResponse,
  HttpParams,
} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

export interface Customer {
  id?: string;
  primerNombre?: string;
  segundoNombre?: string;
  primerApellido?: string;
  segundoApellido?: string;
  email?: string;
  telefono?: string;
  direccion?: string;
  ciudad?: string;
  fechaRegistro?: string;
  estado?: string;
  // Agrega más campos según la respuesta de tu backend
}

export interface ErrorResponse {
  code: string;
  message: string;
  timestamp: string;
}

@Injectable({
  providedIn: 'root',
})
export class CustomerService {
  private apiUrl = 'http://localhost:8080/api/v1/customers'; // Ajusta el puerto según tu backend

  constructor(private http: HttpClient) {}

  /**
   * Busca un cliente por tipo y número de documento
   * @param documentTypeId - ID del tipo de documento
   * @param documentNumber - Número de documento
   * @returns Observable con los datos del cliente
   */
  searchCustomerByDocument(
    documentTypeId: number,
    documentNumber: string,
  ): Observable<Customer> {
    const params = new HttpParams()
      .set('identityDocumentTypeId', documentTypeId.toString())
      .set('identityDocumentNumber', documentNumber);

    return this.http
      .get<Customer>(`${this.apiUrl}/search/by-document`, { params })
      .pipe(catchError(this.handleError));
  }

  /**
   * Manejo centralizado de errores
   */
  private handleError(error: HttpErrorResponse) {
    console.log('Error completo:', error); // Para debug
    console.log('Error body:', error.error); // Para debug

    let errorMessage = 'Error desconocido';
    let errorCode = 'UNKNOWN_ERROR';

    if (error.status === 0) {
      // Error de conexión o CORS
      errorMessage =
        'No se pudo conectar con el servidor. Verifique su conexión.';
      errorCode = 'CONNECTION_ERROR';
    } else if (error.error && error.error.message) {
      // El backend devolvió un ErrorResponse con estructura
      errorCode = error.error.code || 'BACKEND_ERROR';
      errorMessage = error.error.message;
    } else {
      // Errores HTTP genéricos sin estructura personalizada
      switch (error.status) {
        case 400:
          errorMessage = 'Solicitud inválida. Verifique los datos ingresados';
          errorCode = 'BAD_REQUEST';
          break;
        case 404:
          errorMessage = 'Cliente no encontrado';
          errorCode = 'NOT_FOUND';
          break;
        case 500:
          errorMessage = 'Error interno del servidor';
          errorCode = 'INTERNAL_ERROR';
          break;
        default:
          errorMessage = `Error ${error.status}: ${error.statusText}`;
          errorCode = `HTTP_${error.status}`;
      }
    }

    // Retornar un objeto estructurado
    return throwError(() => ({
      code: errorCode,
      message: errorMessage,
      status: error.status,
      timestamp: new Date().toISOString(),
    }));
  }
}
