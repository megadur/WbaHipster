import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'bng-info',
        loadChildren: () => import('./bng-info/bng-info.module').then(m => m.WbaHipsterBngInfoModule),
      },
      {
        path: 'line-info',
        loadChildren: () => import('./line-info/line-info.module').then(m => m.WbaHipsterLineInfoModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class WbaHipsterEntityModule {}
