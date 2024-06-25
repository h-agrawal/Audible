import { Injectable} from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from '@angular/router';
import { LoginService } from './loginpage/login.service';

@Injectable({
    providedIn: 'root'
})
export class AccessGuardService implements CanActivate {
    constructor(private serv:LoginService, private router: Router ) {};
    canActivate(_route: ActivatedRouteSnapshot, _state: RouterStateSnapshot) {
        if(this.serv.canLogin()) {
            
            return true;
            
        } else {
          this.router.navigate(['/login'])
            return false;
        }
        
    }
}
