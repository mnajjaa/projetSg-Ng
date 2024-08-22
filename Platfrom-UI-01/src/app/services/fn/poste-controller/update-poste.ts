/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PosteRequest } from '../../models/poste-request';
import { PosteResponse } from '../../models/poste-response';

export interface UpdatePoste$Params {
  id: number;
      body: PosteRequest
}

export function updatePoste(http: HttpClient, rootUrl: string, params: UpdatePoste$Params, context?: HttpContext): Observable<StrictHttpResponse<PosteResponse>> {
  const rb = new RequestBuilder(rootUrl, updatePoste.PATH, 'put');
  if (params) {
    rb.path('id', params.id, {});
    rb.body(params.body, 'application/json');
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

updatePoste.PATH = '/postes/{id}';
