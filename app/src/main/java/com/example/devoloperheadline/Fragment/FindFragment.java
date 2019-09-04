package com.example.devoloperheadline.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.devoloperheadline.R;

public class FindFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content, null);
        TextView tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        tvContent.setText("发现");
        return rootView;
    }
}
