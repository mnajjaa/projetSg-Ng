/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { OffreResponse } from '../../models/offre-response';

export interface GetOffreById$Params {
  id: number;
}

export function getOffreById(http: HttpClient, rootUrl: string, params: GetOffreById$Params, context?: HttpContext): Observable<StrictHttpResponse<OffreResponse>> {
  const rb = new RequestBuilder(rootUrl, getOffreById.PATH, 'get');
  if (params) {
    rb.path('id', params.id, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<OffreResponse>;
    })
  );
}

getOffreById.PATH = '/offres/{id}';
