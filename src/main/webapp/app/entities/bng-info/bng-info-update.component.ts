import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBngInfo, BngInfo } from 'app/shared/model/bng-info.model';
import { BngInfoService } from './bng-info.service';

@Component({
  selector: 'jhi-bng-info-update',
  templateUrl: './bng-info-update.component.html',
})
export class BngInfoUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    endSz: [],
    lineId: [],
    portName: [],
    tsLastQuery: [],
    tsLastQuerySuccess: [],
  });

  constructor(protected bngInfoService: BngInfoService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bngInfo }) => {
      this.updateForm(bngInfo);
    });
  }

  updateForm(bngInfo: IBngInfo): void {
    this.editForm.patchValue({
      id: bngInfo.id,
      endSz: bngInfo.endSz,
      lineId: bngInfo.lineId,
      portName: bngInfo.portName,
      tsLastQuery: bngInfo.tsLastQuery,
      tsLastQuerySuccess: bngInfo.tsLastQuerySuccess,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bngInfo = this.createFromForm();
    if (bngInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.bngInfoService.update(bngInfo));
    } else {
      this.subscribeToSaveResponse(this.bngInfoService.create(bngInfo));
    }
  }

  private createFromForm(): IBngInfo {
    return {
      ...new BngInfo(),
      id: this.editForm.get(['id'])!.value,
      endSz: this.editForm.get(['endSz'])!.value,
      lineId: this.editForm.get(['lineId'])!.value,
      portName: this.editForm.get(['portName'])!.value,
      tsLastQuery: this.editForm.get(['tsLastQuery'])!.value,
      tsLastQuerySuccess: this.editForm.get(['tsLastQuerySuccess'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBngInfo>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
