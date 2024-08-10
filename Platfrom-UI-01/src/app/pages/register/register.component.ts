import {Component} from '@angular/core';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/services/authentication.service';
import {RegistrationRequest} from '../../services/models/registration-request';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  roles: string[] = ['USER', 'RECRUITER']; // Add available roles here
  registerRequest: RegistrationRequest = {
    email: '',
    firstname: '',
    lastname: '',
    password: '',
    role: ''
  };
  errorMsg: Array<string> = [];
  showSpecificFields: boolean = false;

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  onRoleChange(event: Event) {
    const selectElement = event.target as HTMLSelectElement | null;
    const selectedRole = selectElement?.value || '';
    this.showSpecificFields = (selectedRole === 'USER' || selectedRole === 'RECRUITER');
    this.registerRequest.role = selectedRole;
  }




  login() {
    this.router.navigate(['login']);
  }

  register() {
    this.errorMsg = [];
    this.authService.register({
      body: this.registerRequest
    })
      .subscribe({
        next: () => {
          this.router.navigate(['activate-account']);
        },
        error: (err) => {
          this.errorMsg = err.error.validationErrors;
        }
      });
  }
}
