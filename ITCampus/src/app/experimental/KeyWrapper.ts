import { UserData } from '../shared/user/user.data';
import { FormGroup, FormControl, FormArray, AbstractControl } from '@angular/forms';

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
type FormGroupKeyNames<T> = { [K in keyof T]: DifferenceMapper<T[K], Primitive|Array<any>, K>}[keyof T];
// type FormGroupKeyNames<T> = { [K in keyof T]:
//     DifferenceMapper<T[K], PrimitiveKeyNames<T> | ArrayKeyNames<T>, K> }[keyof T];
type FormGroupKeyType<T, K extends FormGroupKeyNames<T>> = T[K];
type ArrayValueType<T, K extends ArrayKeyNames<T>> = T[K][];

type FormControls = FormGroup | FormControl | FormArray;

type KeyType<T, K extends keyof T> = ExtensibleMapper<T[K], Primitive, FormControl,
    ExtensibleMapper<T[K], Array<any>, FormArray, FormGroup>>;


export class Map<K extends keyof any, T> {
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
    // private form: Map<keyof T, AbstractControl>;
    // private controls: Map<keyof T, FormControl>;
    // private arrays: Map<keyof T, FormControlHolder<FormArray>>;
    // private subgroups: Map<keyof T, FormGroupHolder<T>>;

    constructor(private form: Map<keyof T, AbstractControl>,
        public readonly controls: Map<keyof T, FormControl>,
        public readonly arrays: Map<keyof T, FormControlHolder<FormArray>>,
        public readonly subgroups: Map<keyof T, any>) {
        // this.form = new Map<keyof T, AbstractControl>();
        // this.controls = new Map<keyof T, FormControl>();
        // this.arrays = new Map<keyof T, FormControlHolder<FormArray>>();
        // this.subgroups = new Map<keyof T, FormGroupHolder<T>>();
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

type FormArrayElement<T> = T extends Primitive ? FormControl : FormGroupHolder<T>;
//Decide, whether it should be possible to contain an array inside of array;
//Enhance types for maps in formMap
export class FormArrayHolder<T> extends FormControlHolder<FormArray> {

}

export class FormGroupHolder<T> extends FormControlHolder<FormGroup> {
    private formGroupMap: FormGroupMap<T>;

    constructor(private defaultValue: T) {
        super(null);
        this.formGroupMap = this.mapFormDataToFormGroupMap(defaultValue);
        this.control = new FormGroup(this.formGroupMap.formControls);
    }

    private mapFormDataToFormGroupMap<T>(formData: T): FormGroupMap<T> {
        const form: Map<keyof T, AbstractControl> = new Map<keyof T, AbstractControl>();
        const controls: Map<keyof T, FormControl> = new Map<keyof T, FormControl>();
        const arrays: Map<keyof T, FormControlHolder<FormArray>> =
            new Map<keyof T, FormControlHolder<FormArray>>();
        const subgroups: Map<keyof T, FormGroupHolder<any>> = new Map<keyof T, FormGroupHolder<any>>();
        for (const property in formData) {
            if (formData.hasOwnProperty(property)) {
                const formControlHolder = this.mapValueToFormControlHolder(formData[property]);
                const formControl = formControlHolder.control;
                form.put(property, formControl);
                if (formControl instanceof FormControl) {
                    controls.put(property, formControl);
                } else if (formControl instanceof FormArray) {
                    arrays.put(property, formControlHolder as FormControlHolder<FormArray>);
                } else {
                    subgroups.put(property, formControlHolder as FormGroupHolder<T>);
                }
            }
        }
        return new FormGroupMap(form, controls, arrays, subgroups);
    }

    private mapValueToFormControlHolder(value: any): FormControlHolder<FormControls> {
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

    public getFormControl(param: PrimitiveKeyNames<T>): FormControl {
        return this.formGroupMap.controls.get(param);
    }

    public getFormGroup<K extends FormGroupKeyNames<T>>(param: K): FormGroupHolder<FormGroupKeyType<T,K>>  {
        return this.formGroupMap.subgroups.get(param) as FormGroupHolder<FormGroupKeyType<T,K>>;
    }

    public getArrayGroup<K extends ArrayKeyNames<T>>(param: K): FormControlHolder<ArrayValueType<T,K>> {
        return this.formGroupMap.arrays.get(param);
    }

}

export class Main {

    constructor(private group: FormGroup) { }
    /**
     * name
     */
    public name() {
        const val = {
            authorities: [],
            id: 1,
            login: 'loginn',
            name: 'Peter',
            surname: 'Hastings',
            innerUser: {
                value1: 'haha',
                value2: 'superImportant'
            }
        };
        type Val = typeof val;
        let wrapper: FormGroupHolder<Val> = new FormGroupHolder(val);
        wrapper.getFormControl("login");
        let subgroup: FormGroupKeyNames<Val> = "innerUser";
        wrapper.getFormGroup("innerUser");
        wrapper.getArrayGroup("authorities");
    }

    public indexedParamsType() {
        let p: PrimitiveKeyNames<UserData>;
    }
}