package wyq.tool.massager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ToggleButton;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private ToggleButton button;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		button = (ToggleButton) findViewById(R.id.toggleButton1);
		button.setChecked(MyService.isRunning());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		// return true;
		return false;
	}

	public void toggleButtonClicked(View view) {
		Log.d(TAG, "button clicked!");
		if (button.isChecked()) {
			Log.d(TAG, "button is checked");
			startService(new Intent(this, MyService.class));
		} else {
			Log.d(TAG, "button not checked");
			stopService(new Intent(this, MyService.class));
		}
	}
}
