import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IBngInfo } from 'app/shared/model/bng-info.model';
import { BngInfoService } from './bng-info.service';

@Component({
  templateUrl: './bng-info-delete-dialog.component.html',
})
export class BngInfoDeleteDialogComponent {
  bngInfo?: IBngInfo;

  constructor(protected bngInfoService: BngInfoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.bngInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('bngInfoListModification');
      this.activeModal.close();
    });
  }
}
