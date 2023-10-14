package com.example.get_cool;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;




// 베이커리 상세 페이지의 첫번째 화면 java 파일


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BakeryFirstFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BakeryFirstFragment extends Fragment {

    private String title;    //가게 이름
    private String content;     // 가게 상세 설명

    //newInstance constructor for creagting fragment with arguments
    public static BakeryFirstFragment newInstance(String title, String content) {
        BakeryFirstFragment fragment = new BakeryFirstFragment();
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
        View view = inflater.inflate(R.layout.fragment_bakery_first, container, false);
        EditText tvLabel = (EditText) view.findViewById(R.id.editTitle);
        EditText tvLabel2 = (EditText) view.findViewById(R.id.editContent);
        tvLabel.setText(title);
        tvLabel2.setText(content);
        return view;
    }
}