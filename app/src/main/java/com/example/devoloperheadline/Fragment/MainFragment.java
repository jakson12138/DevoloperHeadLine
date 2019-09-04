package com.example.devoloperheadline.Fragment;

import android.app.Activity;
import android.net.wifi.aware.SubscribeConfig;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.TableLayout;
import android.widget.TextView;

import com.example.devoloperheadline.Activity.MainActivity;
import com.example.devoloperheadline.Adapter.FragmentAdapter;
import com.example.devoloperheadline.R;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private ViewPager viewPager;

    private MainActivity.MainDrawerListener drawerListener;

    public MainFragment(){

    }

    public static MainFragment newInstance(MainActivity.MainDrawerListener drawerListener){
        MainFragment mainFragment = new MainFragment();
        mainFragment.drawerListener = drawerListener;
        return mainFragment;
    }

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle saverdInstanceState){
        View rootView = inflater.inflate(R.layout.fragment_main,null);

        viewPager = (ViewPager) rootView.findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(2); //设置缓存页数
        viewPager.setCurrentItem(0);

        FragmentAdapter pagerAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager());
        SelectedFragment selectedFragment = new SelectedFragment();
        SubscribeFragment subscribeFragment = new SubscribeFragment();
        FindFragment findFragment = new FindFragment();

        pagerAdapter.addFragment(selectedFragment,"精选");
        pagerAdapter.addFragment(subscribeFragment,"订阅");
        pagerAdapter.addFragment(findFragment,"发现");

        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = (TabLayout) rootView.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar_menu);
        toolbar.setNavigationIcon(R.mipmap.ic_menu_white);
        toolbar.setTitle("我的开发者头条");
        toolbar.setTitleTextColor(ContextCompat.getColor(getActivity(),android.R.color.white));
        toolbar.setNavigationOnClickListener(onClickListener);
        return rootView;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(drawerListener != null){
                drawerListener.open();
            }
        }
    };

}
