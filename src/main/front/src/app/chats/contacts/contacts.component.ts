import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-contacts',
  templateUrl: './contacts.component.html',
  styleUrls: ['./contacts.component.scss']
})
export class ContactsComponent {

  @Input() chats;
  @Output() onActiveChat = new EventEmitter();

  avatar: string = '../assets/user-image.jpg';

  constructor() {
  }

  ngAfterViewInit() {
  }

  setActiveChat(chat) {
    this.onActiveChat.emit(chat);
  }

}
