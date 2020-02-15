import { UserData, PublicUserData } from '../shared/user/user.data';
import { PersonalInfoData } from '../personal-info/personal-info.data';
import { SubjectData } from '../subjects/subject.data';
import { ProjectData } from '../projects/project.data';

export interface ProfileData {
    personalInfo: PersonalInfoData;
    subjects: SubjectData[];
    projects: ProjectData[];
}