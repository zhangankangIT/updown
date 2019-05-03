package com.example.tuodong;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

import java.util.Collections;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements TouchCallBack{
    private List<Bean.DataBean> list;
    Context context;

    public Adapter(List<Bean.DataBean> list, Context context) {
        this.list = list;
        this.context = context;
    }
    @Override
    public void itemMove(int fromposition, int toPosition) {
        Collections.swap(list,fromposition,toPosition);
        notifyItemMoved(fromposition,toPosition);
    }

    @Override
    public void onItemDelete(int position) {
        list.remove(position);
        notifyItemRemoved(position);

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item, null);

        return new MyView(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        MyView myView= (MyView) viewHolder;

        RequestOptions options = RequestOptions.circleCropTransform()
                        .placeholder(R.mipmap.ic_launcher)
                        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                        .skipMemoryCache(true);
                Glide.with(context).load(list.get(i).getPic()).apply(options).into(myView.img);
        myView.txt.setText(list.get(i).getFood_str());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class MyView extends RecyclerView.ViewHolder {

        private final ImageView img;
        private final TextView txt;

        public MyView(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            txt = itemView.findViewById(R.id.txt);
        }
    }
}
