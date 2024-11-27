import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export const BASE_URI: string = 'http://localhost:8000/data';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}


  getFireflies(): Observable<any> {
    return this.httpClient.get(BASE_URI + '/getFireflies?host=localhost&port1=50051&port2=50052&port3=50053');
  }

}
