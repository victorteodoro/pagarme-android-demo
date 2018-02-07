package me.pagar.demoandroidpagarmempos.adapters.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import me.pagar.demoandroidpagarmempos.R;
import me.pagar.demoandroidpagarmempos.models.Transaction;

/**
 * Created by victor on 26/01/18.
 */

public class TransactionViewHolder extends RecyclerView.ViewHolder {
  @BindView(R.id.holder_name) TextView cardHolderName;
  @BindView(R.id.card_pan) TextView cardPAN;
  @BindView(R.id.transaction_amount) TextView transactionAmount;
  @BindView(R.id.transaction_date) TextView transactionDate;

  private Context context;

  public TransactionViewHolder(View itemView) {
    super(itemView);
    ButterKnife.bind(this, itemView);
    context = itemView.getContext();
  }

  public void bindTransaction(Transaction transaction) {
    if(transaction.getCardHolderName() != null)
      cardHolderName.setText(transaction.getCardHolderName());
    else
      cardHolderName.setText("");

    if(transaction.getCardLastDigits() != null)
      cardPAN.setText("Final ".concat(transaction.getCardLastDigits()));
    else
      cardPAN.setText("");

    NumberFormat format = NumberFormat.getCurrencyInstance();
    transactionAmount.setText(format.format(transaction.getAmount()/100.0));

    transactionDate.setText(dateFormat(transaction.getDateCreated()));
  }

  private String dateFormat(String date) {
    return date.substring(8, 10) + '/' + date.substring(5, 7) + '/' + date.substring(0, 4);
  }
}
