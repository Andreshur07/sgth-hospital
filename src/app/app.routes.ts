import { Routes } from '@angular/router';

import { Dashboard } from './pages/dashboard/dashboard';
import { Funcionarios } from './pages/funcionarios/funcionarios';
import { FuncionarioForm } from './pages/funcionario-form/funcionario-form';
import { Documentos } from './pages/documentos/documentos';
import { Alertas } from './pages/alertas/alertas';
import { Usuarios } from './pages/usuarios/usuarios';
import { HistorialEstados } from './pages/historial-estados/historial-estados';
import { DocumentoForm } from './pages/documento-form/documento-form';
import { UsuarioForm } from './pages/usuario-form/usuario-form';
import { FuncionarioPerfil } from './pages/funcionario-perfil/funcionario-perfil';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },

  { path: 'dashboard', component: Dashboard },

  { path: 'funcionarios', component: Funcionarios },
  { path: 'funcionarios/nuevo', component: FuncionarioForm },
  { path: 'funcionarios/editar/:id', component: FuncionarioForm },
  { path: 'funcionarios/perfil/:id', component: FuncionarioPerfil },


  { path: 'documentos/nuevo', component: DocumentoForm },
  { path: 'documentos', component: Documentos },
  { path: 'documentos/editar/:id', component: DocumentoForm },
  { path: 'alertas', component: Alertas },
  { path: 'usuarios', component: Usuarios },
  { path: 'historial-estados', component: HistorialEstados },
  { path: 'usuarios/nuevo', component: UsuarioForm },
  { path: 'usuarios/editar/:id', component: UsuarioForm },

  { path: '**', redirectTo: 'dashboard' }
];