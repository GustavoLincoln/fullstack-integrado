import { CommonModule, CurrencyPipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { Beneficio } from '../../models/beneficio';
import { BeneficioService } from '../../services/beneficio.service';

@Component({
  selector: 'app-beneficio-list',
  standalone: true,
  imports: [CommonModule, RouterModule, CurrencyPipe],
  templateUrl: './beneficio-list.component.html',
  styleUrl: './beneficio-list.component.scss'
})

export class BeneficioListComponent implements OnInit {
  beneficios: Beneficio[] = [];
  erro = '';
  carregando = false;

  constructor(
    private service: BeneficioService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.carregando = true;
    this.erro = '';

    this.service.listar().subscribe({
      next: (data) => {
        this.beneficios = data;
        this.carregando = false;
      },
      error: () => {
        this.erro = 'Erro ao carregar benefícios.';
        this.carregando = false;
      }
    });
  }

  excluir(id: number): void {
    if (!confirm('Deseja remover este benefício?')) return;

    this.carregando = true;

    this.service.remover(id).subscribe({
      next: () => this.carregar(),
      error: () => {
        this.erro = 'Erro ao remover benefício.';
        this.carregando = false;
      }
    });
  }

  totalAtivos(): number {
    return this.beneficios.filter(b => b.ativo).length;
  }

  totalInativos(): number {
    return this.beneficios.filter(b => !b.ativo).length;
  }

  valorTotal(): number {
    return this.beneficios.reduce((acc, item) => acc + item.valor, 0);
  }

  editar(id: number): void {
    this.router.navigate(['/editar', id]);
  }

  novo(): void {
    this.router.navigate(['/novo']);
  }

  transferir(): void {
    this.router.navigate(['/transferir']);
  }
}