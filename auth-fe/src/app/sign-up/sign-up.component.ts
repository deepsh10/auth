import { Component, OnInit } from '@angular/core';
import { SignInData } from './SignInData';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { SignInRequest } from './SignInRequest';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {

  data : SignInData = {
    emailId:'',
    urn:'',
    password:'',
    cpassword:''
  };

  signIn : SignInRequest = {
    emailId:'',
    urn:'',
    password:''
  };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  validateEmail(){
    console.log("Email:"+this.data.emailId);

    this.http.post('http://localhost:8080/api/auth/generateToken',{},{params: {email: this.data.emailId}})
                .subscribe(
                    err => console.log(err),
                    () => alert('Please check your inbox of: ' + this.data.emailId)
                );
  }

  signup(){
    this.signIn.emailId = this.data.emailId;
    this.signIn.urn = this.data.urn;
    this.signIn.password = this.data.password;

    const headers = { 'content-type': 'application/json'}

    this.http.post('http://localhost:8080/api/auth/signUp',JSON.stringify(this.signIn),{'headers':headers})
            .subscribe(
                err => console.log(err),
                () => alert('Sign In successful.')
            );

  }

}
