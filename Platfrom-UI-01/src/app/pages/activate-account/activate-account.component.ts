import { Component, ViewChild, ElementRef } from '@angular/core';
import { Router } from '@angular/router';
import { AuthenticationService } from '../../services/services/authentication.service';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrls: ['./activate-account.component.css']
})
export class ActivateAccountComponent {

  @ViewChild('digit1') digit1!: ElementRef;
  @ViewChild('digit2') digit2!: ElementRef;
  @ViewChild('digit3') digit3!: ElementRef;
  @ViewChild('digit4') digit4!: ElementRef;
  @ViewChild('digit5') digit5!: ElementRef;
  @ViewChild('digit6') digit6!: ElementRef;

  message = '';
  isOkay = true;
  submitted = false;
  otp = '';

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
        this.message = 'Token has been expired or invalid';
        this.submitted = true;
        this.isOkay = false;
        this.resetInputs();
      }
    });
  }

  onSubmit() {
    const token = this.otp;
    this.confirmAccount(token);
  }

  onInputChange(event: any, nextElementIndex: number) {
    const input = event.target;
    const value = input.value;
    const nextElement = this[`digit${nextElementIndex + 1}` as keyof ActivateAccountComponent] as ElementRef;

    if (value.length === 1) {
      this.otp = this.getOtpValue();
      if (nextElement) {
        nextElement.nativeElement.focus();
      }
    }
  }

  getOtpValue(): string {
    return `${this.digit1.nativeElement.value}${this.digit2.nativeElement.value}${this.digit3.nativeElement.value}${this.digit4.nativeElement.value}${this.digit5.nativeElement.value}${this.digit6.nativeElement.value}`;
  }

  resetInputs() {
    this.digit1.nativeElement.value = '';
    this.digit2.nativeElement.value = '';
    this.digit3.nativeElement.value = '';
    this.digit4.nativeElement.value = '';
    this.digit5.nativeElement.value = '';
    this.digit6.nativeElement.value = '';
    this.otp = '';
    this.digit1.nativeElement.focus();
  }

  resendCode() {
    // Resend code logic here
  }
}
