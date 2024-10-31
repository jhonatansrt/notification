import { WebPlugin } from '@capacitor/core';
import type { HubeesNotificationPlugin } from './definitions';
export declare class HubeesNotificationWeb extends WebPlugin implements HubeesNotificationPlugin {
    sendNotification(options: {
        timeStart: string;
        timeEnd: string;
        remainingTime: string;
        details: string;
        arrivalTime: string;
        progress: number;
    }): Promise<{
        success: boolean;
    }>;
    updateNotification(options: {
        timeStart: string;
        timeEnd: string;
        remainingTime: string;
        details: string;
        arrivalTime: string;
        progress: number;
    }): Promise<{
        success: boolean;
    }>;
}
