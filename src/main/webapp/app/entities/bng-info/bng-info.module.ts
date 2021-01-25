import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WbaHipsterSharedModule } from 'app/shared/shared.module';
import { BngInfoComponent } from './bng-info.component';
import { BngInfoDetailComponent } from './bng-info-detail.component';
import { BngInfoUpdateComponent } from './bng-info-update.component';
import { BngInfoDeleteDialogComponent } from './bng-info-delete-dialog.component';
import { bngInfoRoute } from './bng-info.route';

@NgModule({
  imports: [WbaHipsterSharedModule, RouterModule.forChild(bngInfoRoute)],
  declarations: [BngInfoComponent, BngInfoDetailComponent, BngInfoUpdateComponent, BngInfoDeleteDialogComponent],
  entryComponents: [BngInfoDeleteDialogComponent],
})
export class WbaHipsterBngInfoModule {}
