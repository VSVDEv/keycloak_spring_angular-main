import {ActivatedRoute, Router} from '@angular/router';
import {Component, OnInit} from '@angular/core';
import {Endpoint} from '../../models/endpoint';
import {EndpointService} from '../../services/endpoint.service';

@Component({
  selector: 'app-updat',
  templateUrl: './updat.component.html',
  styleUrls: ['./updat.component.css']
})
export class UpdatComponent implements OnInit {

  endpoint: Endpoint;

  constructor(
    private endpointService: EndpointService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.endpointService.detail(id).subscribe(
      data => {
        this.endpoint = data;
      },
      err => console.log(err)
    );
  }

  onUpdate(): void {
    const id = this.activatedRoute.snapshot.params.id;
    this.endpointService.update(id, this.endpoint).subscribe(
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
