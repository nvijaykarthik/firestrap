import { Component, OnInit } from '@angular/core';
import { TopNavService } from './top-nav.service'

@Component({
  selector: 'app-top-nav',
  templateUrl: './top-nav.component.html',
  styleUrls: ['./top-nav.component.css']
})
export class TopNavComponent implements OnInit {

userName: string;

  constructor(private topNavService: TopNavService) { }

  ngOnInit() {
   console.debug("Inside TopNavComponent init");
     this.topNavService.getUserName().subscribe(
      resp => {
        this.userName = resp;
      },
      err => {
        alert("Failed to get the user name")
      }
    )
  }

}
