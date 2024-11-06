import { WebPlugin } from '@capacitor/core';
import type { HubeesNotificationPlugin } from './definitions';

export class HubeesNotificationWeb extends WebPlugin implements HubeesNotificationPlugin {
  async sendNotification(options: {
    remainingTime: string;
    details: string;
    arrivalTime: string;
    progress: number;
  }): Promise<{ success: boolean }> {
    return { success: true };
  }

  async updateNotification(options: {
    remainingTime: string;
    details: string;
    arrivalTime: string;
    progress: number;
  }): Promise<{ success: boolean }> {
    return { success: true };
  }

  async isNotificationClosed(): Promise<{ notificationClosed: boolean }> {
    // Retorna `false` na versão web como padrão
    return { notificationClosed: false };
  }
}
