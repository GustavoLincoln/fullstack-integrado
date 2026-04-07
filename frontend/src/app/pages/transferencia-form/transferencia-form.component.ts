import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormBuilder, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { BeneficioService } from '../../services/beneficio.service';

@Component({
  selector: 'app-transferencia-form',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule, RouterModule],
  templateUrl: './transferencia-form.component.html',
  styleUrl: './transferencia-form.component.scss'
})
export class TransferenciaFormComponent {
  erro = '';
  sucesso = '';
  processando = false;

  form = this.fb.nonNullable.group({
    fromId: [0, [Validators.required, Validators.min(1)]],
    toId: [0, [Validators.required, Validators.min(1)]],
    amount: [0, [Validators.required, Validators.min(0.01)]]
  });

  constructor(
    private fb: FormBuilder,
    private service: BeneficioService,
    private router: Router
  ) { }

  transferir(): void {
  this.erro = '';
  this.sucesso = '';

  if (this.form.invalid) {
    this.form.markAllAsTouched();
    return;
  }

  const payload = this.form.getRawValue();

  if (payload.fromId === payload.toId) {
    this.erro = 'Origem e destino não podem ser iguais.';
    return;
  }

  this.processando = true;

  this.service.transferir(payload).subscribe({
    next: () => {
      this.sucesso = 'Transferência realizada com sucesso.';
      this.processando = false;
      this.form.reset({
        fromId: 0,
        toId: 0,
        amount: 0
      });
    },
    error: (err) => {
      this.erro = err?.error?.message || 'Erro ao realizar transferência.';
      this.processando = false;
    }
  });
}

  voltar(): void {
    this.router.navigate(['/']);
  }
}
