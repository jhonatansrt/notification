import { Component } from '@angular/core';
import {
  IonHeader,
  IonToolbar,
  IonTitle,
  IonContent,
} from '@ionic/angular/standalone';
import { HubeesNotification } from 'plugins/hubees-notification/src';

@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
  standalone: true,
  imports: [IonHeader, IonToolbar, IonTitle, IonContent],
})
export class HomePage {
  private interval: any;
  private progress: number = 100; // Começa com 100
  private remainingTime: number = 180; // 3 horas em minutos
  private detailsMinutes: number = 0; // Contador de minutos para a permanência
  private details: string = 'Permanência: 00h00'; // Tempo de permanência em minutos
  private arrivalTime: string = 'Chegada: 09h00';

  constructor() {}

  startService() {
    // Envia a notificação inicial com os dados configurados
    HubeesNotification.sendNotification({
      remainingTime: this.formatTime(this.remainingTime), // Formata o tempo inicial
      arrivalTime: this.arrivalTime,
      details: this.details,
      progress: this.progress,
    })
      .then((result) => {
        console.log('Notification sent:', result);
      })
      .catch((error) => {
        console.error('Error sending notification:', error);
      });

    // Atualiza a notificação a cada minuto
    this.interval = setInterval(async () => {
      const { notificationClosed } =
        await HubeesNotification.isNotificationClosed();

      if (notificationClosed) {
        this.stopService();
        return;
      }

      this.remainingTime--; // Diminui 1 minuto
      this.detailsMinutes++; // Aumenta o contador de minutos de permanência
      this.details = `Permanência: ${this.formatTime(this.detailsMinutes)}`; // Atualiza o tempo de permanência
      this.progress = Math.max(Math.round((this.remainingTime / 180) * 100), 0); // Diminui o progresso de 100 a 0 conforme o tempo restante

      this.updateNotification();

      if (this.remainingTime <= 0) {
        this.stopService(); // Para o intervalo quando o tempo restante chega a 0
      }
    }, 60000); // Atualiza a cada 60 segundos (1 minuto)
  }

  updateNotification() {
    const remainingTimeFormatted = this.formatTime(this.remainingTime); // Formata o tempo restante (exemplo: "2h05")

    HubeesNotification.updateNotification({
      remainingTime: remainingTimeFormatted,
      arrivalTime: this.arrivalTime,
      details: this.details,
      progress: this.progress,
    })
      .then((result) => {
        console.log('Notification updated:', result);
      })
      .catch((error) => {
        console.error('Error updating notification:', error);
      });
  }

  formatTime(minutes: number): string {
    const hours = Math.floor(minutes / 60);
    const mins = minutes % 60;
    return `${this.pad(hours)}h${this.pad(mins)}`;
  }

  pad(value: number): string {
    return value < 10 ? `0${value}` : value.toString();
  }

  stopService() {
    clearInterval(this.interval);
  }
}
