import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { OffreRoutingModule } from './offre-routing.module';
import { MainComponent } from './pages/main/main.component';
import { MenuComponent } from './components/menu/menu.component';
import { OffreListComponent } from './pages/offre-list/offre-list.component';


@NgModule({
  declarations: [
    MainComponent,
    MenuComponent,
    OffreListComponent
  ],
  imports: [
    CommonModule,
    OffreRoutingModule
  ]
})
export class OffreModule { }
