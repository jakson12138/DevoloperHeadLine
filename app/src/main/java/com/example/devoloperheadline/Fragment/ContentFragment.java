package com.example.devoloperheadline.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devoloperheadline.R;

public class ContentFragment extends Fragment {

    private TextView tv;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content,null);
        tv = (TextView) rootView.findViewById(R.id.tv_content);
        return rootView;
    }

    public void setContent(String content){
        tv.setText(content);
    }

}
