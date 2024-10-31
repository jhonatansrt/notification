import { Component } from '@angular/core';
import { IonHeader, IonToolbar, IonTitle, IonContent } from '@ionic/angular/standalone';
import { HubeesNotification } from 'plugins/hubees-notification/src';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent],
})
export class HomePage {
  constructor() {}

  startService() {
    const timeStart = "09:00";
    const timeEnd = "12:00";
    const remainingTime = "2h05";
    const details = "Permanência: 00:30";
    const arrivalTime = "Chegada: 09:00";
    const progress = 30;

    HubeesNotification.sendNotification({
      timeStart,
      timeEnd,
      remainingTime,
      arrivalTime,
      details,
      progress
    }).then(result => {
      console.log('Notification result:', result);
    }).catch(error => {
      console.error('Error sending notification:', error);
    });
  }

  updateService() {
    const timeStart = "12:00";
    const timeEnd = "13:00";
    const remainingTime = "2h35";
    const details = "Permanência: 10:55";
    const arrivalTime = "Chegada: 10:00";
    const progress = 80;

    HubeesNotification.updateNotification({
      timeStart,
      timeEnd,
      remainingTime,
      arrivalTime,
      details,
      progress
    }).then(result => {
      console.log('Notification result:', result);
    }).catch(error => {
      console.error('Error sending notification:', error);
    });
  }
}
