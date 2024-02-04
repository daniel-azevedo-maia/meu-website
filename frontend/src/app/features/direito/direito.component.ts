import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-direito',
  templateUrl: './direito.component.html',
  styleUrls: ['./direito.component.css']
})
export class DireitoComponent implements OnInit {

  posts: any;

  constructor() {}

  ngOnInit(): void {}

  titulo = "Meu livro - Introdução à Advocacia Trabalhista";
  subtitulo = "Guia para Advogados Iniciantes";
  resumo = "Publicado em 2019, Introdução à Advocacia Trabalhista é um guia perfeito para advogados iniciantes.";
  dataPublicacao = "Publicado em: 01/01/2019, 10:00";

  teste() {
    console.log(this.posts);
  }

}
