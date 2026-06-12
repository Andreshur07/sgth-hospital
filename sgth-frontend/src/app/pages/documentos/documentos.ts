import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { DocumentoService } from '../../core/services/documento';
import { Router } from '@angular/router';

@Component({
  selector: 'app-documentos',
  imports: [CommonModule, FormsModule],
  templateUrl: './documentos.html',
  styleUrl: './documentos.scss',
})
export class Documentos implements OnInit {

  private documentoService = inject(DocumentoService);
  private cdr = inject(ChangeDetectorRef);
  private router = inject(Router);

  documentos: any[] = [];
  terminoBusqueda: string = '';
  mensajeExito: string = '';
  mensajeError: string = '';

  ngOnInit(): void {
    this.cargarDocumentos();
  }

  cargarDocumentos(): void {
    this.documentoService.listarResumen().subscribe({
      next: (data: any[]) => {
        this.documentos = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }

  documentosFiltrados(): any[] {
    const termino = this.terminoBusqueda.toLowerCase().trim();

    if (!termino) {
      return this.documentos;
    }

    return this.documentos.filter(documento =>
      String(documento.nombreArchivo ?? '').toLowerCase().includes(termino) ||
      String(documento.tipoDocumento ?? '').toLowerCase().includes(termino) ||
      String(documento.fechaVencimiento ?? '').toLowerCase().includes(termino) ||
      String(documento.estado ?? '').toLowerCase().includes(termino) ||
      String(documento.nombreFuncionario ?? '').toLowerCase().includes(termino)
    );
  }
  nuevoDocumento(): void {
    this.router.navigate(['/documentos/nuevo']);
  }

  editarDocumento(id: number): void {
    this.router.navigate(['/documentos/editar', id]);
  }

  eliminarDocumento(id: number): void {

    const confirmar = confirm(
      '¿Está seguro de eliminar definitivamente este documento? Esta acción no se puede deshacer.'
    );

    if (!confirmar) {
      return;
    }

    this.documentoService.eliminar(id).subscribe({
      next: () => {

        this.mensajeExito =
          'Documento eliminado definitivamente.';

        this.cargarDocumentos();

        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });

        this.cdr.detectChanges();

        setTimeout(() => {
          this.mensajeExito = '';
          this.cdr.detectChanges();
        }, 3000);
      },
      error: (err) => {

        console.error(err);

        this.mensajeError =
          'No se pudo eliminar el documento. Puede estar asociado a otros registros.';

        window.scrollTo({
          top: 0,
          behavior: 'smooth'
        });

        this.cdr.detectChanges();

        setTimeout(() => {
          this.mensajeError = '';
          this.cdr.detectChanges();
        }, 3000);
      }
    });
  }
}