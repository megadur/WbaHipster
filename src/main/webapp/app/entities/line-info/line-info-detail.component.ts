import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ILineInfo } from 'app/shared/model/line-info.model';

@Component({
  selector: 'jhi-line-info-detail',
  templateUrl: './line-info-detail.component.html',
})
export class LineInfoDetailComponent implements OnInit {
  lineInfo: ILineInfo | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lineInfo }) => (this.lineInfo = lineInfo));
  }

  previousState(): void {
    window.history.back();
  }
}
