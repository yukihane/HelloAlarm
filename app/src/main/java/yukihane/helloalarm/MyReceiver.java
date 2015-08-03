package yukihane.helloalarm;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

/**
 * アラームのintentを受け取りサービスを実行します.
 */
public class MyReceiver extends WakefulBroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent service = new Intent(context, MyService.class);
        startWakefulService(context, service);
    }
}
