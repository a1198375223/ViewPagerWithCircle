package com.example.viewpagerwithcircle;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CommonFragment extends Fragment {
    private String text;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(text);
        return view;
    }

    public void setText(String text) {
        this.text = text;
    }
}
