import { Component, AfterViewInit } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements AfterViewInit {

  ngAfterViewInit() {
    this.loadScripts();
  }

  loadScripts() {
    // Recarregar os scripts que manipulam o DOM
    const scripts = [
      'assets/js/jquery.min.js',
      'assets/js/browser.min.js',
      'assets/js/breakpoints.min.js',
      'assets/js/util.js',
      'assets/js/main.js'
    ];

    for (let script of scripts) {
      let node = document.createElement('script');
      node.src = script;
      node.type = 'text/javascript';
      node.async = true;
      node.charset = 'utf-8';
      document.getElementsByTagName('body')[0].appendChild(node);
    }
  }
}
