import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export const BASE_URI: string = 'http://localhost:8000/data';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  constructor(private httpClient: HttpClient) {}


  getFireflies(row: number, col: number): Observable<any> {
    return this.httpClient.get(BASE_URI + '/getFireflies?row=' + row + "&col=" + col);
  }

  start(): Observable<any> {
    return this.httpClient.get(BASE_URI + '/start');
  }

}
