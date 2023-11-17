package com.example.get_cool;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_pastry_first#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_pastry_first extends Fragment {

    //Store instance variables
    private String title;
    private String content;

    //newInstance constructor for creagting fragment with arguments
    public static Fragment_pastry_first newInstance(String title, String content) {
        Fragment_pastry_first fragment = new Fragment_pastry_first();
        Bundle args = new Bundle();
        args.putString("someTitle", title);
        args.putString("someContent", content);
        fragment.setArguments(args);
        return fragment;
    }

    //Store instance variables based on arguments passed
    //Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");
        content = getArguments().getString("someContent");
    }

    //Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pastry_first, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.editTitle);
        TextView tvLabel2 = (TextView) view.findViewById(R.id.editContent);
        tvLabel.setText(title);
        tvLabel2.setText(content);
        return view;
    }
}