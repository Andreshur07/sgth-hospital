import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';

import { DocumentoService } from '../../core/services/documento';
import { FuncionarioService } from '../../core/services/funcionario';
import { TipoDocumentoService } from '../../core/services/tipo-documento';

@Component({
  selector: 'app-documento-form',
  imports: [CommonModule, FormsModule],
  templateUrl: './documento-form.html',
  styleUrl: './documento-form.scss',
})
export class DocumentoForm implements OnInit {

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  private cdr = inject(ChangeDetectorRef);

  private documentoService = inject(DocumentoService);
  private funcionarioService = inject(FuncionarioService);
  private tipoDocumentoService = inject(TipoDocumentoService);

  modoEdicion = false;
  documentoId!: number;

  mensajeExito = '';
  mensajeError = '';

  funcionarios: any[] = [];
  tiposDocumento: any[] = [];

  documento: any = {
    funcionario: null,
    tipoDocumento: null,
    nombreArchivo: '',
    rutaArchivo: '',
    fechaExpedicion: '',
    fechaVencimiento: '',
    estado: 'VIGENTE',
    observacion: ''
  };

  ngOnInit(): void {
    this.cargarFuncionarios();
    this.cargarTiposDocumento();

    const id = this.route.snapshot.paramMap.get('id');

    if (id) {
      this.modoEdicion = true;
      this.documentoId = Number(id);
      this.cargarDocumento(this.documentoId);
    }
  }

  cargarFuncionarios(): void {
    this.funcionarioService.listarTodos().subscribe({
      next: (data: any) => {
        this.funcionarios = data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudieron cargar los funcionarios.';
        this.cdr.detectChanges();
      }
    });
  }

  cargarDocumento(id: number): void {
    this.documentoService.buscarPorId(id).subscribe({
      next: (data: any) => {
        this.documento = { ...this.documento, ...data };
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudo cargar la información del documento.';
        this.cdr.detectChanges();
      }
    });
  }

  guardar(): void {
  this.mensajeExito = '';
  this.mensajeError = '';

  if (this.modoEdicion) {
    this.documentoService.actualizar(this.documentoId, this.documento).subscribe({
      next: () => {
        this.mensajeExito = 'Documento actualizado correctamente.';
        this.cdr.detectChanges();

        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });

        setTimeout(() => {
          this.router.navigateByUrl('/documentos');
        }, 2500);
      },
      error: (err) => {
        console.error(err);

        this.mensajeError = 'No se pudo actualizar el documento.';
        this.cdr.detectChanges();

        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });
      }
    });
  } else {
    this.documentoService.guardar(this.documento).subscribe({
      next: () => {
        this.mensajeExito = 'Documento registrado correctamente.';
        this.cdr.detectChanges();

        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });

        setTimeout(() => {
          this.router.navigateByUrl('/documentos');
        }, 2500);
      },
      error: (err) => {
        console.error(err);

        this.mensajeError = 'No se pudo guardar el documento.';
        this.cdr.detectChanges();

        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });
      }
    });
  }
}
  cancelar(): void {
    this.router.navigateByUrl('/documentos');
  }
  cargarTiposDocumento(): void {
    this.tipoDocumentoService.listarTodos().subscribe({
      next: (data: any[]) => {
        this.tiposDocumento = data;
        this.cdr.detectChanges();
      },
      error: (err) => {
        console.error(err);
        this.mensajeError = 'No se pudieron cargar los tipos de documento.';
        this.cdr.detectChanges();
      }
    });
  }
}