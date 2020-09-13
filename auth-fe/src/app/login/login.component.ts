import { Component, OnInit } from '@angular/core';
import { Login } from './login';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  data : Login = {
    emailId: '',
    password: ''
  };

  constructor(private http: HttpClient) { }

  ngOnInit(): void {
  }

  login(){

    const headers = { 'content-type': 'application/json'}

    this.http.post('http://localhost:8080/api/auth/login',JSON.stringify(this.data),{'headers':headers})
        .subscribe(
            err => console.log(err),
            () => console.log('done')
        );

  }

}
