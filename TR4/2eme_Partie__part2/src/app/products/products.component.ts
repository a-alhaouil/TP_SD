import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import { NgModule } from '@angular/core';

import {AsyncPipe, NgForOf,NgClass} from "@angular/common";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";
import {Observable} from "rxjs";
import {FormsModule} from "@angular/forms";
import { Router } from '@angular/router';


@Component({
  selector: 'app-products',
  standalone: true,
  imports: [
    NgForOf,
    AsyncPipe,
    FormsModule,
    NgClass,


  ],
  templateUrl:'./products.component.html',
  styleUrl: './products.component.css'
})
export class ProductsComponent implements OnInit{
  public products:Array<Product>=[];
  public keyword: string='';
  totalPages:number=0;
  pageSize:number=5;
  currentPage:number=1;

  constructor(private productService:ProductService,private router :Router) {
  }
  ngOnInit() {
    this.searchProducts();


  }
  searchProducts(){
    this.productService.searchProducts(this.keyword,this.currentPage,this.pageSize)
      .subscribe({
        next:(resp) =>{
          this.products=resp.body as Product[];
          let totalProducts:number=parseInt(resp.headers.get('x-total-count')!);
          this.totalPages=Math.floor(totalProducts/this.pageSize);
          if (totalProducts % this.pageSize !=0){
            this.totalPages=this.totalPages+1;
          }
        },
        error :err => {
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

  // searchProducts() {
  //   this.productService.searchProducts(this.keyword).subscribe({
  //     next: value=> {
  //       this.products=value;
  //     }
  //
  //   })
  // }

  handleGotoPage(page: number) {
    this.currentPage=page;
    this.searchProducts();
  }
  handleEdit(product: Product) {
    this.router.navigateByUrl(`/editProduct/${product.id}`)
  }
}
