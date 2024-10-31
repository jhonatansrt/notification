import { Component } from '@angular/core';
import { PushNotifications } from '@capacitor/push-notifications';
import { IonApp, IonRouterOutlet } from '@ionic/angular/standalone';

@Component({
  selector: 'app-root',
  templateUrl: 'app.component.html',
  standalone: true,
  imports: [IonApp, IonRouterOutlet],
})
export class AppComponent {
  constructor() {}

  ngOnInit() {
    this.initializePushNotifications();
  }

  private async initializePushNotifications() {
    const permissionStatus = await PushNotifications.checkPermissions();

    if (permissionStatus.receive === 'granted') {
    } else {
      const result = await PushNotifications.requestPermissions();
      if (result.receive === 'granted') {
        console.log('Push notification permission granted');
      } else {
        console.log('Push notification permission not granted');
      }
    }
  }
}
