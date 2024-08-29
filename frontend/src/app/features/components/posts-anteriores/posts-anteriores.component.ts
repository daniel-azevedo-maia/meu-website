import { Component } from '@angular/core';
import { PostService } from 'src/app/core/services/post.service';
import { PostSummaryDTO } from 'src/app/shared/models/post-summary.dto';

@Component({
  selector: 'posts-anteriores-component',
  templateUrl: './posts-anteriores.component.html',
  styleUrls: ['./posts-anteriores.component.css'],
})
export class PostsAnterioresComponent {
  
  posts: PostSummaryDTO[] = [];

  constructor(private postService: PostService) {}

  ngOnInit(): void {
    this.postService.getPosts().subscribe((data: PostSummaryDTO[]) => {
      this.posts = data;
    });

    /*
    Assim que o componente for carregado e renderizado pelo Angular, o método ngOnInit() 
    é acionado. Dentro deste método, eu chamo o serviço de posts (PostService) e uso 
    o método getPosts() para buscar as publicações. Este método getPosts() retorna 
    um Observable, 
    que é como uma fonte de dados que pode emitir valores ao longo do tempo. 
    Eu então "me inscrevo" nesse Observable usando o método subscribe(). 
    Ao me inscrever, eu passo uma função anônima (arrow function) 
    que recebe um parâmetro chamado data. Esse data é do tipo PostSummaryDTO[], 
    ou seja, um array de objetos PostSummaryDTO. Quando o Observable tiver dados 
    prontos para serem entregues (ou seja, quando a resposta da requisição HTTP chegar), 
    ele chamará a função que eu passei, passando os dados recebidos como argumento 
    para o parâmetro data. Nesse momento, a variável posts da classe será 
    atualizada com o conteúdo de data, que contém as publicações recebidas da API. 
    Assim, as publicações são exibidas na tela automaticamente, pois o Angular 
    detecta a mudança e re-renderiza o template com os novos dados.
    */
  }
}
