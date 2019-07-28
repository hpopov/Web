import { Injectable } from '@angular/core';
import { ProjectData } from './project.data';
import { Observable } from 'rxjs';
import { ProfileService } from '../profile/profile.service';
import { map, filter } from 'rxjs/operators';
import { CleanableSubject } from '../utils/cleanable-subject';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private projectsSubject: CleanableSubject<ProjectData[]>;
  private projectsLoaded: boolean = false;
  

  constructor(private pageDataService: ProfileService) {
    this.projectsSubject = new CleanableSubject();
    this.bindProjectsToProfile();
  }

  private bindProjectsToProfile() : void {
    this.projectsLoaded = true;
    this.pageDataService.getProfile().pipe(
      map(pageData => pageData.projects)).subscribe(projects => {
        this.projectsSubject.next(projects);
      });
  }

  private getProjects(): CleanableSubject<ProjectData[]> {
    if (!this.projectsLoaded) {
      this.bindProjectsToProfile();
    }
    return this.projectsSubject;
  }

  public getProjectsAsObservable() : Observable<ProjectData[]> {
    return this.getProjects().asObservable();
  }
}
