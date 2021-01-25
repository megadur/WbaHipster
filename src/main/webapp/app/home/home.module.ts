import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { WbaHipsterSharedModule } from 'app/shared/shared.module';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';

@NgModule({
  imports: [WbaHipsterSharedModule, RouterModule.forChild([HOME_ROUTE])],
  declarations: [HomeComponent],
})
export class WbaHipsterHomeModule {}
