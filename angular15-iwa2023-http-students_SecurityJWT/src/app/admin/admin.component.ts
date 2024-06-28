import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import { Appointment } from '../models/appointment.model';
import { Prescription } from '../models/prescription';
import {AdminService} from "../services/admin.service";
import {AppointmentService} from "../models/appointment.service";
@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {
 /* board?: string;
  errorMessage?: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getAdminPage().subscribe({
      next:(data) => {
        this.board = data;
      },
      error: (error) => {
        this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
      }
  });
  } */
  appointments: Appointment[] = [];
  selectedAppointmentId: number = 0;
  prescriptions: Prescription[] = [];
  newAppointment: Appointment = new Appointment();

  constructor(private appointmentService: AppointmentService) { }

   getAppointments(): void {
    this.appointmentService.getAppointmetns()
      .subscribe(appointmentList => this.appointments = appointmentList);
  }

  ngOnInit(): void {
  }

  add(date: string, time: string, description: string, patientId: number, doctorId: number): void {
    date = date.trim();
    time = time.trim();
    description = description.trim();

    this.appointmentService.addAppointment({date, time, description, patientId, doctorId} as Appointment)
      .subscribe({
        next: (appointment: Appointment) => {
          this.appointments?.push(appointment);
        },
        error: () => {
        },
        complete: () => {
          if (this.appointments != undefined) {
            this.appointmentService.totalItems.next(this.appointments.length);
            console.log(this.appointments.length);
          }
        }
      });
  }

  /*addAppointment(): void {
    this.adminService.addAppointment(this.newAppointment).subscribe(appointment => {
      this.appointments.push(appointment);
      this.newAppointment = new Appointment(); // Reset the form
    });
  }

  loadPrescriptions(): void {
    if (this.selectedAppointmentId) {
      this.adminService.getPrescriptionsByAppointment(this.selectedAppointmentId).subscribe(prescriptions => {
        this.prescriptions = prescriptions;
      });
    }
  }*/

  protected readonly String = String;
    protected readonly Number = Number;
}
