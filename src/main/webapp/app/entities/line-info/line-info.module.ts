import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WbaHipsterSharedModule } from 'app/shared/shared.module';
import { LineInfoComponent } from './line-info.component';
import { LineInfoDetailComponent } from './line-info-detail.component';
import { LineInfoUpdateComponent } from './line-info-update.component';
import { LineInfoDeleteDialogComponent } from './line-info-delete-dialog.component';
import { lineInfoRoute } from './line-info.route';

@NgModule({
  imports: [WbaHipsterSharedModule, RouterModule.forChild(lineInfoRoute)],
  declarations: [LineInfoComponent, LineInfoDetailComponent, LineInfoUpdateComponent, LineInfoDeleteDialogComponent],
  entryComponents: [LineInfoDeleteDialogComponent],
})
export class WbaHipsterLineInfoModule {}
