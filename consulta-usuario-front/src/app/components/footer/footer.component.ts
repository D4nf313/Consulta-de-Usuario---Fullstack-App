import { Component} from '@angular/core';

@Component({
  selector: 'app-footer',
  templateUrl: './footer.component.html',
  styleUrls: ['./footer.component.css'],
  standalone: true,
})
export class FooterComponent {
  currentYear: number = new Date().getFullYear();
  
  footerLinks = [
    { name: 'Sitemap', url: '/sitemap' },
    { name: 'Seguridad', url: '/seguridad' },
    { name: 'Aviso legal', url: '/aviso-legal' },
    { name: 'Políticas', url: '/politicas' },
    { name: 'Reglamento de productos', url: '/reglamento-productos' }
  ];
}