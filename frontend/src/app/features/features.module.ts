import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { DireitoComponent } from './direito/direito.component';
import { EmConstrucaoComponent } from './em-construcao/em-construcao.component';
import { FaleComigoComponent } from './fale-comigo/fale-comigo.component';
import { HomeComponent } from './home/home.component';
import { OpinioesComponent } from './opinioes/opinioes.component';
import { QuemSouEuComponent } from './quem-sou-eu/quem-sou-eu.component';
import { NovoPostComponent } from './novo-post/novo-post.component';
import { FormsModule } from '@angular/forms';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

@NgModule({
  declarations: [
    DireitoComponent,
    EmConstrucaoComponent,
    FaleComigoComponent,
    HomeComponent,
    OpinioesComponent,
    QuemSouEuComponent,
    NovoPostComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    CKEditorModule
  ],
  exports: [
    DireitoComponent,
    EmConstrucaoComponent,
    FaleComigoComponent,
    HomeComponent,
    OpinioesComponent,
    QuemSouEuComponent,
    NovoPostComponent
  ]

})
export class FeaturesModule { }
