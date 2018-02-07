package me.pagar.demoandroidpagarmempos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import java.text.NumberFormat;
import java.text.ParseException;

public class ChooseAmountActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

  private String sessionId;
  private String encryptionKey;
  private String softDescriptor;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_choose_amount);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle =
        new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    Bundle extras = getIntent().getExtras();
    if(extras != null) {
      sessionId = extras.getString("sessionId");
      encryptionKey = extras.getString("encryptionKey");
      softDescriptor = extras.getString("softDescriptor");
    }
  }

  @Override public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.choose_amount, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @SuppressWarnings("StatementWithEmptyBody") @Override public boolean onNavigationItemSelected(MenuItem item) {
    // Handle navigation view item clicks here.
    int id = item.getItemId();

    if (id == R.id.nav_camera) {
      // Handle the camera action
    } else if (id == R.id.nav_gallery) {

    } else if (id == R.id.nav_slideshow) {

    } else if (id == R.id.nav_manage) {

    } else if (id == R.id.nav_share) {

    } else if (id == R.id.nav_send) {

    }

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    drawer.closeDrawer(GravityCompat.START);
    return true;
  }

  public void addNumber(View view) {
    // Get the button digit as an int
    Button digitBtn = (Button) view;
    int digit = Integer.parseInt(digitBtn.getText().toString());
    Log.i("Button Pressed", digitBtn.getText().toString());

    // Get the current amount as an int and then print it as currency
    TextView amountDisplay = (TextView) findViewById(R.id.amount_display);
    int amount = Integer.parseInt(amountDisplay.getText().toString().substring(3).replace(",", ""));
    amount = amount * 10 + digit;
    Log.i("Resultant integer", String.valueOf(amount));
    if(digit == 0)
      amountDisplay.setText(String.format("R$ %s", String.valueOf(amount / 100.0).replace(".", ",").concat("0")));
    else
      amountDisplay.setText(String.format("R$ %s", String.valueOf(amount / 100.0).replace(".", ",")));
  }

  public void subtractNumber(View view) {
    // Get the current amount as an int and then print it as currency
    TextView amountDisplay = (TextView) findViewById(R.id.amount_display);
    int amount = Integer.parseInt(amountDisplay.getText().toString().substring(3).replace(",", ""));

    if((amount % 10) == 0 && amount >= 100) {
      amount /= 10;
      Log.i("Resultant integer", String.valueOf(amount));
      amountDisplay.setText(
          String.format("R$ %s", String.valueOf(amount / 100.0).replace(".", ",").concat("0")));
    } else if(amount > 10){
      amount /= 10;
      Log.i("Resultant integer", String.valueOf(amount));
      amountDisplay.setText(
          String.format("R$ %s", String.valueOf(amount / 100.0).replace(".", ",")));
    } else amountDisplay.setText("R$ 0,00");
  }

  public void goToPaymentActivity(View view) {
    // Get the current amount as an int
    TextView amountDisplay = (TextView) findViewById(R.id.amount_display);
    int amount = Integer.parseInt(amountDisplay.getText().toString().substring(3).replace(",", ""));

    Intent intent = new Intent(ChooseAmountActivity.this, TransactionActivity.class);
    intent.putExtra("sessionId", sessionId);
    intent.putExtra("encryptionKey", encryptionKey);
    intent.putExtra("softDescriptor", softDescriptor);
    intent.putExtra("amount", amount);
    startActivity(intent);
  }
}
