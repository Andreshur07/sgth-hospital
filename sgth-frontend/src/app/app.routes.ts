import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Funcionarios } from './pages/funcionarios/funcionarios';
import { FuncionarioForm } from './pages/funcionario-form/funcionario-form';
import { Documentos } from './pages/documentos/documentos';
import { Alertas } from './pages/alertas/alertas';
import { Usuarios } from './pages/usuarios/usuarios';
import { HistorialEstados } from './pages/historial-estados/historial-estados';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },

  { path: 'dashboard', component: Dashboard },

  { path: 'funcionarios', component: Funcionarios },
  { path: 'funcionarios/nuevo', component: FuncionarioForm },
  { path: 'funcionarios/editar/:id', component: FuncionarioForm },

  { path: 'documentos', component: Documentos },
  { path: 'alertas', component: Alertas },
  { path: 'usuarios', component: Usuarios },
  { path: 'historial-estados', component: HistorialEstados },

  { path: '**', redirectTo: 'dashboard' }
];