import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IBngInfo, BngInfo } from 'app/shared/model/bng-info.model';
import { BngInfoService } from './bng-info.service';
import { BngInfoComponent } from './bng-info.component';
import { BngInfoDetailComponent } from './bng-info-detail.component';
import { BngInfoUpdateComponent } from './bng-info-update.component';

@Injectable({ providedIn: 'root' })
export class BngInfoResolve implements Resolve<IBngInfo> {
  constructor(private service: BngInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IBngInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((bngInfo: HttpResponse<BngInfo>) => {
          if (bngInfo.body) {
            return of(bngInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new BngInfo());
  }
}

export const bngInfoRoute: Routes = [
  {
    path: '',
    component: BngInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.bngInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: BngInfoDetailComponent,
    resolve: {
      bngInfo: BngInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.bngInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: BngInfoUpdateComponent,
    resolve: {
      bngInfo: BngInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.bngInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: BngInfoUpdateComponent,
    resolve: {
      bngInfo: BngInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.bngInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
