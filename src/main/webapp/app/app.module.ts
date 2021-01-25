import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { WbaHipsterSharedModule } from 'app/shared/shared.module';
import { WbaHipsterCoreModule } from 'app/core/core.module';
import { WbaHipsterAppRoutingModule } from './app-routing.module';
import { WbaHipsterHomeModule } from './home/home.module';
import { WbaHipsterEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';

@NgModule({
  imports: [
    BrowserModule,
    WbaHipsterSharedModule,
    WbaHipsterCoreModule,
    WbaHipsterHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    WbaHipsterEntityModule,
    WbaHipsterAppRoutingModule,
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent],
})
export class WbaHipsterAppModule {}
