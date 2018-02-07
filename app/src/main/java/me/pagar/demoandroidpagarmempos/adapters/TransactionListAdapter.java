package me.pagar.demoandroidpagarmempos.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;
import me.pagar.demoandroidpagarmempos.adapters.viewholder.TransactionViewHolder;
import me.pagar.demoandroidpagarmempos.models.Transaction;
import me.pagar.demoandroidpagarmempos.R;

/**
 * Created by victor on 26/01/18.
 */

public class TransactionListAdapter extends RecyclerView.Adapter<TransactionViewHolder> {
  private List<Transaction> transactions = new ArrayList<>();
  private Context context;

  public TransactionListAdapter(Context context, List<Transaction> transactions) {
    this.context = context;
    this.transactions = transactions;
  }

  @Override public TransactionViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_line_view, parent, false);
    TransactionViewHolder viewHolder = new TransactionViewHolder(view);
    return viewHolder;
  }

  @Override public void onBindViewHolder(TransactionViewHolder holder, int position) {
    holder.bindTransaction(transactions.get(position));
  }

  @Override public int getItemCount() {
    return transactions.size();
  }
}
