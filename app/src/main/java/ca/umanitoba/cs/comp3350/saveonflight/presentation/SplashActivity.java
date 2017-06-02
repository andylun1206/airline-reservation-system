package ca.umanitoba.cs.comp3350.saveonflight.presentation;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import ca.umanitoba.cs.comp3350.saveonflight.R;

/**
 * SplashActivity.java
 *
 * Activity to transition from the splash screen to home screen.
 *
 * @author Andy Lun
 */
public class SplashActivity extends AppCompatActivity {
	private final int SPLASH_DISPLAY_LENGTH = 1500;
	
	@Override
	protected void onCreate(Bundle saveInstanceState) {
		super.onCreate(saveInstanceState);
		
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				finish();
			}
		}, SPLASH_DISPLAY_LENGTH);
	}
}
