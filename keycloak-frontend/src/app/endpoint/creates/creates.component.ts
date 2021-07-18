import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {Endpoint} from '../../models/endpoint';
import {EndpointService} from '../../services/endpoint.service';

@Component({
  selector: 'app-creates',
  templateUrl: './creates.component.html',
  styleUrls: ['./creates.component.css']
})
export class CreatesComponent implements OnInit {

  endpoint: Endpoint;
  endpointName: string;

  constructor(
    private endpointService: EndpointService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  onCreate(): void {
    this.endpoint = new Endpoint(null, this.endpointName);
    this.endpointService.create(this.endpoint).subscribe(
      data => {
        console.log(data);
        this.volver();
      },
      err => console.log(err)
    );
  }

  volver(): void {
    this.router.navigate(['/list']);
  }


}
