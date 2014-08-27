package com.example.kandashboard;

import com.kankan.style.dashboard.MDashboard;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	private int progress = 0;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		final MDashboard dashboard = (MDashboard) findViewById(R.id.kan_dashboard);
		dashboard.setMax(100);
		dashboard.setProgress(progress);
		//写一个匿名内部类，改变指针
		((Button) findViewById(R.id.add_progress))
				.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						progress += 10;
						Toast.makeText(MainActivity.this, "进度加10%，progress +10", Toast.LENGTH_LONG).show();
						
						if (dashboard.getProgress() >= dashboard.getMax()) {
							progress = 0;
						}
						//写入进入，刷新进度
						dashboard.setProgress(progress);
					}
				});
		
	}

 
}
