package wyq.tool.massager;

import java.util.Random;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Vibrator;
import android.util.Log;

public class MyService extends Service {

	private static MyService svc = null;

	public static boolean isRunning() {
		return (svc != null) ? svc.isRunning : false;
	}

	private boolean isRunning = false;
	private Random rand = new Random();
	private Thread worker;
	private static final String TAG = "MyService";

	public MyService() {
		svc = this;
	}

	@Override
	public IBinder onBind(Intent intent) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public void onCreate() {
		super.onCreate();
		worker = new Thread("MyService-worker") {
			@Override
			public void run() {

				Log.d(TAG, "worker running");
				try {
					while (isRunning) {
						Vibrator worker = (Vibrator) getSystemService(VIBRATOR_SERVICE);
						long vibMillsec = 200 + rand.nextInt(3000);
						worker.vibrate(vibMillsec);
						long sleepMilsec = 200 + rand.nextInt(2000);
						Thread.sleep(sleepMilsec);
					}
				} catch (InterruptedException e) {
					Log.d(TAG, "interrupted");
				}
			}
		};
		Log.d(TAG, "onCreated");
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		isRunning = true;
		worker.start();
		Log.d(TAG, "onStarted");
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		isRunning = false;
		worker.interrupt();
		worker = null;
		Log.d(TAG, "onDestoyed");
	}

}
