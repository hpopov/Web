import { UserData } from '../user.data';
import { PersonalInfoData } from '../personal-info/personal-info.data';
import { SubjectData } from '../subjects/subject.data';
import { ProjectData } from '../projects/project.data';

export interface PageData {
    user: UserData;
    personalInfo: PersonalInfoData;
    subjects: SubjectData[];
    projects: ProjectData[];
    currentUser?: UserData;
}