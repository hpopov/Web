import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { ProfileService } from '../profile/profile.service';
import { CleanableSubject } from '../utils/cleanable-subject';
import { ProjectData } from './project.data';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  projects: CleanableSubject<ProjectData[]>;
  // private projectsLoaded: boolean = false;


  constructor(private profileService: ProfileService) {
    this.projects = new CleanableSubject();
    this.bindProjectsToProfile();
  }

  private bindProjectsToProfile(): void {
    // this.projectsLoaded = true;
    this.profileService.profile.asObservable().pipe(
      map(pageData => pageData.projects)).subscribe(projects => {
        this.projects.next(projects);
      });
  }
}
