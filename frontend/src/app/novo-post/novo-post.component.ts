import { Component } from '@angular/core';
import ClassicEditor from '@ckeditor/ckeditor5-build-classic';

@Component({
  selector: 'app-novo-post',
  templateUrl: './novo-post.component.html',
  styleUrls: ['./novo-post.component.css']
})
export class NovoPostComponent {

  public Editor = ClassicEditor;
  titulo = '';
  subtitulo = '';
  categoria = '';
  autor = '';
  texto = '';
  

  
}
