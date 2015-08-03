package yukihane.helloalarm;

import android.app.AlarmManager;
import android.app.Application;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import java.util.Calendar;

/**
 * http://developer.android.com/training/scheduling/alarms.html のサンプルでは
 * Receiverクラスに一緒くたに実装されているが, 分けたほうがわかりやすいと思われる.
 */
public class MyAlarm {

    /**
     * 30秒毎定期的に実行するようアラームをセットします.
     */
    public static void set(Context context) {

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = getPendingIntent(context);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 30);


        alarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(), 30 * 1000, alarmIntent);


        setBootReceiver(context, true);
    }

    /**
     * アラームをキャンセルします.
     */
    public static void cancel(Context context) {

        AlarmManager alarmMgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent alarmIntent = getPendingIntent(context);

        alarmMgr.cancel(alarmIntent);


        setBootReceiver(context, false);
    }

    private static PendingIntent getPendingIntent(Context context) {
        Intent intent = new Intent(context, MyReceiver.class);
        return PendingIntent.getBroadcast(context, 0, intent, 0);
    }

    public static void setBootReceiver(Context context, boolean enabled) {
        ComponentName receiver = new ComponentName(context, BootReceiver.class);
        PackageManager pm = context.getPackageManager();

        int state = (enabled)
                ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;

        pm.setComponentEnabledSetting(receiver, state, PackageManager.DONT_KILL_APP);
    }
}
