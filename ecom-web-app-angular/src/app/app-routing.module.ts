import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes } from '@angular/router';
import { ProductsComponent } from './products/products.component';
import { RouterModule } from '@angular/router';
import { CostumersComponent } from './costumers/costumers.component';
import { BillsComponent } from './bills/bills.component';


const routes: Routes = [
   {
      path: 'products', component: ProductsComponent
   } , 
   {
      path: 'costumers', component: CostumersComponent
   },
   {
    path: 'bills/:costumerId', component: BillsComponent
   }
]

@NgModule({
  declarations: [],
  imports: [
    CommonModule,
    RouterModule.forRoot(routes)
  ]
})
export class AppRoutingModule { }
