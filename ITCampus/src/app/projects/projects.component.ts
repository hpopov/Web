import { Component, OnInit } from '@angular/core';
import { ProjectData } from './project.data';
import { ProjectService } from './project.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.scss']
})
export class ProjectsComponent implements OnInit {

  projects: ProjectData[];
  maxProjectsPerPage: number;

  constructor(private projectService: ProjectService) {
    this.projects = [];
    this.maxProjectsPerPage = 3;
    projectService.getProjectsObservable().subscribe(projects => {
      this.projects = projects;
    });
  }

  ngOnInit() {
  }

  showMoreProjects(): void {
    this.maxProjectsPerPage += 3;
  }

  showLessProjects(): void {
    this.maxProjectsPerPage -= 3;
  }

}
