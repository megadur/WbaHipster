import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IBngInfo } from 'app/shared/model/bng-info.model';
import { BngInfoService } from './bng-info.service';
import { BngInfoDeleteDialogComponent } from './bng-info-delete-dialog.component';

@Component({
  selector: 'jhi-bng-info',
  templateUrl: './bng-info.component.html',
})
export class BngInfoComponent implements OnInit, OnDestroy {
  bngInfos?: IBngInfo[];
  eventSubscriber?: Subscription;

  constructor(protected bngInfoService: BngInfoService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.bngInfoService.query().subscribe((res: HttpResponse<IBngInfo[]>) => (this.bngInfos = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInBngInfos();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IBngInfo): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInBngInfos(): void {
    this.eventSubscriber = this.eventManager.subscribe('bngInfoListModification', () => this.loadAll());
  }

  delete(bngInfo: IBngInfo): void {
    const modalRef = this.modalService.open(BngInfoDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.bngInfo = bngInfo;
  }
}
