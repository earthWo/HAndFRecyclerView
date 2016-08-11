package com.orange.handfrecyclerview;

import android.graphics.drawable.GradientDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import model.HandFRecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HandFRecyclerView recyclerView= (HandFRecyclerView) findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setHeaderView(R.layout.header);

        ImageView  headerView= (ImageView) LayoutInflater.from(this).inflate(R.layout.header,recyclerView,false);
        headerView.setImageResource(R.mipmap.header2);
        recyclerView.setHeaderView(headerView);

        ImageView  footer1= (ImageView) LayoutInflater.from(this).inflate(R.layout.header,recyclerView,false);
        footer1.setImageResource(R.mipmap.footer2);

        recyclerView.setFooterView(footer1);
        recyclerView.setFooterView(R.layout.footer);

        recyclerView.setAdapter(adapter);
    }

    private class Holder extends RecyclerView.ViewHolder{

        private TextView textView;

        public Holder(View itemView) {
            super(itemView);
            textView= (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

    private RecyclerView.Adapter adapter=new RecyclerView.Adapter() {
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_recyclerview,parent,false);
            return new Holder(v);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            ((Holder)holder).textView.setText("第"+position+"个item");
        }

        @Override
        public int getItemCount() {
            return 10;
        }
    };
}