import { Routes } from '@angular/router';
import { BeneficioFormComponent } from './pages/beneficio-form/beneficio-form.component';
import { BeneficioListComponent } from './pages/beneficio-list/beneficio-list.component';
import { TransferenciaFormComponent } from './pages/transferencia-form/transferencia-form.component';

export const routes: Routes = [
  { path: '', component: BeneficioListComponent },
  { path: 'novo', component: BeneficioFormComponent },
  { path: 'editar/:id', component: BeneficioFormComponent },
  { path: 'transferir', component: TransferenciaFormComponent }
];
