import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FaleComigoComponent } from './fale-comigo/fale-comigo.component';
import { HomeComponent } from './home/home.component';
import { QuemSouEuComponent } from './quem-sou-eu/quem-sou-eu.component';
import { EmConstrucaoComponent } from './em-construcao/em-construcao.component';
import { NovoPostComponent } from './novo-post/novo-post.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'direito', component: EmConstrucaoComponent },
  { path: 'tecnologia', component: EmConstrucaoComponent },
  { path: 'opinioes', component: EmConstrucaoComponent },
  { path: 'quem-sou-eu', component: QuemSouEuComponent },
  { path: 'fale-comigo', component: FaleComigoComponent },
  { path: 'novo-post', component: NovoPostComponent }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}