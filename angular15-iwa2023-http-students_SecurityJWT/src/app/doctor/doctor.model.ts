import {Appointment} from "../models/appointment.model";
import {User} from "../usermodel/user.model";
import {Prescription} from "../models/prescription";

export class Doctor{
  id?: number;
  name?: string;
  speciality?:string;
  user?:User;
  appointment?: Appointment;
  prescription?: Prescription;
}
