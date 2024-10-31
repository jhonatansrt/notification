
import { WebPlugin } from '@capacitor/core';
import type { HubeesNotificationPlugin } from './definitions';

export class HubeesNotificationWeb extends WebPlugin implements HubeesNotificationPlugin {
  async sendNotification(options: { timeStart: string, timeEnd: string, remainingTime: string, details: string, arrivalTime: string, progress: number}): Promise<{ success: boolean }>{
    return { success: true };
  }

  async updateNotification(options: { timeStart: string, timeEnd: string, remainingTime: string, details: string, arrivalTime: string, progress: number}): Promise<{ success: boolean }>{
    return { success: true };
  }
}

