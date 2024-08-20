import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainComponent} from "./pages/main/main.component";
import {OffreComponent} from "../../pages/offre/offre.component";
import {OffreListComponent} from "./pages/offre-list/offre-list.component";

const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    children:[
      {
        path: 'offresss',
        component: OffreListComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OffreRoutingModule { }
