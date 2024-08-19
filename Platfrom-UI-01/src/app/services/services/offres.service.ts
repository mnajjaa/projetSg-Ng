/* tslint:disable */
/* eslint-disable */
import { HttpClient, HttpContext } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';

import { BaseService } from '../base-service';
import { ApiConfiguration } from '../api-configuration';
import { StrictHttpResponse } from '../strict-http-response';

import { createOffre } from '../fn/offres/create-offre';
import { CreateOffre$Params } from '../fn/offres/create-offre';
import { deleteOffre } from '../fn/offres/delete-offre';
import { DeleteOffre$Params } from '../fn/offres/delete-offre';
import { getAllOffres } from '../fn/offres/get-all-offres';
import { GetAllOffres$Params } from '../fn/offres/get-all-offres';
import { getAllOffresByRecruter } from '../fn/offres/get-all-offres-by-recruter';
import { GetAllOffresByRecruter$Params } from '../fn/offres/get-all-offres-by-recruter';
import { getOffreById } from '../fn/offres/get-offre-by-id';
import { GetOffreById$Params } from '../fn/offres/get-offre-by-id';
import { getOffreName } from '../fn/offres/get-offre-name';
import { GetOffreName$Params } from '../fn/offres/get-offre-name';
import { OffreResponse } from '../models/offre-response';
import { updateOffre } from '../fn/offres/update-offre';
import { UpdateOffre$Params } from '../fn/offres/update-offre';

@Injectable({ providedIn: 'root' })
export class OffresService extends BaseService {
  constructor(config: ApiConfiguration, http: HttpClient) {
    super(config, http);
  }

  /** Path part for operation `getOffreById()` */
  static readonly GetOffreByIdPath = '/offres/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getOffreById()` instead.
   *
   * This method doesn't expect any request body.
   */
  getOffreById$Response(params: GetOffreById$Params, context?: HttpContext): Observable<StrictHttpResponse<OffreResponse>> {
    return getOffreById(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getOffreById$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getOffreById(params: GetOffreById$Params, context?: HttpContext): Observable<OffreResponse> {
    return this.getOffreById$Response(params, context).pipe(
      map((r: StrictHttpResponse<OffreResponse>): OffreResponse => r.body)
    );
  }

  /** Path part for operation `updateOffre()` */
  static readonly UpdateOffrePath = '/offres/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `updateOffre()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateOffre$Response(params: UpdateOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<OffreResponse>> {
    return updateOffre(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `updateOffre$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  updateOffre(params: UpdateOffre$Params, context?: HttpContext): Observable<OffreResponse> {
    return this.updateOffre$Response(params, context).pipe(
      map((r: StrictHttpResponse<OffreResponse>): OffreResponse => r.body)
    );
  }

  /** Path part for operation `deleteOffre()` */
  static readonly DeleteOffrePath = '/offres/{id}';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `deleteOffre()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteOffre$Response(params: DeleteOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<void>> {
    return deleteOffre(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `deleteOffre$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  deleteOffre(params: DeleteOffre$Params, context?: HttpContext): Observable<void> {
    return this.deleteOffre$Response(params, context).pipe(
      map((r: StrictHttpResponse<void>): void => r.body)
    );
  }

  /** Path part for operation `getAllOffres()` */
  static readonly GetAllOffresPath = '/offres';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllOffres()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllOffres$Response(params?: GetAllOffres$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<OffreResponse>>> {
    return getAllOffres(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllOffres$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllOffres(params?: GetAllOffres$Params, context?: HttpContext): Observable<Array<OffreResponse>> {
    return this.getAllOffres$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<OffreResponse>>): Array<OffreResponse> => r.body)
    );
  }

  /** Path part for operation `createOffre()` */
  static readonly CreateOffrePath = '/offres';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `createOffre()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createOffre$Response(params: CreateOffre$Params, context?: HttpContext): Observable<StrictHttpResponse<number>> {
    return createOffre(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `createOffre$Response()` instead.
   *
   * This method sends `application/json` and handles request body of type `application/json`.
   */
  createOffre(params: CreateOffre$Params, context?: HttpContext): Observable<number> {
    return this.createOffre$Response(params, context).pipe(
      map((r: StrictHttpResponse<number>): number => r.body)
    );
  }

  /** Path part for operation `getOffreName()` */
  static readonly GetOffreNamePath = '/offres/{id}/name';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getOffreName()` instead.
   *
   * This method doesn't expect any request body.
   */
  getOffreName$Response(params: GetOffreName$Params, context?: HttpContext): Observable<StrictHttpResponse<string>> {
    return getOffreName(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getOffreName$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getOffreName(params: GetOffreName$Params, context?: HttpContext): Observable<string> {
    return this.getOffreName$Response(params, context).pipe(
      map((r: StrictHttpResponse<string>): string => r.body)
    );
  }

  /** Path part for operation `getAllOffresByRecruter()` */
  static readonly GetAllOffresByRecruterPath = '/offres/recruter';

  /**
   * This method provides access to the full `HttpResponse`, allowing access to response headers.
   * To access only the response body, use `getAllOffresByRecruter()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllOffresByRecruter$Response(params?: GetAllOffresByRecruter$Params, context?: HttpContext): Observable<StrictHttpResponse<Array<OffreResponse>>> {
    return getAllOffresByRecruter(this.http, this.rootUrl, params, context);
  }

  /**
   * This method provides access only to the response body.
   * To access the full response (for headers, for example), `getAllOffresByRecruter$Response()` instead.
   *
   * This method doesn't expect any request body.
   */
  getAllOffresByRecruter(params?: GetAllOffresByRecruter$Params, context?: HttpContext): Observable<Array<OffreResponse>> {
    return this.getAllOffresByRecruter$Response(params, context).pipe(
      map((r: StrictHttpResponse<Array<OffreResponse>>): Array<OffreResponse> => r.body)
    );
  }

}
