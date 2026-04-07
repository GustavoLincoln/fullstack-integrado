import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { BeneficioService } from '../../services/beneficio.service';

@Component({
  selector: 'app-beneficio-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './beneficio-form.component.html',
  styleUrl: './beneficio-form.component.scss'
})
export class BeneficioFormComponent implements OnInit {
  erro = '';
  editando = false;
  carregando = false;
  salvando = false;
  id!: number;

  form = this.fb.nonNullable.group({
    nome: ['', Validators.required],
    descricao: ['', Validators.required],
    valor: [0, [Validators.required, Validators.min(0.01)]],
    ativo: [true]
  });

  constructor(
    private fb: FormBuilder,
    private service: BeneficioService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const idParam = this.route.snapshot.paramMap.get('id');
    if (idParam) {
      this.editando = true;
      this.id = Number(idParam);
      this.carregando = true;

      this.service.buscar(this.id).subscribe({
        next: (beneficio) => {
          this.form.patchValue({
            nome: beneficio.nome,
            descricao: beneficio.descricao,
            valor: beneficio.valor,
            ativo: beneficio.ativo
          });
          this.carregando = false;
        },
        error: () => {
          this.erro = 'Erro ao carregar benefício';
          this.carregando = false;
        }
      });
    }
  }

  salvar(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.salvando = true;
    this.erro = '';

    const payload = this.form.getRawValue();

    const request = this.editando
      ? this.service.atualizar(this.id, payload)
      : this.service.criar(payload);

    request.subscribe({
      next: () => {
        this.salvando = false;
        this.router.navigate(['/']);
      },
      error: (err) => {
        this.erro = err?.error?.message || 'Erro ao salvar benefício.';
        this.salvando = false;
      }
    });
  }

  voltar(): void {
    this.router.navigate(['/']);
  }
}