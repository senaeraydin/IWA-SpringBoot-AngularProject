import {Injectable} from '@angular/core'
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Patient} from "./patient";

@Injectable({
  providedIn:'root'
})
export  class PatientService{
  constructor(private httpClient:HttpClient) {
  }

  private baseUrl = "http://localhost:8080/api/patients"

  getPatientList():Observable<Patient[]>{
    return this.httpClient.get<Patient[]>('')
  }
}
