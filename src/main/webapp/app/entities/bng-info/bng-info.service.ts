import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IBngInfo } from 'app/shared/model/bng-info.model';

type EntityResponseType = HttpResponse<IBngInfo>;
type EntityArrayResponseType = HttpResponse<IBngInfo[]>;

@Injectable({ providedIn: 'root' })
export class BngInfoService {
  public resourceUrl = SERVER_API_URL + 'api/bng-infos';

  constructor(protected http: HttpClient) {}

  create(bngInfo: IBngInfo): Observable<EntityResponseType> {
    return this.http.post<IBngInfo>(this.resourceUrl, bngInfo, { observe: 'response' });
  }

  update(bngInfo: IBngInfo): Observable<EntityResponseType> {
    return this.http.put<IBngInfo>(this.resourceUrl, bngInfo, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IBngInfo>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IBngInfo[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
