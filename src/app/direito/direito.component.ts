import { Component, OnInit } from '@angular/core';
import { DireitoService } from '../services/direito.service';

@Component({
  selector: 'app-direito',
  templateUrl: './direito.component.html',
  styleUrls: ['./direito.component.css']
})
export class DireitoComponent implements OnInit {

  posts: any;

  constructor(private direitoService: DireitoService) {}

  ngOnInit(): void {
    this.direitoService.listarPosts().subscribe(posts => {
      this.posts = posts;
    });
  }

  titulo = "Meu livro - Introdução à Advocacia Trabalhista";
  subtitulo = "Guia para Advogados Iniciantes";
  resumo = "Publicado em 2019, Introdução à Advocacia Trabalhista é um guia perfeito para advogados iniciantes.";
  dataPublicacao = "Publicado em: 01/01/2019, 10:00";

  teste() {
    console.log(this.posts);
  }

}
