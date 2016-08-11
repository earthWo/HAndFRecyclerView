package model;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import adapter.HandFAdapter;

/**
 * Created by wuzefeng on 16/8/10.
 */

public class HandFRecyclerView extends RecyclerView {

    private HandFAdapter adapter;

    public HandFRecyclerView(Context context) {
        super(context,null);
    }

    public HandFRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs,0);
    }

    public HandFRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        adapter=new HandFAdapter();
    }

   public void setHeaderView(int id){
       if(adapter==null){
           init();
       }
        adapter.addHeaderView(id,getContext(),this);
    }


    public void setHeaderView(View view){
        if(adapter==null){
            init();
        }
        adapter.addHeaderView(view);
    }


    public void setFooterView(int id){
        if(adapter==null){
            init();
        }
        adapter.addFooterView(id,getContext(),this);
    }


    public void setFooterView(View view){
        if(adapter==null){
            init();
        }
        adapter.addFooterView(view);
    }

    public void setAdapter(Adapter adapter){
        this.adapter.setInnerAdapter(adapter);
        super.setAdapter(this.adapter);
    }


}
