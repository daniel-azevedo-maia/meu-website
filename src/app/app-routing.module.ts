import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FaleComigoComponent } from './fale-comigo/fale-comigo.component';
import { HomeComponent } from './home/home.component';
import { DireitoComponent } from './direito/direito.component';
import { QuemSouEuComponent } from './quem-sou-eu/quem-sou-eu.component';
import { OpinioesComponent } from './opinioes/opinioes.component';

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'direito', component: DireitoComponent },
  { path: 'opinioes', component: OpinioesComponent },
  { path: 'quem-sou-eu', component: QuemSouEuComponent },
  { path: 'fale-comigo', component: FaleComigoComponent },
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}