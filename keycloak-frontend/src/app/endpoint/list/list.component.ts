import {LoginService} from './../../services/login.service';
import {Component, OnInit} from '@angular/core';
import {Endpoint} from '../../models/endpoint';
import {EndpointService} from '../../services/endpoint.service';

@Component({
  selector: 'app-list',
  templateUrl: './list.component.html',
  styleUrls: ['./list.component.css']
})
export class ListComponent implements OnInit {

  endpoints: Endpoint[] = [];

  isAdmin: boolean;

  constructor(private endpointService: EndpointService, private loginService: LoginService) { }

  ngOnInit(): void {
    this.loadEndpoints();
    this.isAdmin = this.loginService.getIsAdmin();
  }

  loadEndpoints(): void {
    this.endpointService.list().subscribe(
      data => {
        this.endpoints = data;
      },
      err => console.log(err)
    );
  }

  onDelete(id: number): void {
    this.endpointService.delete(id).subscribe(
      data => {
        console.log(data);
        this.loadEndpoints();
      },
      err => console.log(err)
    );
  }

}
