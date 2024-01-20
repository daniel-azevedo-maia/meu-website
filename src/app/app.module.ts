import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { FooterComponent } from './footer/footer.component';
import { FaleComigoComponent } from './fale-comigo/fale-comigo.component';
import { DireitoComponent } from './direito/direito.component';
import { HttpClientModule } from '@angular/common/http';
import { QuemSouEuComponent } from './quem-sou-eu/quem-sou-eu.component';
import { OpinioesComponent } from './opinioes/opinioes.component';
import { EmConstrucaoComponent } from './em-construcao/em-construcao.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    FaleComigoComponent,
    DireitoComponent,
    QuemSouEuComponent,
    OpinioesComponent,
    EmConstrucaoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
