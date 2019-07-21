import { Injectable } from '@angular/core';
import { ProjectData } from './project.data';
import { Observable } from 'rxjs';
import { PageDataService } from '../page-web.service';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ProjectService {
  private projectsObservable: Observable<ProjectData[]>;

  constructor(private pageDataService: PageDataService) {
    this.projectsObservable = pageDataService.getObservablePageData().pipe(
      map(pageData => pageData.projects));
  }

  public getProjectsObservable(): Observable<ProjectData[]> {
    return this.projectsObservable;
  }
}
