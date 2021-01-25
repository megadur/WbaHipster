import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ILineInfo, LineInfo } from 'app/shared/model/line-info.model';
import { LineInfoService } from './line-info.service';
import { LineInfoComponent } from './line-info.component';
import { LineInfoDetailComponent } from './line-info-detail.component';
import { LineInfoUpdateComponent } from './line-info-update.component';

@Injectable({ providedIn: 'root' })
export class LineInfoResolve implements Resolve<ILineInfo> {
  constructor(private service: LineInfoService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ILineInfo> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((lineInfo: HttpResponse<LineInfo>) => {
          if (lineInfo.body) {
            return of(lineInfo.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new LineInfo());
  }
}

export const lineInfoRoute: Routes = [
  {
    path: '',
    component: LineInfoComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.lineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: LineInfoDetailComponent,
    resolve: {
      lineInfo: LineInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.lineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: LineInfoUpdateComponent,
    resolve: {
      lineInfo: LineInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.lineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: LineInfoUpdateComponent,
    resolve: {
      lineInfo: LineInfoResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'wbaHipsterApp.lineInfo.home.title',
    },
    canActivate: [UserRouteAccessService],
  },
];
