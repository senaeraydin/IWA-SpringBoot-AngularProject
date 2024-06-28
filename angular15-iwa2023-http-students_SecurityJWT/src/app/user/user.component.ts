import { Component, OnInit } from '@angular/core';
import {UserService} from '../services/user.service';
import { TokenStorageService } from '../auth/token-storage.service';
import { Appointment } from '../models/appointment.model';
import { Prescription } from '../models/prescription';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {
  board?: string;
  errorMessage?: string;
  appointments: Appointment[] = [];
  prescriptions: Prescription[] = [];
  username: string = '';

  /*constructor(private userService: UserService) { }

  ngOnInit() {
    this.userService.getUserPage().subscribe({
      next: (data) =>
    {
      this.board = data;
    }
  ,
    error: (error) => {
      this.errorMessage = `${error.status}: ${JSON.parse(error.error).message}`;
    }
  });
  }*/

  constructor(
    private userService: UserService,
    private tokenStorage: TokenStorageService
  ) { }

  ngOnInit(): void {
    this.username = this.tokenStorage.getUsername();
    this.loadUserData();
  }

  loadUserData(): void {
    this.userService.getUserAppointments(this.username).subscribe(appointments => {
      this.appointments = appointments;
    });

    this.userService.getUserPrescriptions(this.username).subscribe(prescriptions => {
      this.prescriptions = prescriptions;
    });
  }
}
