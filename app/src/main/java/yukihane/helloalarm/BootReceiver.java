package yukihane.helloalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * broadcast intentを受け, アラームをセットし直します.
 */
public class BootReceiver extends BroadcastReceiver {

    Logger LOGGER = LoggerFactory.getLogger(BootReceiver.class);

    @Override
    public void onReceive(Context context, Intent intent) {

        LOGGER.info("recieved action: {}", intent.getAction());

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
            MyAlarm.set(context.getApplicationContext());
        }

    }
}
