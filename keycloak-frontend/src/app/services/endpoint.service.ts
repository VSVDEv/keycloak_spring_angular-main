import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Endpoint} from '../models/endpoint';

@Injectable({
  providedIn: 'root'
})
export class EndpointService {

  endpointUrl = 'http://localhost:8888/endpoint/';

  httpOptions = { headers: new HttpHeaders({'Content-Type' : 'application/json'})};

  constructor(private httpClient: HttpClient) { }

    public list(): Observable<Endpoint[]> {
    return this.httpClient.get<Endpoint[]>(this.endpointUrl + 'list', this.httpOptions);
  }

  public detail(id: number): Observable<Endpoint> {
    return this.httpClient.get<Endpoint>(this.endpointUrl + `detail/${id}`, this.httpOptions);
  }

  public create(endpoint: Endpoint): Observable<any> {
    return this.httpClient.post<any>(this.endpointUrl + 'create', endpoint, this.httpOptions);
  }

  public update(id: number, foo: Endpoint): Observable<any> {
    return this.httpClient.put<any>(this.endpointUrl + `update/${id}`, foo, this.httpOptions);
  }

  public delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(this.endpointUrl + `delete/${id}`, this.httpOptions);
  }
}
