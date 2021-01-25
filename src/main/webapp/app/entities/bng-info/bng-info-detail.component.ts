import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IBngInfo } from 'app/shared/model/bng-info.model';

@Component({
  selector: 'jhi-bng-info-detail',
  templateUrl: './bng-info-detail.component.html',
})
export class BngInfoDetailComponent implements OnInit {
  bngInfo: IBngInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bngInfo }) => (this.bngInfo = bngInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
