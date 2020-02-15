import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { WebRequestService } from '../web-request.service';
import { ProjectData } from './project.data';
import { ProjectService } from './project.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit, OnDestroy {

  @Input()public projects: ProjectData[];
  public maxProjectsPerPage: number;
  public minProjectsPerPage: number = 3;
  private projectsSubscription: Subscription;

  constructor(private projectService: ProjectService, private webRequestService: WebRequestService) {
    this.maxProjectsPerPage = this.minProjectsPerPage;
  }

  ngOnInit() {
    this.projectsSubscription = this.projectService.projects.asObservable().subscribe(projects => {
      this.projects = projects;
    });
  }

  ngOnDestroy(): void {
    this.projectsSubscription.unsubscribe();
  }

  showMoreProjects(): void {
    this.maxProjectsPerPage += 3;
  }

  showLessProjects(): void {
    this.maxProjectsPerPage -= 3;
  }

  resolve(projectImageUrl: string): string {
    return this.webRequestService.resolveUrl(projectImageUrl);
  }

}
