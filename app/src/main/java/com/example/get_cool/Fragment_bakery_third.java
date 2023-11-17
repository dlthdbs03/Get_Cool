package com.example.get_cool;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment_bakery_third#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fragment_bakery_third extends Fragment {

    //Store instance variables
    private String title;
    private String content;

    //newInstance constructor for creagting fragment with arguments
    public static Fragment_bakery_third newInstance(String title, String content) {
        Fragment_bakery_third fragment = new Fragment_bakery_third();
        Bundle args = new Bundle();
        args.putString("someTitle", title);
        args.putString("someContent", content);
        fragment.setArguments(args);
        return fragment;
    }

    //Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("someTitle");
        content = getArguments().getString("someContent"); // "someContent"로 변경
    }

    //Inflate the view for the fragment based on layout XML
    //Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bakery_third, container, false);
        TextView tvLabel = (TextView) view.findViewById(R.id.editTitle);
        TextView tvLabel2 = (TextView) view.findViewById(R.id.editContent);
        tvLabel.setText(title);
        tvLabel2.setText(content);
        return view;
    }
}