import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FuncionarioService } from '../../core/services/funcionario';
import { CargoService } from '../../core/services/cargo';
import { DependenciaService } from '../../core/services/dependencia';
import { TipoContratoService } from '../../core/services/tipo-contrato';
import { EstadoFuncionarioService } from '../../core/services/estado-funcionario';

@Component({
  selector: 'app-funcionario-form',
  imports: [FormsModule, CommonModule],
  templateUrl: './funcionario-form.html',
  styleUrl: './funcionario-form.scss',
})
export class FuncionarioForm implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private funcionarioService = inject(FuncionarioService);
  private cargoService = inject(CargoService);
  private dependenciaService = inject(DependenciaService);
  private tipoContratoService = inject(TipoContratoService);
  private estadoFuncionarioService = inject(EstadoFuncionarioService);
  private cdr = inject(ChangeDetectorRef);

  modoEdicion = false;
  funcionarioId!: number;
  tipoToast: string = 'success';
  tituloToast: string = 'Éxito';

  mensajeExito = '';
  mensajeError = '';

  cargos: any[] = [];
  dependencias: any[] = [];
  tiposContrato: any[] = [];
  estadosFuncionario: any[] = [];

  funcionario: any = {
    tipoDocumento: 'C.C',
    numeroDocumento: '',
    sexo: 'M',
    nacionalidad: 'Colombiano',
    pais: '',
    lugarExpedicion: '',
    fechaExpedicion: '',
    estadoCivil: 'Soltero(a)',
    numeroHijos: 0,
    primerNombre: '',
    segundoNombre: '',
    primerApellido: '',
    segundoApellido: '',
    fechaNacimiento: '',
    paisNacimiento: '',
    departamentoNacimiento: '',
    ciudadNacimiento: '',
    direccion: '',
    paisDireccion: '',
    departamentoDireccion: '',
    ciudadDireccion: '',
    telefono: '',
    correo: '',
    grupoSanguineo: 'A+',
    grupoEtnico: 'Ninguno',
    claseLibretaMilitar: 'Ninguna',
    numeroLibretaMilitar: '',
    distritoMilitar: '',
    profesion: '',
    eps: '',
    fondoPensiones: '',
    arl: '',
    fechaIngreso: '',
    estado: 'ACTIVO',
    cargo: null,
    dependencia: null,
    tipoContrato: null,
    estadoFuncionario: null
  };

  ngOnInit(): void {
    this.cargarCombos();

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicion = true;
      this.funcionarioId = Number(id);
      this.cargarFuncionario(this.funcionarioId);
    }
  }

  cargarFuncionario(id: number): void {
    this.funcionarioService.buscarPorId(id).subscribe({
      next: (data: any) => {
        console.log('FUNCIONARIO CARGADO:', data);
        this.funcionario = { ...this.funcionario, ...data };
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudo cargar la información del funcionario.';
        this.cdr.detectChanges();
      }
    });
  }

  cargarCombos(): void {
    this.cargoService.listarTodos().subscribe({
      next: (data) => {
        this.cargos = data;
        this.cdr.detectChanges();
      }
    });

    this.dependenciaService.listarTodos().subscribe({
      next: (data) => {
        this.dependencias = data;
        this.cdr.detectChanges();
      }
    });

    this.tipoContratoService.listarTodos().subscribe({
      next: (data) => {
        this.tiposContrato = data;
        this.cdr.detectChanges();
      }
    });

    this.estadoFuncionarioService.listarTodos().subscribe({
      next: (data) => {
        this.estadosFuncionario = data;
        this.cdr.detectChanges();
      }
    });
  }

  guardar(): void {

    this.mensajeExito = '';
    this.mensajeError = '';

    if (this.modoEdicion) {

      this.funcionarioService
        .actualizar(this.funcionarioId, this.funcionario)
        .subscribe({

          next: () => {

            this.configurarToastPorEstado();

            this.cdr.detectChanges();

            window.scrollTo({
              top: 0,
              behavior: 'smooth'
            });

            setTimeout(() => {
              this.router.navigateByUrl('/funcionarios');
            }, 2500);

          },

          error: (err) => {

            console.error(err);

            this.mensajeError =
              'No se pudo actualizar el funcionario.';

            this.cdr.detectChanges();
          }
        });

    } else {

      this.funcionarioService
        .guardar(this.funcionario)
        .subscribe({

          next: () => {

            this.configurarToastPorEstado();

            this.cdr.detectChanges();

            window.scrollTo({
              top: 0,
              behavior: 'smooth'
            });

            setTimeout(() => {
              this.router.navigateByUrl('/funcionarios');
            }, 2500);

          },

          error: (err) => {

            console.error(err);

            this.mensajeError =
              'No se pudo guardar el funcionario.';

            this.cdr.detectChanges();
          }
        });
    }
  }
  cancelar(): void {
    this.router.navigateByUrl('/funcionarios');
  }

  configurarToastPorEstado(): void {

    const estado =
      this.funcionario.estadoFuncionario?.nombre;

    switch (estado) {

      case 'ACTIVO':
        this.tipoToast = 'success';
        this.tituloToast = 'Funcionario activo';
        this.mensajeExito =
          'El funcionario se encuentra activo y disponible para sus funciones.';
        break;

      case 'INACTIVO':
        this.tipoToast = 'danger';
        this.tituloToast = 'Funcionario inactivo';
        this.mensajeExito =
          'El funcionario fue marcado como inactivo correctamente.';
        break;

      case 'RETIRADO':
        this.tipoToast = 'dark';
        this.tituloToast = 'Funcionario retirado';
        this.mensajeExito =
          'El funcionario fue retirado de la institución.';
        break;

      case 'SUSPENDIDO':
        this.tipoToast = 'warning';
        this.tituloToast = 'Funcionario suspendido';
        this.mensajeExito =
          'El funcionario fue suspendido temporalmente.';
        break;

      case 'LICENCIA':
        this.tipoToast = 'info';
        this.tituloToast = 'Funcionario en licencia';
        this.mensajeExito =
          'El funcionario se encuentra actualmente en licencia.';
        break;

      case 'VACACIONES':
        this.tipoToast = 'purple';
        this.tituloToast = 'Funcionario en vacaciones';
        this.mensajeExito =
          'El funcionario se encuentra disfrutando de vacaciones.';
        break;

      default:
        this.tipoToast = 'success';
        this.tituloToast = 'Actualización exitosa';
        this.mensajeExito =
          'Funcionario actualizado correctamente.';
    }
  }
}