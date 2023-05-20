import { Component , OnInit} from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-costumers',
  templateUrl: './costumers.component.html',
  styleUrls: ['./costumers.component.css']
})
export class CostumersComponent implements OnInit{

  costumers: any;
  
  constructor(private http:HttpClient , private router: Router) { 

  }

  ngOnInit(): void {
      this.http.get("http://localhost:8888/COSTUMER-SERVICE/costumers")
              .subscribe({
                next: (data) => {
                  this.costumers = data;
                },
                error : (error) => {
                }
              }) 
  }

  getBills(costumer: any){
    this.router.navigate(['/bills', costumer.id]);
  }
}
