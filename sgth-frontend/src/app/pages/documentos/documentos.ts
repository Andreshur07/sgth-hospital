import { Component, OnInit, inject, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DocumentoService } from '../../core/services/documento';

@Component({
  selector: 'app-documentos',
  imports: [CommonModule],
  templateUrl: './documentos.html',
  styleUrl: './documentos.scss',
})
export class Documentos implements OnInit {

  private documentoService = inject(DocumentoService);
  private cdr = inject(ChangeDetectorRef);

  documentos: any[] = [];

  ngOnInit(): void {
    this.documentoService.listarResumen().subscribe({
      next: (data: any) => {
        this.documentos = [...data];
        this.cdr.detectChanges();
      },
      error: (err) => console.error(err)
    });
  }
}