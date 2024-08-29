import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PostSummaryDTO } from 'src/app/shared/models/post-summary.dto';

@Injectable({
  providedIn: 'root'
})
export class PostService {

  private apiUrl = 'http://localhost:8080/api/v1/posts';

  constructor(private http: HttpClient) { }

  getPosts(): Observable<PostSummaryDTO[]> {
    return this.http.get<PostSummaryDTO[]>(this.apiUrl);
  }

}
