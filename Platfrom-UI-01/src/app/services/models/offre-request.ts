/* tslint:disable */
/* eslint-disable */
import { PosteRequest } from '../models/poste-request';
export interface OffreRequest {
  description: string;
  nomOffre: string;
  poste: Array<PosteRequest>;
}
