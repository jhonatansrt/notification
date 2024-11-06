export interface HubeesNotificationPlugin {
  sendNotification(options: {
    remainingTime: string;
    details: string;
    arrivalTime: string;
    progress: number;
  }): Promise<{ success: boolean }>;

  updateNotification(options: {
    remainingTime: string;
    details: string;
    arrivalTime: string;
    progress: number;
  }): Promise<{ success: boolean }>;

  isNotificationClosed(): Promise<{ notificationClosed: boolean }>; // Novo método
}
