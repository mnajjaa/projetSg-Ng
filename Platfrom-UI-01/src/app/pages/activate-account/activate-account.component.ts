import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services/authentication.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent {
  message = '';
  isOkay = true;
  submitted = false;
  otp = ['', '', '', '', '', ''];

  constructor(
    private router: Router,
    private authService: AuthenticationService
  ) {}

  private confirmAccount(token: string) {
    this.authService.confirm({ token }).subscribe({
      next: () => {
        this.message = 'Your account has been successfully activated.\nNow you can proceed to login';
        this.submitted = true;
        this.isOkay = true;
        this.router.navigate(['login']);
      },
      error: () => {
        this.message = 'Token has expired or is invalid';
        this.submitted = true;
        this.isOkay = false;
      }
    });
  }

  onCodeCompleted() {
    const token = this.otp.join('');
    this.confirmAccount(token);
  }

  onSubmit() {
    this.onCodeCompleted();
  }

  resendCode() {
    // Logic to resend the code
    console.log('Resend code logic goes here');
  }
}
