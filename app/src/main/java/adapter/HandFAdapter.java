package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuzefeng on 16/8/10.
 */

public class HandFAdapter extends RecyclerView.Adapter {


    private List<View> headerViewList=new ArrayList<>();

    private List<View>footerViewList=new ArrayList<>();

    private RecyclerView.Adapter adapter;

    private static final int HEADER_FOOTER_TYPE=100;



    public void addHeaderView(int id, Context context, RecyclerView recyclerView){
        if(context!=null&&headerViewList!=null){
            View v= LayoutInflater.from(context).inflate(id,recyclerView,false);
            headerViewList.add(v);
        }
    }

    public void addHeaderView(View view){
        if(view!=null&&headerViewList!=null){
            headerViewList.add(view);
        }
    }

    public void addFooterView(int id, Context context, RecyclerView recyclerView){
        if(context!=null&&footerViewList!=null){
            View v= LayoutInflater.from(context).inflate(id,recyclerView,false);
            footerViewList.add(v);
        }
    }

    public void addFooterView(View view){
        if(view!=null&&footerViewList!=null){
            footerViewList.add(view);
        }
    }



    public void setInnerAdapter(RecyclerView.Adapter adapter){
        if(adapter!=null){
            this.adapter=adapter;
        }else{
            throw new NullPointerException("adapter 不能为空");
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType<HEADER_FOOTER_TYPE+headerViewList.size()&&viewType>=HEADER_FOOTER_TYPE){
            return new Holder(headerViewList.get(viewType-HEADER_FOOTER_TYPE));
        }else if(viewType>=headerViewList.size()+getInnerAdapterItemCount()){
            return new Holder(footerViewList.get(viewType-headerViewList.size()-getInnerAdapterItemCount()-HEADER_FOOTER_TYPE));
        }else{
            return adapter.onCreateViewHolder(parent,viewType);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(!(holder instanceof Holder)&&adapter!=null){
           adapter.onBindViewHolder(holder,position-headerViewList.size());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(position<headerViewList.size()||position>=headerViewList.size()+getInnerAdapterItemCount()){
            return HEADER_FOOTER_TYPE+position;
        }else{
            return adapter.getItemViewType(position-headerViewList.size());
        }
    }

    @Override
    public int getItemCount() {
        return adapter==null?0:headerViewList.size()+getInnerAdapterItemCount()+footerViewList.size();
    }



    private int getInnerAdapterItemCount(){
        return adapter==null?0:adapter.getItemCount();
    }


    private class Holder extends RecyclerView.ViewHolder{

        public Holder(View itemView) {
            super(itemView);
        }
    }


    public boolean isHeaderOrFooter(int position){
        return position<headerViewList.size()||position>=headerViewList.size()+getInnerAdapterItemCount();
    }
}
