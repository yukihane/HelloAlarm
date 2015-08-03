package yukihane.helloalarm;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainActivity.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button startButton = (Button) findViewById(R.id.startBtn);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "アラームをセットします", Toast.LENGTH_SHORT).show();
                MyAlarm.set(getApplicationContext());
            }
        });

        Button stopButton = (Button) findViewById(R.id.stopBtn);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "アラームをキャンセルします", Toast.LENGTH_SHORT).show();
                MyAlarm.cancel(getApplicationContext());
            }
        });

        Button outputLogButton = (Button) findViewById(R.id.logBtn);
        outputLogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                exportLog();
            }
        });
    }

    private void exportLog() {
        final File filesDir = getFilesDir();
        final File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Toast.makeText(this, downloadDir.toString(), Toast.LENGTH_LONG).show();

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                File[] logfiles = filesDir.listFiles(new FilenameFilter() {
                    @Override
                    public boolean accept(File file, String s) {
                        LOGGER.debug("file: {} {}", file, s);
                        if (s.endsWith("log") || s.endsWith("log.txt")) {
                            return true;
                        }
                        return false;
                    }
                });

                for (File f : logfiles) {
                    try {
                        LOGGER.debug("copy file: {}", f);
                        Utils.copy(f, downloadDir);
                    } catch (IOException e) {
                        LOGGER.error("copy error", e);
                    }
                }
            }
        };

        new Thread(runnable).start();
    }
}
