import { Injectable } from '@angular/core';
import {Product} from "../model/product.model";

@Injectable({
  providedIn: 'root'
})
export class AppStateService {
  public productState: any = {
    products :[],
    keyword:'',
    totalPages:0,
    pageSize:3,
    currentPage:1,
    totalProducts:0,
    status:"",
    errorMessage:""
  }
  constructor() { }

  public setProductState(state:any):void {
    //lorsqu'on fait les 3 points : càd qu'on va copier tous les attributs de l'état vers une autre copie
    this.productState={...this.productState, ...state}
  }
}
