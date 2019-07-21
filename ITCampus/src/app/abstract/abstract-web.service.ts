import { Subject, Subscription, Observable } from 'rxjs';



export abstract class AbstractWebService<Model> {

    private loadedModel: Model;
    private modelSubject: Subject<Model>;
    private subscription: Subscription;
    
    constructor() {
        this.modelSubject = new Subject<Model>();
        this.subscription = null;
        this.loadedModel = null;
    }

    protected loadModel(): void {
        this.loadedModel = null;
        if (this.subscription != null) {
          this.subscription.unsubscribe();
        }
        this.subscription = this.getModelObservable().subscribe(response => {
          console.log(response);
          this.modelSubject.next(response);
          this.loadedModel = response;
        });
    }

    public subscribeOnModel(subscribeFunction: (Model) => void): Subscription {
        let subscription: Subscription = this.modelSubject.subscribe(subscribeFunction);
        if (this.loadedModel) {
          subscribeFunction(this.loadedModel);
        }
        return subscription;
    }

    protected abstract getModelObservable(): Observable<Model>;

}