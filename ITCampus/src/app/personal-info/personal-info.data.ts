import { EducationData } from './education.data';
import { LanguageLevelData } from './language-level.data';

export interface PersonalInfoData {
  phoneNumber: string;
  dateOfBirth: Date;
  city: string;
  // education: EducationData;
  education: string
  faculty: string;
  educationYear: [number, number];
  skills: [string, string][];
  languages: [string, string][];
}