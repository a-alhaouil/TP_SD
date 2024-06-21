import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";

import {AsyncPipe, NgForOf} from "@angular/common";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {Observable} from "rxjs";
import {FormsModule} from "@angular/forms";


@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    NgForOf,
    AsyncPipe,
    FormsModule,

  ],
  templateUrl: './products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  public products:Array<Product>=[];
  public keyword: string='';

  constructor(private productService:ProductService) {
  }
  ngOnInit() {
    this.getProducts();


  }
  getProducts(){
    this.productService.getProducts(1,4).subscribe({
      next : data=> {
        this.products = data
      },
      error:err =>{
        console.log(err);
      }
    })
    //this.products$=this.productService.getProducts();
  }

  handleCheckedProduct(product: Product){
    this.productService.checkProducts(product).subscribe({
      next: updateProduct=>{
        product.checked=!product.checked;

      }
    })
  }

  handleDelete(product: Product) {
    if(confirm("Etes vous sure?"))
    this.productService.deleteProduct(product).subscribe({
      next:value => {
        //this.getProducts();
        this.products=this.products.filter(p=>p.id!=product.id);
      }
    })

  }

  searchProducts() {
    this.productService.searchProducts(this.keyword).subscribe({
      next: value=> {
        this.products=value;
      }

    })
  }
}
