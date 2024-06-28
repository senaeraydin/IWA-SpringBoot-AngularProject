import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, catchError, forkJoin, Observable, of, tap} from "rxjs";
import {Appointment} from "./appointment.model";


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};
5
@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private appointmentUrl = 'http://localhost:8080/api/appointments';
  totalItems: any;

  constructor(private http: HttpClient) { }


  getAppointmetns(): Observable<Appointment[]> {
    return this.http.get<Appointment[]>(this.appointmentUrl);
  }
  addAppointment(appointment: Appointment): Observable<Appointment> {
    return this.http.post<Appointment>(this.appointmentUrl, appointment, httpOptions).pipe(
      tap((appointmentAdded: Appointment) => this.log(`added appointment id=${appointmentAdded.id}`)),
      catchError(this.handleError<Appointment>('addAppointment'))
    );
  }

  private log(message: string) {
    console.log('AppointmentService: ' + message);
  }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      // TODO: send the error to remote logging infrastructure
      console.error(error); // log to console instead

      // TODO: better job of transforming error for user consumption
      this.log(`${operation} failed: ${error.message}`);

      // Let the app keep running by returning an empty result.
      return of(result as T);
    };
  }
}
