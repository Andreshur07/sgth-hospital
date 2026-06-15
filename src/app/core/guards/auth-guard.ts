import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

import { Auth } from '../services/auth';

export const authGuard: CanActivateFn = () => {

  const auth = inject(Auth);
  const router = inject(Router);

  console.log('AUTH GUARD EJECUTADO');
  console.log('Usuario:', auth.obtenerUsuario());
  console.log('Está logueado:', auth.estaLogueado());

  if (auth.estaLogueado()) {
    return true;
  }

  router.navigateByUrl('/login');
  return false;
};