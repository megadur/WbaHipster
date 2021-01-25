import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ILineInfo } from 'app/shared/model/line-info.model';
import { LineInfoService } from './line-info.service';

@Component({
  templateUrl: './line-info-delete-dialog.component.html',
})
export class LineInfoDeleteDialogComponent {
  lineInfo?: ILineInfo;

  constructor(protected lineInfoService: LineInfoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.lineInfoService.delete(id).subscribe(() => {
      this.eventManager.broadcast('lineInfoListModification');
      this.activeModal.close();
    });
  }
}
