# hubees-notification

Notification

## Install

```bash
npm install hubees-notification
npx cap sync
```

## API

<docgen-index>

* [`sendNotification(...)`](#sendnotification)
* [`updateNotification(...)`](#updatenotification)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### sendNotification(...)

```typescript
sendNotification(options: { timeStart: string; timeEnd: string; remainingTime: string; details: string; arrivalTime: string; progress: number; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                                                                                                                |
| ------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ timeStart: string; timeEnd: string; remainingTime: string; details: string; arrivalTime: string; progress: number; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------


### updateNotification(...)

```typescript
updateNotification(options: { timeStart: string; timeEnd: string; remainingTime: string; details: string; arrivalTime: string; progress: number; }) => Promise<{ success: boolean; }>
```

| Param         | Type                                                                                                                                |
| ------------- | ----------------------------------------------------------------------------------------------------------------------------------- |
| **`options`** | <code>{ timeStart: string; timeEnd: string; remainingTime: string; details: string; arrivalTime: string; progress: number; }</code> |

**Returns:** <code>Promise&lt;{ success: boolean; }&gt;</code>

--------------------

</docgen-api>
