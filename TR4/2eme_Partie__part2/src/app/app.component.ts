import { Component } from '@angular/core';
import {RouterLink, RouterOutlet} from '@angular/router';
import {NgForOf} from "@angular/common";
import { FormsModule,ReactiveFormsModule } from '@angular/forms';


@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, NgForOf,FormsModule,ReactiveFormsModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = '2eme_Partie_Part1';
  actions :Array<any> =[
    {title :"Home", "route":"/home", icon : "house"},
    {title :"Products", "route":"/products", icon : "search"},
    {title :"New Product", "route":"/newProduct", icon : "plus-circle"}

  ];
  currentAction : any
  setCurrentAction(action: any){
    this.currentAction = action;

}
}
