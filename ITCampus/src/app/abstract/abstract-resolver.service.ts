import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { Subscription, Subject, Observable } from 'rxjs';
import { CleanableSubject } from '../utils/cleanable-subject';
import { take, tap } from 'rxjs/operators';




export abstract class AbstractResolver<Model> implements Resolve<Model> {

  // private valueSubject: Subject<Model>;
  // private valueSubscription: Subscription;

  constructor() {
  }
  
  public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot)
      : Model | Observable<Model> | Promise<Model> {
    const valueAsSubject : CleanableSubject<Model> = this.getValueAsSubject();

    // if (this.valueSubscription) {
    //   this.valueSubscription.unsubscribe();
    // }
    // if (valueAsSubject.getValue()) {
    //   return valueAsSubject.getValue();
    // }
    // this.valueSubject = new Subject();
    // this.valueSubscription= 
    return valueAsSubject.asObservable().pipe(take(1), tap(console.log));
    // subscribe(model => {
    //     console.log(model);
    //     this.valueSubject.next(model);
    //     this.valueSubject.complete();
    //     });
    // return this.valueSubject;
  }

  protected abstract getValueAsSubject() : CleanableSubject<Model>;
    
}