import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CustomerSearchComponent } from '../customer-search/customer-search.component';
import { HeaderComponent } from './components/header/header.component';
import { FooterComponent } from './components/footer/footer.component';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [

    RouterOutlet,
    HeaderComponent,
    FooterComponent,
    CustomerSearchComponent,
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'consulta-usuario-front';
}
