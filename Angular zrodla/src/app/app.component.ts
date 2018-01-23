import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Http } from '@angular/http';
class ToSend{
	title: any;
	actors: any;
	steps: any;
	constructor(){
		this.title = "";
		this.actors = "";
		this.steps = [];
	}
}
class json{
	head: any;
	content: any;
	constructor(h: any, c: any){
		this.head = h;
		this.content = c;
	}
}
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
	file: any;
  title = 'app';
  data="ff";
  firstWord: any;
  content = "";
  handler: any;
  jsonArray: any;
  actors: any;
  path: any;
  constructor(private http: Http){
	this.file = new ToSend();
	this.jsonArray = [];  
  }
	postData(){
		this.handler = this.http.post(this.path,this.file).subscribe();
	}
	calculateJson(){
		this.file.actors = this.actors.split(" "); 
		this.content = "";
		var words = this.data.split("\n");
		for(var i = 0; i < words.length; i++){
			var a = words[i].split(" ");
			if(a[0] == ""){
				this.jsonArray[i] = new json(a[1],"");
				for(var ii = 1; ii< a.length; ii++){
					this.jsonArray[i].content = this.jsonArray[i].content + " " + a[ii];
				}
				this.jsonArray[i].head = this.calculateHead(this.jsonArray[i].head);
			}else{
				this.jsonArray[i] = new json(a[0],"");
				for(var ii = 0; ii< a.length; ii++){
					this.jsonArray[i].content = this.jsonArray[i].content + " " + a[ii];
				}
				this.jsonArray[i].head = this.calculateHead(this.jsonArray[i].head);
			}
		}
		this.file.steps = this.jsonArray;
		this.postData();
	}
	calculateActor(name: any){
		var d = 0;
		for(var i = 0; i < this.file.actors.length; i++){
				if(name == this.file.actors[i]){
					d = 1;
					break
				}
		}
		if(d== 0){
			return "";
		}else{
			return "ACTOR";
		}
	}
	calculateHead(name: any){
		if(name == "If"){
			return "If";
		}if(name == "ELSE"){
			return "ELSE";
		}if(name == "FOR"){
			return "FOR";
		}if(name == "END_TAG"){
			return "END";
		}if(this.calculateActor(name) == "ACTOR"){
			return "ACTOR";
		}else{
			return "SIMPLE";
		}
	}
}
