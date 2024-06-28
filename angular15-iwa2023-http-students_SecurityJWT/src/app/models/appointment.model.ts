import {Patient} from "../patient";
import {Doctor} from "../doctor/doctor.model";

export class Appointment {
  id?: number;
  date?: string;
  time?:string;
  description?:string;
  patient?: Patient;
  doctor?: Doctor;
}
