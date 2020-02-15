import { PersonalInfoData } from '../personal-info/personal-info.data';
import { ProjectData } from '../projects/project.data';
import { SubjectData } from '../subjects/subject.data';

export interface ProfileData {
    personalInfo: PersonalInfoData;
    subjects: SubjectData[];
    projects: ProjectData[];
}