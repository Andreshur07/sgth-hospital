import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

import { FuncionarioService } from '../../core/services/funcionario';
import { DocumentoService } from '../../core/services/documento';
import { TipoDocumentoService } from '../../core/services/tipo-documento';

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

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.funcionarioId = Number(id);
      this.cargarFuncionario();
      this.cargarDocumentosFuncionario();
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
}