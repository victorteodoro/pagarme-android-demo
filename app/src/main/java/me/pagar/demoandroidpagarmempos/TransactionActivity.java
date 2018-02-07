package me.pagar.demoandroidpagarmempos;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Set;
import me.pagar.mposandroid.EmvApplication;
import me.pagar.mposandroid.Mpos;
import me.pagar.mposandroid.MposListener;
import me.pagar.mposandroid.MposPaymentResult;
import me.pagar.mposandroid.PaymentMethod;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class TransactionActivity extends AppCompatActivity {
  TextView textFinalValue;
  TextView valueTextView;
  TextView numberInstallmentsTextView;
  //EditText valueEditText;
  RadioGroup radioGroup;
  RadioButton debitRadioButton;
  Button sendButton;
  Spinner instalmentsSpinner;
  private String urlString = "https://api.pagar.me/1/transactions?session_id=";
  private ProgressBar spinner;
  public JSONObject jsonBody;

  private int amount = 1000;
  private String sessionId = "f26f0e083dbc02c17537ca97360f45f5093868a24275c60b5e92f3113442";
  private String encryptionKey = "ek_test_jkVSptPeZ4zJlQm1RmDnjsFiAF6Go2";
  private final String macAddress = "04:A3:16:9A:56:47";
  private String softDescriptor = "Alesinha";

  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_transaction);

    Bundle extras = getIntent().getExtras();
    if(extras != null) {
      sessionId = extras.getString("sessionId");
      encryptionKey = extras.getString("encryptionKey");
      softDescriptor = extras.getString("softDescriptor");
      amount = extras.getInt("amount");
    }

    textFinalValue = (TextView) findViewById(R.id.textFinalValue);
    // Mostra o valor formatado pra reais
    NumberFormat realFormat = NumberFormat.getCurrencyInstance();
    textFinalValue.setText(realFormat.format(amount/100.0));

    valueTextView = (TextView) findViewById(R.id.textViewValue);
    spinner = (ProgressBar) findViewById(R.id.progress_bar);
    hideSpinner();
    numberInstallmentsTextView = (TextView) findViewById(R.id.textViewInstallments);
    //valueEditText = (EditText) findViewById(R.id.editTextValue);
    radioGroup = (RadioGroup) findViewById(R.id.radioGroupDebitCredit);
    sendButton = (Button) findViewById(R.id.buttonSendTransaction);
    instalmentsSpinner = (Spinner) findViewById(R.id.spinnerInstallments);
    debitRadioButton = (RadioButton) findViewById(R.id.radioDebit);

    numberInstallmentsTextView.setVisibility(View.INVISIBLE);
    instalmentsSpinner.setVisibility(View.INVISIBLE);

    spinnerAction();
    radioGroupClick();
    sendTransaction();
  }

  private void radioGroupClick() {
    radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.radioDebit) {
          numberInstallmentsTextView.setVisibility(View.INVISIBLE);
          instalmentsSpinner.setVisibility(View.INVISIBLE);
        } else {
          numberInstallmentsTextView.setVisibility(View.VISIBLE);
          instalmentsSpinner.setVisibility(View.VISIBLE);
        }
      }
    });
  }

  public void sendTransaction() {
    sendButton.setOnClickListener(v -> {
      try {
        goMpos(macAddress);
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  public void goMpos(String deviceName) throws IOException {

    //BluetoothDevice deviceAsked = new BluetoothDevice(deviceName);
    final Context cont = getApplicationContext();
    BluetoothDevice result = null;

    BluetoothAdapter adapter = BluetoothAdapter.getDefaultAdapter();

    Set<BluetoothDevice> devices = adapter.getBondedDevices();
    if (devices != null) {
      for (BluetoothDevice device : devices) {
        if (deviceName.equals(device.getAddress())) {
          result = device;
          break;
        }
      }
    }
    BluetoothDevice device = result;

    try {
      final Mpos mpos = new Mpos(device, encryptionKey, cont);


      mpos.addListener(new MposListener() {
        public void bluetoothConnected() {
          Log.d("Pagar.me", "Bluetooth connected.");
          mpos.initialize();
        }

        public void bluetoothDisconnected() {
          Log.d("Pagar.me", "Bluetooth disconnected.");
        }

        public void bluetoothErrored(int error) {
          Log.d("Pagar.me", "Received bluetooth error");
        }

        public void receiveInitialization() {
          Log.d("Pagar.me", "receive initialization!");

          /*// Pagar a trx
          runOnUiThread(() -> showSpinner());

          ArrayList<EmvApplication> l = new ArrayList<EmvApplication>();
          EmvApplication masterCredit = new EmvApplication(PaymentMethod.CreditCard, "mastercard");
          l.add(masterCredit);
          EmvApplication mesterDebit = new EmvApplication(PaymentMethod.DebitCard, "mastercard");
          l.add(mesterDebit);
          EmvApplication visaCredit = new EmvApplication(PaymentMethod.CreditCard, "visa");
          l.add(visaCredit);
          EmvApplication visaDebit = new EmvApplication(PaymentMethod.DebitCard, "visa");
          l.add(visaDebit);
          mpos.payAmount(amount, l, PaymentMethod.CreditCard);*/

          try {
            runOnUiThread(() -> showSpinner());
            mpos.downloadEMVTablesToDevice(false);
          } catch (Exception e) {
            Log.d("Pagar.me", "Got error in initialization and table update " + e.getMessage());
          }
        }

        public void receiveNotification(String notification) {
          Log.d("Pagar.me", "Got Notification " + notification);
        }

        @Override
        public void receiveOperationCompleted() {
        }

        public void receiveTableUpdated(boolean loaded) {
          Log.d("Pagar.me", "received table updated loaded = " + loaded);

          ArrayList<EmvApplication> l = new ArrayList<EmvApplication>();
          EmvApplication masterCredit = new EmvApplication(PaymentMethod.CreditCard, "mastercard");
          l.add(masterCredit);
          EmvApplication mesterDebit = new EmvApplication(PaymentMethod.DebitCard, "mastercard");
          l.add(mesterDebit);
          EmvApplication visaCredit = new EmvApplication(PaymentMethod.CreditCard, "visa");
          l.add(visaCredit);
          EmvApplication visaDebit = new EmvApplication(PaymentMethod.DebitCard, "visa");
          l.add(visaDebit);

          mpos.payAmount(amount, l, PaymentMethod.CreditCard);
        }

        public void receiveFinishTransaction() {
          runOnUiThread(new Runnable() {
            @Override
            public void run() {
              hideSpinner();
              showSuccess();
            }
          });
          Log.d("Pagar.me", "Finished transaction");
          mpos.close("TRANS. APROVADA");
          Intent intent = new Intent(TransactionActivity.this, MainActivity.class);
          intent.putExtra("sessionId", sessionId);
          intent.putExtra("encryptionKey", encryptionKey);
          intent.putExtra("softDescriptor", softDescriptor);
          startActivity(intent);
        }

        public void receiveClose() {
          Log.d("Pagar.me", "Receive close");
          mpos.closeConnection();
        }

        public void receiveCardHash(String cardHash, MposPaymentResult result) {
          Log.d("Pagar.me", "Card Hash is " + cardHash);
          Log.d("Pagar.me", "Card Brand is " + result.cardBrand);
          Log.d("Pagar.me", "FD = " + result.cardFirstDigits + " LD = " + result.cardLastDigits);
          Log.d("Pagar.me", "ONL = " + result.isOnline);

          try {
            // Inicializa o JSON da transação
            jsonBody = new JSONObject();
            jsonBody.put("card_hash", cardHash);
            jsonBody.put("amount", amount);
            jsonBody.put("installments", instalmentsSpinner.getSelectedItemPosition());
            jsonBody.put("soft_descriptor", softDescriptor);

            Log.d("Pagar.me", jsonBody.toString());

            // Faz o POST no Pagar.me
            postApiMpos(mpos);
          } catch (JSONException e) {
            e.printStackTrace();
          }
        }

        public void receiveError(int error) {
          Log.d("Pagar.me", "Received error " + error);
          if (error == 11){
            mpos.close("APROVADO!");
            Toast.makeText(getApplicationContext(), "Transação enviada com sucesso e salva no banco.", Toast.LENGTH_SHORT).show();
          }
          else
            mpos.closeConnection();
        }

        public void receiveOperationCancelled() {
          Log.d("Pagar.me", "Cancel");
        }
      });

      Log.d("Pagar.me", "Telling to initialize");
      mpos.openConnection(false);
    }
    catch (Exception e) {
      e.printStackTrace();
    }

  }

  private void postApiMpos(final Mpos mpos) {

    RequestQueue nRequestQueue = Volley.newRequestQueue(this);

    JsonObjectRequest jsObjRequest = new JsonObjectRequest
        (Request.Method.POST, urlString + sessionId, jsonBody, new Response.Listener<JSONObject>() {

          public static final String TAG = "POST Pagar.me";

          @Override
          public void onResponse(JSONObject t) {
            String resp = ("Response: " + t.toString());
            Log.i(TAG,resp);
            try {
              String r = t.get("acquirer_response_code").toString();
              //String emv_r = t.get("card_emv_response").toString();
              Log.d("Pagar.me", "resp = " + r);
              //Log.d("Pagar.me", "emv_resp = " + emv_r);
              mpos.finishTransaction(true, Integer.parseInt((String) t.get("acquirer_response_code".toString())), "000000000.0000");
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
        }, new Response.ErrorListener() {

          public static final String TAG = "POST Pagar.me";

          @Override
          public void onErrorResponse(VolleyError error) {
            String resp = ("err: " + error.toString());
            Log.i(TAG,resp);
          }
        });

    nRequestQueue.add(jsObjRequest);
  }

  private void spinnerAction() {
    ArrayAdapter<CharSequence>
        adapter = ArrayAdapter.createFromResource(this, R.array.installments_array, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    instalmentsSpinner.setAdapter(adapter);
  }

  public void showSpinner() {
    spinner.setVisibility(View.VISIBLE);
  }
  public void hideSpinner() {
    spinner.setVisibility(View.GONE);
  }
  public void showSuccess() {
    Toast.makeText(getApplicationContext(), "Transação enviada com sucesso.", Toast.LENGTH_LONG).show();
  }
}
