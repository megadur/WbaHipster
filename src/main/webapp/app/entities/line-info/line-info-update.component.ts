import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ILineInfo, LineInfo } from 'app/shared/model/line-info.model';
import { LineInfoService } from './line-info.service';
import { IBngInfo } from 'app/shared/model/bng-info.model';
import { BngInfoService } from 'app/entities/bng-info/bng-info.service';

@Component({
  selector: 'jhi-line-info-update',
  templateUrl: './line-info-update.component.html',
})
export class LineInfoUpdateComponent implements OnInit {
  isSaving = false;
  bnginfos: IBngInfo[] = [];

  editForm = this.fb.group({
    id: [],
    lineId: [],
    uplinkPort: [],
    ipAddress: [],
    tsLastQuery: [],
    tsLastQuerySuccess: [],
    bngInfo: [],
  });

  constructor(
    protected lineInfoService: LineInfoService,
    protected bngInfoService: BngInfoService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ lineInfo }) => {
      this.updateForm(lineInfo);

      this.bngInfoService.query().subscribe((res: HttpResponse<IBngInfo[]>) => (this.bnginfos = res.body || []));
    });
  }

  updateForm(lineInfo: ILineInfo): void {
    this.editForm.patchValue({
      id: lineInfo.id,
      lineId: lineInfo.lineId,
      uplinkPort: lineInfo.uplinkPort,
      ipAddress: lineInfo.ipAddress,
      tsLastQuery: lineInfo.tsLastQuery,
      tsLastQuerySuccess: lineInfo.tsLastQuerySuccess,
      bngInfo: lineInfo.bngInfo,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const lineInfo = this.createFromForm();
    if (lineInfo.id !== undefined) {
      this.subscribeToSaveResponse(this.lineInfoService.update(lineInfo));
    } else {
      this.subscribeToSaveResponse(this.lineInfoService.create(lineInfo));
    }
  }

  private createFromForm(): ILineInfo {
    return {
      ...new LineInfo(),
      id: this.editForm.get(['id'])!.value,
      lineId: this.editForm.get(['lineId'])!.value,
      uplinkPort: this.editForm.get(['uplinkPort'])!.value,
      ipAddress: this.editForm.get(['ipAddress'])!.value,
      tsLastQuery: this.editForm.get(['tsLastQuery'])!.value,
      tsLastQuerySuccess: this.editForm.get(['tsLastQuerySuccess'])!.value,
      bngInfo: this.editForm.get(['bngInfo'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ILineInfo>>): void {
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

  trackById(index: number, item: IBngInfo): any {
    return item.id;
  }
}
