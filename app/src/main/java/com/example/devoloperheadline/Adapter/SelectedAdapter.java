package com.example.devoloperheadline.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.devoloperheadline.Entity.SelectedArticle;
import com.example.devoloperheadline.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<SelectedArticle> selectedArticles;

    public SelectedAdapter(Context context){
        inflater = LayoutInflater.from(context);
        selectedArticles = new ArrayList<>();
        initData();
    }

    private void initData(){
        for(int i = 0;i < 50;i++){
            SelectedArticle selectedArticle = new SelectedArticle(i,"精选文章"+i,i,i,"");
            selectedArticles.add(selectedArticle);
        }
    }

    public int getCount(){
        return selectedArticles.size();
    }

    public Object getItem(int position){
        return selectedArticles.get(position);
    }

    public long getItemId(int position){
        return selectedArticles.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        ViewHolder holder;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.fragment_selected_item,null);
            holder = new ViewHolder();
            holder.title = (TextView) convertView.findViewById(R.id.tv_title);
            holder.like = (TextView) convertView.findViewById(R.id.tv_like);
            holder.comment = (TextView) convertView.findViewById(R.id.tv_comment);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        SelectedArticle selectedArticle = selectedArticles.get(position);
        holder.title.setText(selectedArticle.getTitle());
        holder.like.setText(selectedArticle.getLikeNumber()+"");
        holder.comment.setText(selectedArticle.getCommentNumber()+"");
        return convertView;
    }

    private class ViewHolder{
        private TextView title;
        private TextView like;
        private TextView comment;
    }

}
