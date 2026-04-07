import { Component } from '@angular/core';
import { RouterLink, RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink],
  template: `
    <header class="topbar">
      <div class="topbar-inner">
        <div>
          <div class="brand">Desafio Fullstack</div>
          <div class="brand-subtitle">Gestão de benefícios e transferências</div>
        </div>

        <nav class="actions">
          <a routerLink="/" class="btn btn-secondary">Início</a>
          <a routerLink="/novo" class="btn btn-primary">Novo benefício</a>
          <a routerLink="/transferir" class="btn btn-success">Transferir</a>
        </nav>
      </div>
    </header>

    <main class="container">
      <router-outlet></router-outlet>
    </main>
  `,
  styleUrl: './app.component.scss'
})
export class AppComponent {
  title = 'app';
}
