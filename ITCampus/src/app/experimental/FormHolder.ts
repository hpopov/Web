import { UserData } from '../shared/user/user.data';
import { FormGroup, FormControl, Validators, FormArray, AbstractControl } from '@angular/forms';

interface Dictionary<T> {
    [key: string]: T;
}

type ExtensibleMapper<Type, Condition, Output, Otherwise> = Type extends Condition ? Output : Otherwise;
type FilteringMapper<Type, Condition, Output> = ExtensibleMapper<Type, Condition, Output, never>;
// type FilteringMapper<Type, Condition, Output> = Type extends Condition ? Output : never;
type Filter<Type, Condition> = FilteringMapper<Type, Condition, Type>;
type Mapper<Type, Output> = FilteringMapper<Type, Type, Output>;

type Primitive = string | number | boolean | symbol;
type PrimitiveKeyNames<T> = { [K in keyof T]: FilteringMapper<T[K], Primitive, K> }[keyof T];
type ArrayKeyNames<T> = { [K in keyof T]: FilteringMapper<T[K], Array<any>, K> }[keyof T];

type KeyType<T, K extends keyof T> = ExtensibleMapper<T[K], Primitive, FormControl,
    ExtensibleMapper<T[K], Array<any>, FormArray, FormGroup>>;

// type KeyType<T extends FormData, K extends keyof T> = T[K] extends Primitive ? FormControl
// : T[K] extends Array<any> ? FormArray
// : FormGroup;



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

export class KeyWrapper<T> {
    private formMap: Map<keyof T, AbstractControl>;
    private _formGroup: FormGroup;
    constructor(private defaultValue: T) {
        this.formMap = this.mapFormDataToFormMap(defaultValue);
        this._formGroup = new FormGroup(this.formMap.dictionary);
    }

    // public get formGroup() : FormGroup {
    //     return this._formGroup;
    // }

    private mapFormDataToFormMap(formData: T): Map<keyof T, AbstractControl> {
        const formMap: Map<keyof T, AbstractControl> = new Map<keyof T, AbstractControl>();
        for (const property in formData) {
            if (formData.hasOwnProperty(property)) {
                const propValue: any = formData[property];
                if (this.isPrimitive(propValue)) {
                    formMap.put(property, new FormControl(propValue));
                } else if (propValue instanceof Array) {
                    const formArray = new FormArray([]);
                    if (propValue.length > 0) {
                        if (this.isPrimitive(propValue[0]))
                        propValue.forEach(element => {
                            formArray.push(new FormControl(element));// only in case of element is primitive!
                        });
                    }
                    formMap.put(property, formArray);
                } else {
                    const formGroup = new KeyWrapper<typeof propValue>(propValue)._formGroup;
                    formMap.put(propValue, formGroup);
                }
            }
        }
        return formMap;
    }

    private isPrimitive(x: any): x is number | string | boolean | symbol {
        return typeof x === "number" || typeof x === "string"
            || typeof x === "boolean" || typeof x === "symbol";
    }
    public methodd(param: keyof T): any {
        return this.defaultValue[param];
    }

    public conditionalMethod<K extends keyof T>(param: K): KeyType<T, K> {
        const value: AbstractControl = this.formMap.get(param);
        if (value instanceof FormControl) {
            return value as KeyType<T, K>;
        }
    }

    public primitiveKeyMethod(param: PrimitiveKeyNames<T>): FormControl {
        return this.formMap.get(param) as FormControl;
    }

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
        let wrapper: KeyWrapper<UserData> = new KeyWrapper(val);
        wrapper.methodd("name");
        wrapper.conditionalMethod("surname");
        wrapper.primitiveKeyMethod("login");
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