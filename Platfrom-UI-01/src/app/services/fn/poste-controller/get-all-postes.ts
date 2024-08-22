/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../../strict-http-response';
import { RequestBuilder } from '../../request-builder';

import { PosteResponse } from '../../models/poste-response';

export interface GetAllPostes$Params {
}

export function getAllPostes(http: HttpClient, rootUrl: string, params?: GetAllPostes$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PosteResponse>>> {
  const rb = new RequestBuilder(rootUrl, getAllPostes.PATH, 'get');
  if (params) {
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

getAllPostes.PATH = '/postes/AllPostes';
