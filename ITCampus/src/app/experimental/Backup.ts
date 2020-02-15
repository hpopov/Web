import { UserData } from '../shared/user/user.data';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';
import { GroupedObservable } from 'rxjs';

interface Dictionary<T> {
    [key: string]: T;
}

type ExtensibleMapper<Type, Condition, Output, Otherwise> = Type extends Condition ? Output : Otherwise;
type FilteringMapper<Type, Condition, Output> = ExtensibleMapper<Type, Condition, Output, never>;
type DifferenceMapper<Type, Condition, Output> = ExtensibleMapper<Type, Condition, never, Output>;
type Difference<Type, Condition> = DifferenceMapper<Type, Condition, Type>;
type Filter<Type, Condition> = FilteringMapper<Type, Condition, Type>;//Exclude, Extract?
type Mapper<Type, Output> = FilteringMapper<Type, Type, Output>;

type Primitive = string | number | boolean | symbol;
type PrimitiveKeyNames<T> = { [K in keyof T]: FilteringMapper<T[K], Primitive, K> }[keyof T];
type ArrayKeyNames<T> = { [K in keyof T]: FilteringMapper<T[K], Array<any>, K> }[keyof T];
// type FormGroupKeyNames<T> = { [K in keyof T]: DifferenceMapper<T[K], Primitive|Array<any>, K>}[keyof T];
type FormGroupKeyNames<T> = { [K in keyof T]:
    DifferenceMapper<T[K], PrimitiveKeyNames<T> | ArrayKeyNames<T>, K> }[keyof T];

type FormControls = FormGroup | FormControl | FormArray;

type KeyType<T, K extends keyof T> = ExtensibleMapper<T[K], Primitive, FormControl,
    ExtensibleMapper<T[K], Array<any>, FormArray, FormGroup>>;

// type KeyType<T extends FormData, K extends keyof T> = T[K] extends Primitive ? FormControl
// : T[K] extends Array<any> ? FormArray
// : FormGroup;

interface IMap<K extends keyof any, T> {
    get(key: K): T;
    put(key: K, value: T): T;
}
export class Map<K extends keyof any, T> implements IMap<K, T>{
    private _dictionary: Dictionary<T>;
    constructor() {
        this._dictionary = {};
    }

    public get(key: K): T {
        return this._dictionary[key.toString()];
    }

    public put(key: K, value: T): T {
        const oldValue: T = this._dictionary[key.toString()];
        this._dictionary[key.toString()] = value;
        return oldValue;
    }

    public get dictionary(): Dictionary<T> {
        const result: Dictionary<T> = {};
        for (const param in this._dictionary) {
            if (this._dictionary.hasOwnProperty(param)) {
                result[param] = this._dictionary[param];
            }
        }
        return result;
    }
}

export class FormGroupMap<T> {
    private form: Map<keyof T, AbstractControl>;
    private controls: Map<keyof T, FormControl>;
    private arrays: Map<keyof T, FormControlHolder<FormArray>>;
    private subgroups: Map<keyof T, FormGroupHolder<T>>;

    constructor() {
        this.form = new Map<keyof T, AbstractControl>();
        this.controls = new Map<keyof T, FormControl>();
        this.arrays = new Map<keyof T, FormControlHolder<FormArray>>();
        this.subgroups = new Map<keyof T, FormGroupHolder<T>>();
    }

    public get formControls(): Dictionary<AbstractControl> {
        return this.form.dictionary;
    }

}

// enum FormControlType {
//     GROUP, CONTROL, ARRAY
// }


class FormControlHolder<T extends FormControls> {
    // public control: T;
    // public readonly controlType: FormControlType;
    constructor(public control: T) {
        // this.control = control;
        // if (this.control instanceof FormGroup) {
        //     this.controlType = FormControlType.GROUP;
        // } else if (this.control instanceof FormControl) {
        //     this.controlType = FormControlType.CONTROL;
        // } else {
        //     this.controlType = FormControlType.ARRAY;
        // }
    }
}

type StringKeyOf<T> = Extract<keyof T, string>;
type FormMap<T> = Map<keyof T, AbstractControl>;
export class FormGroupHolder<T> extends FormControlHolder<FormGroup> {
    private form: Map<keyof T, AbstractControl>;
    private controls: Map<keyof T, FormControl>;
    private arrays: Map<keyof T, FormControlHolder<FormArray>>;
    private subgroups: Map<keyof T, FormGroupHolder<T>>;

    constructor(private defaultValue: T) {
        super(null);
        this.form = new Map<keyof T, AbstractControl>();
        this.control = new FormGroup(this.mapFormDataToFormMap(defaultValue).formControls);
    }

    // public get formGroup() : FormGroup {
    //     return this._formGroup;
    // }

    private mapFormDataToFormMap<T>(formData: T): FormGroupMap<T> {
        const formMap: FormGroupMap<T> = new FormGroupMap<T>();
        for (const property in formData) {
            if (formData.hasOwnProperty(property)) {
                const propValue: any = formData[property];
                const formControlHolder = this.mapValueToFormControl<T>(formData[property]);
                const formControl = formControlHolder.control;

                if (this.isPrimitive(propValue)) {
                    // this.form.put(property, new FormControl(formData[property]));
                }
                if (formControl instanceof FormControl) {

                }
            }
        }
        return formMap;
    }

    private mapValueToFormControl<T>(value: any): FormControlHolder<FormControls> {
        if (this.isPrimitive(value)) {
            return new FormControlHolder(new FormControl(value));
        }
        // else if (value instanceof Array) {
        //     const formArray = new FormArray([]);
        //     if (value.length > 0) {
        //         value.forEach(element => {
        //             formArray.push(this.mapValueToFormControl(element));
        //         });
        //     }
        //     return formArray;
        // }
        else {
            return new FormGroupHolder<T>(value);
        }
    }

    private isPrimitive(x: any): x is number | string | boolean | symbol {
        return typeof x === "number" || typeof x === "string"
            || typeof x === "boolean" || typeof x === "symbol";
    }
    // public methodd(param: keyof T): any {
    //     return this.defaultValue[param];
    // }

    // public conditionalMethod<K extends keyof T>(param: K): KeyType<T, K> {
    //     const value: AbstractControl = this.formMap.get(param);
    //     if (value instanceof FormControl) {
    //         return value as KeyType<T, K>;
    //     }
    // }

    public getFormControl(param: PrimitiveKeyNames<T>): FormControl {
        return this.form.get(param) as FormControl;
    }

    // public getFormGroup(param: FormGroupKeyNames<T>): FormGroup {

    // }

}

export class Main {

    constructor(private group: FormGroup) { }
    /**
     * name
     */
    public name() {
        let val: UserData = {
            authorities: [],
            id: 1,
            login: 'loginn',
            name: 'Peter',
            surname: 'Hastings'
        };
        let wrapper: FormGroupHolder<UserData> = new FormGroupHolder(val);
        // wrapper.methodd("name");
        // wrapper.conditionalMethod("surname");
        wrapper.getFormControl("login");
    }

    public indexedParamsType() {
        let p: PrimitiveKeyNames<UserData>;
    }

    /**
     * formGroupExp
     */
    public formGroupExp() {
        this.group = new FormGroup({
            'name': new FormControl(''),
            'surname': new FormControl('A', Validators.required),
            'car': new FormGroup({
                'price': new FormControl(0),
                'color': new FormControl()
            }),
            'vehicles': new FormArray([
                new FormControl()
            ])
        })
        this.group.get('mene');
    }
}