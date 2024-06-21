import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {JsonPipe} from "@angular/common";
import {ProductService} from "../services/product.service";
import {Product} from "../model/product.model";

@Component({
  selector: 'app-new-product',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    JsonPipe
  ],
  templateUrl: './new-product.component.html',
  styleUrl: './new-product.component.css'
})
export class NewProductComponent implements OnInit{

  public productFrom!:FormGroup;

  constructor(private fb:FormBuilder, private productservice:ProductService) {
  }
  ngOnInit() {
  this.productFrom=this.fb.group({
    name: this.fb.control('',[Validators.required]),
    price: this.fb.control(0),
    checked: this.fb.control(false),
  })
  }

  saveProduct() {
  let product:Product=this.productFrom.value;
  this.productservice.saveProduct(product).subscribe({
    next:data=>{
      alert(JSON.stringify(data));
    }, error:err=>{
      console.log(err);
    }
  });
  }
}
