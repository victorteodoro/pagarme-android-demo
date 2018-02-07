package me.pagar.demoandroidpagarmempos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class EnterActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_enter);

    Button signinBtn = (Button) findViewById(R.id.btn_signin);
    signinBtn.setOnClickListener(view -> {
      Intent intent = new Intent(EnterActivity.this, LoginActivity.class);
      startActivity(intent);
    });
  }
}
