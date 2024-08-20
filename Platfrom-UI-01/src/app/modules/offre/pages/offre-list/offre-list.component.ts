import {Component, OnInit} from '@angular/core';
import {Router} from "@angular/router";
import {OffresService} from "../../../../services/services/offres.service";
import {OffreResponse} from "../../../../services/models/offre-response";


@Component({
  selector: 'app-offre-list',
  templateUrl: './offre-list.component.html',
  styleUrls: ['./offre-list.component.css']
})
export class OffreListComponent implements OnInit{
  offerResponse: OffreResponse[] = [];
   page= 0;
   size= 5;
constructor(
  private offreService: OffresService,
  private router: Router
) {
}

  ngOnInit(): void {
  this.findAllOffres();
  }


  private findAllOffres() {
  this.offreService.getAllOffres({
    page: this.page,
    size: this.size
  }).subscribe({
    next:(offres)=>{
    this.offerResponse = offres;
    }
  });

  }
}
