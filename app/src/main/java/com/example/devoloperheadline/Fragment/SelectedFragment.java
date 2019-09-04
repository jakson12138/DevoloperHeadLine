package com.example.devoloperheadline.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.devoloperheadline.Adapter.SelectedAdapter;
import com.example.devoloperheadline.Adapter.SelectedPagerAdapter;
import com.example.devoloperheadline.Interface.ICarousePagerSelectView;
import com.example.devoloperheadline.R;

import java.util.Timer;
import java.util.TimerTask;

public class SelectedFragment extends Fragment {

    private ViewPager viewPager;
    private SelectedPagerAdapter selectedPagerAdapter;

    private ImageView[] tips;//底部

    private Timer timer;
    private final int CAROUSEL_TIME = 3000;//滚动间隔

    private int currentIndex = 0;

    private TextView tvContent;
    private String[] carousePagerStr = new String[]{"Android","React Native","Python"};

    private ListView listView;
    private SelectedAdapter selectedAdapter;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_selected,null);
        View headView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_selected_header,null);

        tvContent = (TextView) headView.findViewById(R.id.tv_content);
        tvContent.setText(carousePagerStr[0]);

        viewPager = (ViewPager)headView.findViewById(R.id.viewpager_selected_header);
        selectedPagerAdapter = new SelectedPagerAdapter(getActivity(),carousePagetSelectView);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(onPageChangeListener);
        viewPager.setAdapter(selectedPagerAdapter);

        ViewGroup group = (ViewGroup) headView.findViewById(R.id.viewGroup);
        tips = new ImageView[3];
        for(int i = 0;i < tips.length;i++){
            ImageView imageView = new ImageView(getActivity());
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
            group.addView(imageView,lp);
        }

        timer = new Timer(true);
        timer.schedule(task,0,CAROUSEL_TIME);

        listView = (ListView) rootView.findViewById(R.id.list);
        listView.addHeaderView(headView); //设置listview的头部
        selectedAdapter = new SelectedAdapter(getActivity());
        listView.setAdapter(selectedAdapter);

        return rootView;
    }

    private ICarousePagerSelectView carousePagetSelectView = new ICarousePagerSelectView() {
        @Override
        public void carouseSelect(int index) {
            Toast.makeText(getActivity(),carousePagerStr[index],Toast.LENGTH_SHORT).show();
        }
    };

    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            hanlder.sendEmptyMessage(CAROUSEL_TIME);
        }
    };

    private Handler hanlder = new Handler(){
        public void handlerMessage(Message msg){
            switch(msg.what){
                case CAROUSEL_TIME:
                    if(currentIndex >= tips.length-1){
                        viewPager.setCurrentItem(0);
                    }
                    else{
                        viewPager.setCurrentItem(currentIndex+1);
                    }
                    break;
            }
        }
    };
    @Override
    public void onDestroy(){
        super.onDestroy();
        task.cancel();
        System.exit(0);
    }

    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int i, float v, int i1) {

        }

        @Override
        public void onPageSelected(int i) {
            tvContent.setText(carousePagerStr[i]);
            setImageBackground(i);
            currentIndex = i;
        }

        @Override
        public void onPageScrollStateChanged(int i) {

        }
    };

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

}
