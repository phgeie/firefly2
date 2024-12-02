import { Component, OnDestroy, OnInit } from '@angular/core';
import {Router, RouterLink} from "@angular/router";
import { interval, Subscription } from 'rxjs';
import { ApiService } from '../services/api.service';import {
  FormGroup,
  FormControl,
  FormsModule,
  ReactiveFormsModule,
} from '@angular/forms';

@Component({
  selector: 'app-start',
  standalone: true,
  imports: [FormsModule, ReactiveFormsModule],
  templateUrl: './start.component.html',
  styleUrl: './start.component.scss'
})
export class StartComponent implements OnInit,OnDestroy{

  constructor(private router: Router,
    private apiService: ApiService
  ) {
  }
// Initialisiere das Array mit der Größe 10
  private subscription!: Subscription;
  fireflies: any[] = [];
  
  running: boolean = false;
  row: number = 0;
  column: number = 0;
  zoomLevel: number = 1;

  ngOnInit(): void {
  }

  start(): void {
    this.apiService.start().subscribe(res=>{
      this.row = res[0];
      this.column = res[1];
      this.subscription = interval(res[2]).subscribe(() => 
      this.apiService.getFireflies(res[0], res[1]).subscribe(res => {
        this.fireflies = res;
    }));})
      
    this.running = true;
  }

  zoomIn() {
    this.zoomLevel = Math.min(this.zoomLevel + 0.1, 3); // Maximal 300%
  }

  zoomOut() {
    this.zoomLevel = Math.max(this.zoomLevel - 0.1, 0.1); // Minimal 10%
  }



  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
