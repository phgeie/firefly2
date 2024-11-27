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
    coupling: new FormControl(0.99),
    row: new FormControl(2),
    column: new FormControl(2),
    updateTime: new FormControl(100)
  });

  ngOnInit(): void {
      this.fireflies[0] = 0;
      this.fireflies[1] = 0;
      this.fireflies[2] = 0;
      this.fireflies[3] = 0;
  }

  start(data: any): void {
      this.subscription = interval(data.updateTime).subscribe(() => 
      this.apiService.getFireflies().subscribe(res => {
        console.log(res),
        this.fireflies[0] = res[0];
        this.fireflies[1] = res[1];
        this.fireflies[2] = res[2];
        this.fireflies[3] = res[3];
    }));
    this.running = true;
  }


  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

}
