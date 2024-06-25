import { AbstractControl } from '@angular/forms'

export class PasswordValidate {

    static validatePassword(control: AbstractControl): any {
        let pattern1: RegExp = /^.*[A-Z]+.*/;
        let pattern2: RegExp = /^.*[a-z]+.*/;
        let pattern3: RegExp = /.*[\d]+.*/;
        let pattern4: RegExp = /.*[^a-zA-Z-0-9].*/;
        let pattern5: RegExp = /^.{8,16}$/;
        let value = control.value;
        let matches: boolean = pattern1.test(value) && pattern2.test(value) && pattern3.test(value)
            && pattern4.test(value) && pattern5.test(value);

        if (!matches) {
            return { "passwordPatternError": true }
        }
        return null;
    }
}