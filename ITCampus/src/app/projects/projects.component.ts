import { Component, OnInit, OnDestroy, Input } from '@angular/core';
import { ProjectData } from './project.data';
import { ProjectService } from './project.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit, OnDestroy {

  @Input()public projects: ProjectData[];
  public maxProjectsPerPage: number;
  private projectsSubscription: Subscription;

  constructor(private projectService: ProjectService) {
    this.maxProjectsPerPage = 3;
  }

  ngOnInit() {
    this.projectsSubscription = this.projectService.getProjectsAsObservable().subscribe(projects => {
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

}
