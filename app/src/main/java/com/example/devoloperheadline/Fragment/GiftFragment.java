package com.example.devoloperheadline.Fragment;

import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devoloperheadline.Activity.MainActivity;
import com.example.devoloperheadline.R;

public class GiftFragment extends Fragment {

    private TextView tv;

    MainActivity.MainDrawerListener drawerListener;

    public GiftFragment(){}

    public static GiftFragment newInstance(MainActivity.MainDrawerListener drawerListener) {
        GiftFragment fragment = new GiftFragment();
        fragment.drawerListener = drawerListener;
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_gift,null);
        tv = (TextView) rootView.findViewById(R.id.tv_content);

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
