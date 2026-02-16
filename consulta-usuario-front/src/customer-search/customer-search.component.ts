import { Component, signal } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

// PrimeNG Imports
import { CardModule } from 'primeng/card';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { MessageModule } from 'primeng/message';
import { SkeletonModule } from 'primeng/skeleton';
import { DividerModule } from 'primeng/divider';
import { TagModule } from 'primeng/tag';
import { AvatarModule } from 'primeng/avatar';
import { DropdownModule } from 'primeng/dropdown';
import { CustomerService } from './services/customer.service';

// Servicio

// Interfaz para el frontend
interface Customer {
  customerId: string;
  firstName: string;
  lastName: string;
  nationalityId: string;
  genderId: string;
  identityDocument: string;
  identityDocumentNumber: string;
  identityDocumentTypeId: number;
  // Campos derivados (mapeados)
  nationalityName?: string;
  genderName?: string;
}
interface DocumentType {
  id: number;
  name: string;
  abbreviation: string;
}

@Component({
  selector: 'app-customer-search',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    CardModule,
    InputTextModule,
    ButtonModule,
    ProgressSpinnerModule,
    MessageModule,
    SkeletonModule,
    DividerModule,
    TagModule,
    AvatarModule,
    DropdownModule,
  ],
  templateUrl: './customer-search.component.html',
  styleUrls: ['./customer-search.component.css'],
})
export class CustomerSearchComponent {
  customerId = '';
  selectedDocumentType: DocumentType | null = null;

  // Signals
  customer = signal<Customer | null>(null);
  loading = signal(false);
  error = signal<string | null>(null);
  hasSearched = signal(false);
  skeletonItems = [1, 2, 3, 4, 5];

  documentTypes: DocumentType[] = [
    { id: 1, name: 'Cédula de Ciudadanía', abbreviation: 'CC' },
    { id: 2, name: 'Pasaporte', abbreviation: 'PASSPORT' },
    { id: 3, name: 'Tarjeta de Identidad', abbreviation: 'TI' },
    { id: 4, name: 'Cédula de Extranjería', abbreviation: 'CE' },
  ];

  // Diccionarios para mapear códigos a nombres
  nationalityMap: { [key: string]: string } = {
    '57': 'Colombia',
    '1': 'Estados Unidos',
    '44': 'Reino Unido',
    '33': 'Francia',
    '39': 'Italia',
    '52': 'México',
    '55': 'Brasil',
    //
  };

  genderMap: { [key: string]: string } = {
    '1': 'Masculino',
    '2': 'Femenino',
    '3': 'Otro',
    // 
  };

  constructor(private customerService: CustomerService) {}

  searchCustomer() {
    // Validaciones
    if (!this.selectedDocumentType) {
      return;
    }

    if (!this.customerId.trim()) {
      return;
    }

    this.loading.set(true);
    this.error.set(null);
    this.customer.set(null);
    this.hasSearched.set(true);

    console.log('Buscando:', {
      documentTypeId: this.selectedDocumentType.id,
      documentNumber: this.customerId.trim(),
    });

    this.customerService
      .searchCustomerByDocument(
        this.selectedDocumentType.id,
        this.customerId.trim(),
      )
      .subscribe({
        next: (data: any) => {
          console.log('Respuesta:', data);
          const mappedCustomer = this.mapBackendResponse(data);
          this.customer.set(mappedCustomer);
          this.loading.set(false);
        },
        error: (err: any) => {
          console.log('Error:', err);
          this.error.set(err.message || 'Error al consultar el cliente');
          this.loading.set(false);
        },
      });
  }

  private mapBackendResponse(data: any): Customer {
    console.log('Mapeando datos del backend:', data);

    return {
      // Mapeo directo
      customerId: data.customerId || '',
      firstName: data.firstName || 'No especificado',
      lastName: data.lastName || 'No especificado',
      nationalityId: data.nationalityId || '',
      genderId: data.genderId || '',
      identityDocument: data.identityDocument || '',
      identityDocumentNumber: data.identityDocumentNumber || '',
      identityDocumentTypeId: data.identityDocumentTypeId || 0,

      // Campos derivados (mapeados)
      nationalityName: this.getNationalityName(data.nationalityId),
      genderName: this.getGenderName(data.genderId),
    };
  }

  // Método para obtener nombre de nacionalidad
  getNationalityName(nationalityId: string): string {
    return (
      this.nationalityMap[nationalityId] || `Nacionalidad ${nationalityId} || 'Otra nacionalidad'`
    );
  }

  // Método para obtener nombre de género
  getGenderName(genderId: string): string {
    return this.genderMap[genderId] || `Género ${genderId}`;
  }

  // Getters y métodos auxiliares
  get canSearch(): boolean {
    return !!this.selectedDocumentType && !!this.customerId.trim();
  }

  getInputPlaceholder(): string {
    if (!this.selectedDocumentType) {
      return 'Primero seleccione tipo de documento';
    }

    switch (this.selectedDocumentType.id) {
      case 1: // CC
        return 'Ingrese número de cédula (ej: 10000001)';
      case 2: // PASSPORT
        return 'Ingrese número de pasaporte (ej: A1234567)';
      case 3: // TI
        return 'Ingrese número de tarjeta de identidad (ej: 90000001)';
      case 4: // CE
        return 'Ingrese número de cédula de extranjería (ej: E12345)';
      default:
        return 'Ingrese número de identificación';
    }
  }

  getDocumentTypeAbbr(): string {
    return this.selectedDocumentType?.abbreviation || 'ID';
  }

  getDocumentTypeName(typeId: number): string {
    const documentType = this.documentTypes.find((dt) => dt.id === typeId);
    return documentType ? documentType.name : 'Desconocido';
  }

  // Actualizar getInitials para usar firstName y lastName
  getInitials(customer: Customer): string {
    return `${customer.firstName.charAt(0)}${customer.lastName.charAt(0)}`.toUpperCase();
  }
}
