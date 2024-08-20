import {Component, OnInit} from '@angular/core';
import {PosteResponse} from "../../services/models/PosteResponse";
import {PostesService} from "../../services/services/postes.service";

@Component({
  selector: 'app-accueil',
  templateUrl: './accueil.component.html',
  styleUrls: ['./accueil.component.css']
})
export class AccueilComponent implements OnInit{
  postes: PosteResponse[] = [];

  constructor(private postesService: PostesService) {}

  ngOnInit(): void {
    this.postesService.getAllPostes().subscribe({
      next: (data: PosteResponse[]) => {
        this.postes = data;
        console.log(this.postes); // Add this to verify if data is fetched
      },
      error: (error: any) => {
        console.error('There was an error!', error);
      }
    });
  }

}
