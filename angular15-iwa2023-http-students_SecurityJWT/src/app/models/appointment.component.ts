import {Component,OnInit} from "@angular/core";
import {Appointment} from "./appointment.model";
import {Prescription} from "./prescription";
import {AppointmentService} from "./appointment.service";
import {Patient} from "../patient";
import {Doctor} from "../doctor/doctor.model";

@Component({
  selector: 'app-appointment',
  templateUrl: './appointment.component.html',
  styleUrls: ['./appointment.component.css']
})
export  class AppointmentComponent implements OnInit{
    appointments: Appointment[] = [];
    selectedAppointmentId: number = 0;
    prescriptions: Prescription[] = [];
    newAppointment: Appointment = new Appointment();
    patient: Patient = new Patient();
    doctor : Doctor = new Doctor();

    constructor(private appointmentService: AppointmentService) { }

    getAppointments(): void {
        this.appointmentService.getAppointmetns()
            .subscribe(appointmentList => this.appointments = appointmentList);
    }

    ngOnInit(): void {
    }

  add(date: string, time: string, description: string, patientId: string , doctorId: string): void {
        date = date.trim();
        time = time.trim();
        description = description.trim();

        this.patient = {id: Number(patientId)} as Patient;
        this.doctor = {id: Number(doctorId)} as Doctor;

        const patient = this.patient;
        const doctor = this.doctor;

        const appointment: Appointment = {
          date,
          time,
          description,
          patient,
          doctor
        }

        this.appointmentService.addAppointment(appointment)
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

  protected readonly Patient = Patient;
  protected readonly Doctor = Doctor;
}
