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
  data = new FormGroup({
    row: new FormControl(2),
    column: new FormControl(2),
    updateTime: new FormControl(100)
  });

  ngOnInit(): void {
  }

  start(data: any): void {
    this.apiService.start().subscribe(res=>{this.subscription = interval(data.updateTime).subscribe(() => 
      this.apiService.getFireflies(data.row, data.column).subscribe(res => {
        console.log(res),
        this.fireflies = res;
    }));})
      
    this.running = true;
  }



  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
