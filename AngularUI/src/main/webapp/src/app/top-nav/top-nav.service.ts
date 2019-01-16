import { Injectable } from '@angular/core';
import { Observable } from "rxjs";
import { of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Urls,APIURLS } from '../app.constants';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable()
export class TopNavService {
  constructor(private http: HttpClient) {
  } 

  getUserName(): Observable<string> {
   // var url = Urls
    //.concat(APIURLS.user).concat("/getUserInfo");
      //console.log("getMinProfomaDate url : " + url);
      let options= { responseType: 'text' as 'json'};
    return this.http.get<string>(APIURLS.getUserInfoUrl, options );
  }

  
}