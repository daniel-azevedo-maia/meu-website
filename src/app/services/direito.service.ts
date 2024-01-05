import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DireitoService {

  private apiUrl = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  listarPosts(): Observable<any> {
    return this.http.get(this.apiUrl + "post");
  }

  buscarPorId(id: number): Observable<any> {
    return this.http.get(this.apiUrl + "post/" + id);
  }

}
