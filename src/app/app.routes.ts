import { Routes } from '@angular/router';

import { authGuard } from './core/guards/auth-guard';

import { Login } from './pages/login/login';

import { Dashboard } from './pages/dashboard/dashboard';
import { Funcionarios } from './pages/funcionarios/funcionarios';
import { FuncionarioForm } from './pages/funcionario-form/funcionario-form';
import { FuncionarioPerfil } from './pages/funcionario-perfil/funcionario-perfil';

import { Documentos } from './pages/documentos/documentos';
import { DocumentoForm } from './pages/documento-form/documento-form';

import { Alertas } from './pages/alertas/alertas';

import { Usuarios } from './pages/usuarios/usuarios';
import { UsuarioForm } from './pages/usuario-form/usuario-form';

import { HistorialEstados } from './pages/historial-estados/historial-estados';

export const routes: Routes = [

  {
    path: 'login',
    component: Login
  },

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'dashboard',
    component: Dashboard,
    canActivate: [authGuard]
  },

  {
    path: 'funcionarios',
    component: Funcionarios,
    canActivate: [authGuard]
  },
  {
    path: 'funcionarios/nuevo',
    component: FuncionarioForm,
    canActivate: [authGuard]
  },
  {
    path: 'funcionarios/editar/:id',
    component: FuncionarioForm,
    canActivate: [authGuard]
  },
  {
    path: 'funcionarios/perfil/:id',
    component: FuncionarioPerfil,
    canActivate: [authGuard]
  },

  {
    path: 'documentos',
    component: Documentos,
    canActivate: [authGuard]
  },
  {
    path: 'documentos/nuevo',
    component: DocumentoForm,
    canActivate: [authGuard]
  },
  {
    path: 'documentos/editar/:id',
    component: DocumentoForm,
    canActivate: [authGuard]
  },

  {
    path: 'alertas',
    component: Alertas,
    canActivate: [authGuard]
  },

  {
    path: 'usuarios',
    component: Usuarios,
    canActivate: [authGuard]
  },
  {
    path: 'usuarios/nuevo',
    component: UsuarioForm,
    canActivate: [authGuard]
  },
  {
    path: 'usuarios/editar/:id',
    component: UsuarioForm,
    canActivate: [authGuard]
  },

  {
    path: 'historial-estados',
    component: HistorialEstados,
    canActivate: [authGuard]
  },

  {
    path: '**',
    redirectTo: 'login'
  }
];