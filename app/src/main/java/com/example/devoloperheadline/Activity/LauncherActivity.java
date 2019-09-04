package com.example.devoloperheadline.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.devoloperheadline.Adapter.LauncherPagerAdapter;
import com.example.devoloperheadline.Interface.ILauncherView;
import com.example.devoloperheadline.R;

public class LauncherActivity extends FragmentActivity implements ILauncherView {

    private ViewPager viewPager;
    private LauncherPagerAdapter adapter;

    private ImageView[] tips;

    private ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }
        @Override
        public void onPageSelected(int i) {
            setImageBackground(i);

            TextView tv = (TextView) adapter.getViews().get(i).findViewById(R.id.tv_start_headlines);
            if(i == tips.length - 1){
                tv.setVisibility(View.VISIBLE);
            }
            else{
                tv.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_launcher);

        if(!isFirst()){
            gotoMain();
        }

        viewPager = (ViewPager) findViewById(R.id.viewpager_launcher);
        adapter = new LauncherPagerAdapter(this,this);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(changeListener);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(changeListener);

        ViewGroup group = (ViewGroup) findViewById(R.id.viewGroup);
        tips = new ImageView[4];
        for(int i = 0;i < tips.length;i++){
            ImageView imageView = new ImageView(this);
            if(i == 0){
                imageView.setBackgroundResource(R.drawable.page_indicator_focused);
            }
            else{
                imageView.setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
            tips[i] = imageView;
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            lp.leftMargin = 10;
            lp.rightMargin = 10;
            lp.bottomMargin = 30;
            group.addView(imageView,lp);
        }
    }

    private void setImageBackground(int selectItems){
        for(int i = 0;i < tips.length;i++){
            if(i == selectItems){
                tips[i].setBackgroundResource(R.drawable.page_indicator_focused);
            }
            else{
                tips[i].setBackgroundResource(R.drawable.page_indicator_unfocused);
            }
        }
    }

    public void gotoMain(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    private boolean isFirst(){
        SharedPreferences setting = getSharedPreferences("headlines",0);
        Boolean user_first = setting.getBoolean("FIRST",true);
        if(user_first){
            setting.edit().putBoolean("FIRST",true).commit();
            return true;
        }
        else{
            return false;
        }
    }
}
