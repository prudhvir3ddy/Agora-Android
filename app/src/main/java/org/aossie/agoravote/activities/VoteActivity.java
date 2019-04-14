package org.aossie.agoravote.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import org.aossie.agoravote.ItemClickListener;
import org.aossie.agoravote.R;
import org.aossie.agoravote.adapters.RecyclerViewAdapter1;
import org.aossie.agoravote.adapters.RecyclerViewAdapter2;

import java.util.ArrayList;
import java.util.List;

public class VoteActivity extends AppCompatActivity implements ItemClickListener {

    private RecyclerView recyclerView1;
    private RecyclerView recyclerView2;
    private RecyclerViewAdapter1 adapter1;
    private RecyclerViewAdapter2 adapter2;
    private List<String> strings1;
    private List<String> strings2;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        strings1 = new ArrayList<>();
        strings1.add("prudhvi");
        strings1.add("icemc");
        strings2 = new ArrayList<>();
        strings2.add("Thuva");
        strings2.add("vibhav");
        strings2.add("bruno");

        adapter1 = new RecyclerViewAdapter1(getApplicationContext(), strings1);
        adapter2 = new RecyclerViewAdapter2(getApplicationContext(), strings2);
        recyclerView1 = findViewById(R.id.recyclerview1);
        recyclerView2 = findViewById(R.id.recyclerview2);

        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView1.setAdapter(adapter1);
        adapter1.setClickListener(this);

        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(adapter2);
        adapter2.setClickListener(this);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP | ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                moveItem(viewHolder.getAdapterPosition(), viewHolder1.getAdapterPosition());
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                deleteItem(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView2);

    }


    private void deleteItem(int adapterPosition) {
        String s = strings2.get(adapterPosition);
        strings2.remove(adapterPosition);
        strings1.add(s);
        adapter1.notifyDataSetChanged();
        adapter2.notifyDataSetChanged();
    }

    private void moveItem(int adapterPosition, int adapterPosition1) {
        String s = strings2.get(adapterPosition);
        strings2.remove(adapterPosition);
        strings2.add(adapterPosition1, s);
        adapter2.notifyItemMoved(adapterPosition, adapterPosition1);
    }

    @Override
    public void onClick(View view, int position) {
        if (view.getContentDescription().equals("add")) {
            String s = strings1.get(position);
            strings1.remove(position);
            strings2.add(s);
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        } else {
            String s = strings2.get(position);
            strings2.remove(position);
            strings1.add(s);
            adapter1.notifyDataSetChanged();
            adapter2.notifyDataSetChanged();
        }
    }
}
