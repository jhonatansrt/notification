export interface HubeesNotificationPlugin {
  sendNotification(options: { timeStart: string, timeEnd: string, remainingTime: string, details: string, arrivalTime: string, progress: number}): Promise<{ success: boolean }>;
  updateNotification(options: { timeStart: string, timeEnd: string, remainingTime: string, details: string, arrivalTime: string, progress: number}): Promise<{ success: boolean }>;
}
