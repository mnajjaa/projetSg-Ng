/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PosteResponse } from '../../models/poste-response';

export interface GetPosteById$Params {
  id: number;
}

export function getPosteById(http: HttpClient, rootUrl: string, params: GetPosteById$Params, context?: HttpContext): Observable<StrictHttpResponse<PosteResponse>> {
  const rb = new RequestBuilder(rootUrl, getPosteById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<PosteResponse>;
    })
  );
}

getPosteById.PATH = '/postes/{id}';
