/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PosteResponse } from '../../models/poste-response';

export interface GetPostesByOffre$Params {
  offreId: number;
}

export function getPostesByOffre(http: HttpClient, rootUrl: string, params: GetPostesByOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PosteResponse>>> {
  const rb = new RequestBuilder(rootUrl, getPostesByOffre.PATH, 'get');
  if (params) {
    rb.path('offreId', params.offreId, {});
  }

  return http.request(
    rb.build({ responseType: 'json', accept: 'application/json', context })
  ).pipe(
    filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
    map((r: HttpResponse<any>) => {
      return r as StrictHttpResponse<Array<PosteResponse>>;
    })
  );
}

getPostesByOffre.PATH = '/postes/offre/{offreId}';
