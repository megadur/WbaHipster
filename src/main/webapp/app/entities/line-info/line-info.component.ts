import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ILineInfo } from 'app/shared/model/line-info.model';
import { LineInfoService } from './line-info.service';
import { LineInfoDeleteDialogComponent } from './line-info-delete-dialog.component';

@Component({
  selector: 'jhi-line-info',
  templateUrl: './line-info.component.html',
})
export class LineInfoComponent implements OnInit, OnDestroy {
  lineInfos?: ILineInfo[];
  eventSubscriber?: Subscription;

  constructor(protected lineInfoService: LineInfoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.lineInfoService.query().subscribe((res: HttpResponse<ILineInfo[]>) => (this.lineInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInLineInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ILineInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInLineInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('lineInfoListModification', () => this.loadAll());
  }

  delete(lineInfo: ILineInfo): void {
    const modalRef = this.modalService.open(LineInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.lineInfo = lineInfo;
  }
}
