import { Routes } from '@angular/router';
import { CustomerSearchComponent } from '../customer-search/customer-search.component';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'buscar-cliente',
    pathMatch: 'full'
  },
  {
    path: 'buscar-cliente',
    component: CustomerSearchComponent,
    title: 'Consulta de Clientes'
  },
  {
    path: '**',
    redirectTo: 'buscar-cliente'
  }
];