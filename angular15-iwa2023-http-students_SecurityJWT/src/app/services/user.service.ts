import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import { Appointment } from '../models/appointment.model';
import { Prescription } from '../models/prescription';
@Injectable({
  providedIn: 'root'
})
export class UserService {

  private userUrl = 'http://localhost:8080/exampleSecurity/user';
  private adminUrl = 'http://localhost:8080/exampleSecurity/admin';

  private baseUrl = 'http://localhost:8080/api/appointments';
  constructor(private http: HttpClient) { }

  getUserPage(): Observable<string> {
    return this.http.get(this.userUrl, { responseType: 'text' });
  }

  getAdminPage(): Observable<string> {
    return this.http.get(this.adminUrl, { responseType: 'text' });
  }

  getUserAppointments(username: string): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(`${this.baseUrl}/appointments?username=${username}`);
  }

  getUserPrescriptions(username: string): Observable<Prescription[]> {
    return this.http.get<Prescription[]>(`${this.baseUrl}/prescriptions?username=${username}`);
  }
}
