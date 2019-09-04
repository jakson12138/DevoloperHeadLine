package com.example.devoloperheadline.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.devoloperheadline.Interface.ILauncherView;
import com.example.devoloperheadline.R;

import java.util.ArrayList;
import java.util.List;

public class LauncherPagerAdapter extends PagerAdapter implements View.OnClickListener {

    private ILauncherView launcherView;
    private List<View> views;
    private Context mContext;

    private int[] images = new int[]{R.drawable.tutorial_1,R.drawable.tutorial_2,R.drawable.tutorial_3,R.drawable.tutorial_4};

    public LauncherPagerAdapter(Context context, ILauncherView launcherView){
        mContext = context;
        views = new ArrayList<>();
        this.launcherView = launcherView;
        for(int i = 0;i < images.length;i++){
            View item = LayoutInflater.from(context).inflate(R.layout.activity_launcher_pager_item,null);
            ImageView imageView = (ImageView) item.findViewById(R.id.imageview);
            imageView.setImageResource(images[i]);
            item.findViewById(R.id.tv_start_headlines).setOnClickListener(this);
            views.add(item);
        }
    }

    public List<View> getViews(){
        return views;
    }

    public int getCount(){
        return views == null ? 0 : views.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    public void destroyItem(ViewGroup container,int position,Object object){
        ((ViewPager) container).removeView(views.get(position));
    }

    public Object instantiateItem(ViewGroup container,int position){
        ((ViewPager) container).addView(views.get(position),0);
        return views.get(position);
    }

    public void onClick(View v){
        if(launcherView != null){
            launcherView.gotoMain();
        }
    }
}
