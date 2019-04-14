package org.aossie.agoravote.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.aossie.agoravote.ItemClickListener;
import org.aossie.agoravote.R;

import java.util.List;

public class RecyclerViewAdapter1 extends RecyclerView.Adapter<RecyclerViewAdapter1.RecyclerViewHolder1> {

    private final List<String> strings;
    private final Context context;
    private ItemClickListener clickListener;

    public RecyclerViewAdapter1(Context context, List<String> strings) {
        this.context = context;
        this.strings = strings;
    }

    @NonNull
    @Override
    public RecyclerViewHolder1 onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_candidate_add, viewGroup, false);
        return new RecyclerViewHolder1(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder1 recyclerViewHolder1, int i) {
        String s = strings.get(i);

        recyclerViewHolder1.textView.setText(s);
        recyclerViewHolder1.textView2.setText(String.valueOf(i + 1) + " .");
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    public class RecyclerViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener {

        final TextView textView;
        final TextView textView2;
        final ImageView imageView;

        RecyclerViewHolder1(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.textView10);
            textView2 = itemView.findViewById(R.id.textView12);
            imageView = itemView.findViewById(R.id.imageView3);
            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }
}

