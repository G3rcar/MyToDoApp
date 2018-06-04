package com.creativa.mytodoapp.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.creativa.mytodoapp.R;
import com.creativa.mytodoapp.items.ToDoItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {
    private List<ToDoItem> mDataSet;
    private OnItemClickListener listener;


    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txvTitle) public TextView txvTitle;
        @BindView(R.id.txvDescription) public TextView txvDescription;
        @BindView(R.id.lyoTodoItem) public LinearLayout lyoTodoItem;
        public ViewHolder(View v) {
            super(v);
            ButterKnife.bind(this,v);
        }

        @OnClick(R.id.lyoTodoItem)
        public void onClick(View v){
            if(listener!=null){
                Integer position = (Integer) v.getTag();
                ToDoItem item = mDataSet.get(position);
                listener.onItemClick(item);
            }
        }
    }

    public ToDoListAdapter(List<ToDoItem> myDataSet) {
        mDataSet = myDataSet;
    }

    public void setData(List<ToDoItem> myDataSet) {
        mDataSet = myDataSet;
        notifyDataSetChanged();
    }

    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ToDoItem item = mDataSet.get(position);

        holder.lyoTodoItem.setTag(position);
        holder.txvTitle.setText(item.getTitle());
        holder.txvDescription.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }


    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onItemClick(ToDoItem item);
    }
}
