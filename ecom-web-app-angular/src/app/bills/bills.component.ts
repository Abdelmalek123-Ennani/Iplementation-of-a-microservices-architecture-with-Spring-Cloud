import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-bills',
  templateUrl: './bills.component.html',
  styleUrls: ['./bills.component.css']
})
export class BillsComponent implements OnInit{

  bills: any;
  costumerId!: Number;
  constructor(private http:HttpClient , private router: ActivatedRoute) { 
    this.costumerId =  this.router.snapshot.params['costumerId'];
  }

  ngOnInit(): void {
      this.http.get("http://localhost:8888/BILLING-SERVICE/bills/costumer/"+this.costumerId)
              .subscribe({
                next: (data) => {
                  console.log(data);
                  this.bills = data;
                },
                error : (error) => {
                }
              }) 
  }
}
