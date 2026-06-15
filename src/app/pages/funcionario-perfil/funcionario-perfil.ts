import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { FuncionarioService } from '../../core/services/funcionario';
import { DocumentoService } from '../../core/services/documento';
import { TipoDocumentoService } from '../../core/services/tipo-documento';
import { CapacitacionService } from '../../core/services/capacitacion';
import { ExperienciaLaboralService } from '../../core/services/experiencia-laboral';

@Component({
  selector: 'app-funcionario-perfil',
  imports: [CommonModule, FormsModule],
  templateUrl: './funcionario-perfil.html',
  styleUrl: './funcionario-perfil.scss',
})
export class FuncionarioPerfil implements OnInit {

  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private cdr = inject(ChangeDetectorRef);

  private funcionarioService = inject(FuncionarioService);
  public documentoService = inject(DocumentoService);
  public capacitacionService = inject(CapacitacionService);
  public experienciaLaboralService = inject(ExperienciaLaboralService);
  private tipoDocumentoService = inject(TipoDocumentoService);

  funcionarioId!: number;
  funcionario: any = null;

  pestanaActiva = 'datos';

  documentosFuncionario: any[] = [];
  tiposDocumento: any[] = [];
  requisitosLegales: any[] = [];
  porcentajeCumplimiento = 0;
  totalRequisitos = 0;
  requisitosCumplidos = 0;

  mensajeDocumentoExito = '';
  mensajeDocumentoError = '';

  archivoSeleccionado: File | null = null;

  documentoForm: any = {
    tipoDocumentoId: null,
    fechaExpedicion: '',
    fechaVencimiento: '',
    observacion: ''
  };

  capacitacionesFuncionario: any[] = [];

  mensajeCapacitacionExito = '';
  mensajeCapacitacionError = '';

  archivoCapacitacionSeleccionado: File | null = null;

  capacitacionForm: any = {
    nombre: '',
    institucion: '',
    fechaInicio: '',
    fechaVencimiento: ''
  };

  experienciasFuncionario: any[] = [];

  mensajeExperienciaExito = '';
  mensajeExperienciaError = '';

  archivoExperienciaSeleccionado: File | null = null;

  experienciaForm: any = {
    empresa: '',
    cargo: '',
    fechaInicio: '',
    fechaFin: '',
    funciones: ''
  };

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.funcionarioId = Number(id);
      this.cargarFuncionario();
      this.cargarDocumentosFuncionario();
      this.cargarCapacitacionesFuncionario();
      this.cargarExperienciasFuncionario();
      this.cargarTiposDocumento();
    }
  }

  cargarFuncionario(): void {
    this.funcionarioService.buscarPorId(this.funcionarioId).subscribe({
      next: (data: any) => {
        this.funcionario = data;
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  cargarDocumentosFuncionario(): void {
    this.documentoService.listarPorFuncionario(this.funcionarioId).subscribe({
      next: (data: any[]) => {
        this.documentosFuncionario = [...data];

        this.cargarRequisitosLegales();

        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  cargarTiposDocumento(): void {
    this.tipoDocumentoService.listarTodos().subscribe({
      next: (data: any[]) => {
        this.tiposDocumento = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  cambiarPestana(pestana: string): void {
    this.pestanaActiva = pestana;
  }

  seleccionarArchivo(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.archivoSeleccionado = input.files[0];
    }
  }

  guardarDocumentoPerfil(): void {
    this.mensajeDocumentoExito = '';
    this.mensajeDocumentoError = '';

    if (!this.documentoForm.tipoDocumentoId) {
      this.mensajeDocumentoError = 'Debe seleccionar un tipo de documento.';
      return;
    }

    if (!this.documentoForm.fechaExpedicion) {
      this.mensajeDocumentoError = 'Debe ingresar la fecha de expedición.';
      return;
    }

    if (!this.documentoForm.fechaVencimiento) {
      this.mensajeDocumentoError = 'Debe ingresar la fecha de vencimiento.';
      return;
    }

    if (!this.archivoSeleccionado) {
      this.mensajeDocumentoError = 'Debe seleccionar un archivo PDF.';
      return;
    }

    const formData = new FormData();

    formData.append('funcionarioId', String(this.funcionarioId));
    formData.append('tipoDocumentoId', String(this.documentoForm.tipoDocumentoId));
    formData.append('fechaExpedicion', this.documentoForm.fechaExpedicion);
    formData.append('fechaVencimiento', this.documentoForm.fechaVencimiento);
    formData.append('observacion', this.documentoForm.observacion || '');
    formData.append('archivo', this.archivoSeleccionado);

    this.documentoService.subirDocumento(formData).subscribe({
      next: () => {
        this.mensajeDocumentoExito = 'Documento cargado correctamente.';

        this.documentoForm = {
          tipoDocumentoId: null,
          fechaExpedicion: '',
          fechaVencimiento: '',
          observacion: ''
        };

        this.archivoSeleccionado = null;

        this.cargarDocumentosFuncionario();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeDocumentoError = 'No se pudo cargar el documento.';
        this.cdr.detectChanges();
      }
    });
  }

  eliminarDocumentoPerfil(id: number): void {
    const confirmar = confirm('¿Está seguro de eliminar este documento?');

    if (!confirmar) {
      return;
    }

    this.documentoService.eliminar(id).subscribe({
      next: () => {
        this.mensajeDocumentoExito = 'Documento eliminado correctamente.';
        this.cargarDocumentosFuncionario();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeDocumentoError = 'No se pudo eliminar el documento.';
        this.cdr.detectChanges();
      }
    });
  }

  cargarRequisitosLegales(): void {
    this.tipoDocumentoService.listarObligatorios().subscribe({
      next: (tipos: any[]) => {
        this.requisitosLegales = tipos.map(tipo => {
          const documento = this.documentosFuncionario.find(
            doc => doc.tipoDocumento?.id === tipo.id
          );

          return {
            tipoDocumento: tipo,
            documento,
            cumple: !!documento
          };
        });

        this.totalRequisitos = this.requisitosLegales.length;
        this.requisitosCumplidos = this.requisitosLegales.filter(r => r.cumple).length;

        this.porcentajeCumplimiento =
          this.totalRequisitos > 0
            ? Math.round((this.requisitosCumplidos / this.totalRequisitos) * 100)
            : 0;

        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  volver(): void {
    this.router.navigate(['/funcionarios']);
  }

  cargarCapacitacionesFuncionario(): void {
    this.capacitacionService.listarPorFuncionario(this.funcionarioId).subscribe({
      next: (data: any[]) => {
        this.capacitacionesFuncionario = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  seleccionarArchivoCapacitacion(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.archivoCapacitacionSeleccionado = input.files[0];
    }
  }

  guardarCapacitacionPerfil(): void {
    this.mensajeCapacitacionExito = '';
    this.mensajeCapacitacionError = '';

    if (!this.capacitacionForm.nombre) {
      this.mensajeCapacitacionError = 'Debe ingresar el nombre de la capacitación.';
      return;
    }

    if (!this.capacitacionForm.fechaInicio) {
      this.mensajeCapacitacionError = 'Debe ingresar la fecha de inicio.';
      return;
    }

    if (!this.capacitacionForm.fechaVencimiento) {
      this.mensajeCapacitacionError = 'Debe ingresar la fecha de vencimiento.';
      return;
    }

    if (!this.archivoCapacitacionSeleccionado) {
      this.mensajeCapacitacionError = 'Debe seleccionar un PDF de soporte.';
      return;
    }

    const formData = new FormData();

    formData.append('funcionarioId', String(this.funcionarioId));
    formData.append('nombre', this.capacitacionForm.nombre);
    formData.append('institucion', this.capacitacionForm.institucion || '');
    formData.append('fechaInicio', this.capacitacionForm.fechaInicio);
    formData.append('fechaVencimiento', this.capacitacionForm.fechaVencimiento);
    formData.append('archivo', this.archivoCapacitacionSeleccionado);

    this.capacitacionService.subirCapacitacion(formData).subscribe({
      next: () => {
        this.mensajeCapacitacionExito = 'Capacitación cargada correctamente.';

        this.capacitacionForm = {
          nombre: '',
          institucion: '',
          fechaInicio: '',
          fechaVencimiento: ''
        };

        this.archivoCapacitacionSeleccionado = null;

        this.cargarCapacitacionesFuncionario();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeCapacitacionError = 'No se pudo cargar la capacitación.';
        this.cdr.detectChanges();
      }
    });
  }

  eliminarCapacitacionPerfil(id: number): void {
    const confirmar = confirm('¿Está seguro de eliminar esta capacitación?');

    if (!confirmar) {
      return;
    }

    this.capacitacionService.eliminar(id).subscribe({
      next: () => {
        this.mensajeCapacitacionExito = 'Capacitación eliminada correctamente.';
        this.cargarCapacitacionesFuncionario();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeCapacitacionError = 'No se pudo eliminar la capacitación.';
        this.cdr.detectChanges();
      }
    });
  }

  cargarExperienciasFuncionario(): void {
    this.experienciaLaboralService.listarPorFuncionario(this.funcionarioId).subscribe({
      next: (data: any[]) => {
        this.experienciasFuncionario = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  seleccionarArchivoExperiencia(event: Event): void {
    const input = event.target as HTMLInputElement;

    if (input.files && input.files.length > 0) {
      this.archivoExperienciaSeleccionado = input.files[0];
    }
  }

  guardarExperienciaPerfil(): void {
    this.mensajeExperienciaExito = '';
    this.mensajeExperienciaError = '';

    if (!this.experienciaForm.empresa) {
      this.mensajeExperienciaError = 'Debe ingresar la empresa.';
      return;
    }

    if (!this.experienciaForm.cargo) {
      this.mensajeExperienciaError = 'Debe ingresar el cargo.';
      return;
    }

    if (!this.experienciaForm.fechaInicio) {
      this.mensajeExperienciaError = 'Debe ingresar la fecha de inicio.';
      return;
    }

    if (!this.experienciaForm.fechaFin) {
      this.mensajeExperienciaError = 'Debe ingresar la fecha de finalización.';
      return;
    }

    if (!this.archivoExperienciaSeleccionado) {
      this.mensajeExperienciaError = 'Debe seleccionar un PDF de soporte.';
      return;
    }

    const formData = new FormData();

    formData.append('funcionarioId', String(this.funcionarioId));
    formData.append('empresa', this.experienciaForm.empresa);
    formData.append('cargo', this.experienciaForm.cargo);
    formData.append('fechaInicio', this.experienciaForm.fechaInicio);
    formData.append('fechaFin', this.experienciaForm.fechaFin);
    formData.append('funciones', this.experienciaForm.funciones || '');
    formData.append('archivo', this.archivoExperienciaSeleccionado);

    this.experienciaLaboralService.subirExperiencia(formData).subscribe({
      next: () => {
        this.mensajeExperienciaExito = 'Experiencia laboral cargada correctamente.';

        this.experienciaForm = {
          empresa: '',
          cargo: '',
          fechaInicio: '',
          fechaFin: '',
          funciones: ''
        };

        this.archivoExperienciaSeleccionado = null;

        this.cargarExperienciasFuncionario();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeExperienciaError = 'No se pudo cargar la experiencia laboral.';
        this.cdr.detectChanges();
      }
    });
  }

  eliminarExperienciaPerfil(id: number): void {
    const confirmar = confirm('¿Está seguro de eliminar esta experiencia laboral?');

    if (!confirmar) {
      return;
    }

    this.experienciaLaboralService.eliminar(id).subscribe({
      next: () => {
        this.mensajeExperienciaExito = 'Experiencia laboral eliminada correctamente.';
        this.cargarExperienciasFuncionario();
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeExperienciaError = 'No se pudo eliminar la experiencia laboral.';
        this.cdr.detectChanges();
      }
    });
  }

  imprimirReporte(): void {
    window.print();
  }

}