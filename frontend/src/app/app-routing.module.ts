import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FaleComigoComponent } from './features/fale-comigo/fale-comigo.component';
import { QuemSouEuComponent } from './features/quem-sou-eu/quem-sou-eu.component';
import { EmConstrucaoComponent } from './features/em-construcao/em-construcao.component';
import { NovoPostComponent } from './features/novo-post/novo-post.component';
import { HomeComponent } from './features/home/home.component';

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