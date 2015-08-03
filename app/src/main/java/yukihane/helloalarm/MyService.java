package yukihane.helloalarm;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定期的に実行する処理を実装します.
 */
public class MyService extends IntentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyService.class);

    public MyService() {
        super("My Service (yukihane.helloalarm)");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {

            LOGGER.info("サービスで処理を実行しました");

        } finally {
            MyReceiver.completeWakefulIntent(intent);
        }
    }
}
