package me.pagar.demoandroidpagarmempos;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.pagar.demoandroidpagarmempos.adapters.TransactionListAdapter;
import me.pagar.demoandroidpagarmempos.models.MainRecipientBalance;
import me.pagar.demoandroidpagarmempos.models.Transaction;
import org.json.JSONException;
import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

  private ProgressBar mProgressBar;

  private final String COUNT = "15";
  private String sessionId;
  private String encryptionKey;
  private String softDescriptor;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    Bundle extras = getIntent().getExtras();
    if(extras != null) {
      sessionId = extras.getString("sessionId");
      encryptionKey = extras.getString("encryptionKey");
      softDescriptor = extras.getString("softDescriptor");
    }

    FloatingActionButton btnNewPayment = (FloatingActionButton) findViewById(R.id.btn_new_payment);
    btnNewPayment.setOnClickListener(v -> {
      Intent intent = new Intent(MainActivity.this, ChooseAmountActivity.class);
      intent.putExtra("sessionId", sessionId);
      intent.putExtra("encryptionKey", encryptionKey);
      intent.putExtra("softDescriptor", softDescriptor);
      startActivity(intent);
    });


        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
        this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);

    // Hide the progress spinner
    mProgressBar = (ProgressBar) findViewById(R.id.progressBar);
    mProgressBar.setVisibility(View.VISIBLE);

    // Update the balance amount and the transactions list
    TextView balanceAmount = (TextView) findViewById(R.id.balance_amount);
    RecyclerView transactionsList = (RecyclerView) findViewById(R.id.transactions_view);
    updateUI(balanceAmount, transactionsList);
  }

  private void updateUI(final TextView balanceText, final RecyclerView transactionsList) {
    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl(PagarmeAPIService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();

    PagarmeAPIService apiService = retrofit.create(PagarmeAPIService.class);

    Call<MainRecipientBalance> getBalance = apiService.getMainBalance(sessionId, "0");
    getBalance.enqueue(new Callback<MainRecipientBalance>() {
      @Override public void onResponse(Call<MainRecipientBalance> call,
          Response<MainRecipientBalance> response) {
        Log.i("GET", String.valueOf(response.code()));
        if(response.isSuccessful()) {
          // Update balance accordingly
          long balance = response.body().getAvailable().getAmount();
          NumberFormat format = NumberFormat.getCurrencyInstance();
          balanceText.setText(format.format(balance/100.0));
        } else if(response.code() == 400) {
          // TODO: deal with bad requests
          Log.i("POST", "Erro 400!");
        } else if(response.code() == 401) {
          mProgressBar.setVisibility(View.GONE);
          Toast.makeText(MainActivity.this, getString(R.string.error_invalid_session), Toast.LENGTH_LONG).show();
        }
      }

      @Override public void onFailure(Call<MainRecipientBalance> call, Throwable t) {
        Toast.makeText(MainActivity.this, getString(R.string.error_no_connexion), Toast.LENGTH_LONG).show();
      }
    });

    /*// Set the options map of query params
    Map<String, String> options = new HashMap<>();
    options.put("session_id", sessionId);
    options.put("count", COUNT);*/

    // Get the transactions list based on these options
    Call<List<Transaction>> getTransactions = apiService.getTransactions(sessionId, "0");
    getTransactions.enqueue(new Callback<List<Transaction>>() {
      @Override
      public void onResponse(Call<List<Transaction>> call, Response<List<Transaction>> response) {
        Log.i("GET", String.valueOf(response.code()));
        if(response.isSuccessful()) {
          updateRecyclerView(response.body());
          mProgressBar.setVisibility(View.GONE);
        } else if(response.code() == 400) {
          // TODO: deal with bad requests
          Log.i("POST", "Erro 400!");
        } else if(response.code() == 401) {
          mProgressBar.setVisibility(View.GONE);
          Toast.makeText(MainActivity.this, getString(R.string.error_invalid_session), Toast.LENGTH_LONG).show();
        }
      }

      @Override public void onFailure(Call<List<Transaction>> call, Throwable t) {
        Toast.makeText(MainActivity.this, getString(R.string.error_no_connexion), Toast.LENGTH_LONG).show();
      }
    });
  }

  private void updateRecyclerView(List<Transaction> transactions) {
    TransactionListAdapter transactionAdapter = new TransactionListAdapter(getApplicationContext(), transactions);
    RecyclerView transactionsView = (RecyclerView) findViewById(R.id.transactions_view);
    transactionsView.setAdapter(transactionAdapter);
    RecyclerView.LayoutManager layoutManager =
        new LinearLayoutManager(MainActivity.this);
    transactionsView.setLayoutManager(layoutManager);
    transactionsView.setHasFixedSize(true);

    // Configurando um dividr entre linhas, para uma melhor visualização
    transactionsView.addItemDecoration(
        new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL));
  }

  @Override
  public void onBackPressed() {
    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    } else {
      super.onBackPressed();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
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

  @SuppressWarnings("StatementWithEmptyBody")
  @Override
  public boolean onNavigationItemSelected(MenuItem item) {
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
}
