/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createPoste } from '../fn/poste-controller/create-poste';
import { CreatePoste$Params } from '../fn/poste-controller/create-poste';
import { deletePoste } from '../fn/poste-controller/delete-poste';
import { DeletePoste$Params } from '../fn/poste-controller/delete-poste';
import { getAllPostes } from '../fn/poste-controller/get-all-postes';
import { GetAllPostes$Params } from '../fn/poste-controller/get-all-postes';
import { getPosteById } from '../fn/poste-controller/get-poste-by-id';
import { GetPosteById$Params } from '../fn/poste-controller/get-poste-by-id';
import { getPostesByOffre } from '../fn/poste-controller/get-postes-by-offre';
import { GetPostesByOffre$Params } from '../fn/poste-controller/get-postes-by-offre';
import { PosteResponse } from '../models/poste-response';
import { updatePoste } from '../fn/poste-controller/update-poste';
import { UpdatePoste$Params } from '../fn/poste-controller/update-poste';

@Injectable({ providedIn: 'root' })
export class PosteControllerService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getPosteById()` */
  static readonly GetPosteByIdPath = '/postes/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getPosteById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPosteById$Response(params: GetPosteById$Params, context?: HttpContext): Observable<StrictHttpResponse<PosteResponse>> {
    return getPosteById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getPosteById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPosteById(params: GetPosteById$Params, context?: HttpContext): Observable<PosteResponse> {
    return this.getPosteById$Response(params, context).pipe(
      map((r: StrictHttpResponse<PosteResponse>): PosteResponse => r.body)
    );
  }

  /** Path part for operation `updatePoste()` */
  static readonly UpdatePostePath = '/postes/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updatePoste()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePoste$Response(params: UpdatePoste$Params, context?: HttpContext): Observable<StrictHttpResponse<PosteResponse>> {
    return updatePoste(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updatePoste$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updatePoste(params: UpdatePoste$Params, context?: HttpContext): Observable<PosteResponse> {
    return this.updatePoste$Response(params, context).pipe(
      map((r: StrictHttpResponse<PosteResponse>): PosteResponse => r.body)
    );
  }

  /** Path part for operation `deletePoste()` */
  static readonly DeletePostePath = '/postes/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deletePoste()` instead.
   *
   * This method doesn't expect any request body.
   */
  deletePoste$Response(params: DeletePoste$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deletePoste(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deletePoste$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deletePoste(params: DeletePoste$Params, context?: HttpContext): Observable<void> {
    return this.deletePoste$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `createPoste()` */
  static readonly CreatePostePath = '/postes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createPoste()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createPoste$Response(params: CreatePoste$Params, context?: HttpContext): Observable<StrictHttpResponse<PosteResponse>> {
    return createPoste(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createPoste$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createPoste(params: CreatePoste$Params, context?: HttpContext): Observable<PosteResponse> {
    return this.createPoste$Response(params, context).pipe(
      map((r: StrictHttpResponse<PosteResponse>): PosteResponse => r.body)
    );
  }

  /** Path part for operation `getPostesByOffre()` */
  static readonly GetPostesByOffrePath = '/postes/offre/{offreId}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getPostesByOffre()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPostesByOffre$Response(params: GetPostesByOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PosteResponse>>> {
    return getPostesByOffre(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getPostesByOffre$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getPostesByOffre(params: GetPostesByOffre$Params, context?: HttpContext): Observable<Array<PosteResponse>> {
    return this.getPostesByOffre$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<PosteResponse>>): Array<PosteResponse> => r.body)
    );
  }

  /** Path part for operation `getAllPostes()` */
  static readonly GetAllPostesPath = '/postes/AllPostes';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllPostes()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllPostes$Response(params?: GetAllPostes$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<PosteResponse>>> {
    return getAllPostes(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllPostes$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllPostes(params?: GetAllPostes$Params, context?: HttpContext): Observable<Array<PosteResponse>> {
    return this.getAllPostes$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<PosteResponse>>): Array<PosteResponse> => r.body)
    );
  }

}
