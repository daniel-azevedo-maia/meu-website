import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FormsModule } from '@angular/forms';
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import { HomeComponent } from './components/home/home.component';
import { BannerComponent } from './components/banner/banner.component';
import { PostsAnterioresComponent } from './components/posts-anteriores/posts-anteriores.component';

@NgModule({
  declarations: [
    HomeComponent,
    BannerComponent,
    PostsAnterioresComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    CKEditorModule
  ],
  exports: [
    HomeComponent,
    BannerComponent,
    PostsAnterioresComponent
  ]

})
export class FeaturesModule { }
