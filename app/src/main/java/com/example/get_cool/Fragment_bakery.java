package com.example.get_cool;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;




// 베이커리 가게 소개 페이지들을 수용(?)하는 Fragment (정리는 덜 되어있지만 코드상 오류는 없음)

public class Fragment_bakery extends Fragment {

    private ViewPager vpPager;
    private MyPagerAdapter adapterViewPager;

    public Fragment_bakery() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bakery, container, false);

        vpPager = view.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager()); // getSupportFragmentManager() 대신 getChildFragmentManager() 사용
        vpPager.setAdapter(adapterViewPager);

        return view;
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 3;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // 여기에 쓰여진 가게 설명은 각 상세 페이지 화면의 EditText에 기입됨
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BakeryFirstFragment.newInstance("오이소", "정통 프렌치 스타일 프라이엄 베이커리 브랜드");
                case 1:
                    return BakerySecondFragment.newInstance("루아즈 블랑제리", "원하는 페이지 설명");
                case 2:
                    return BakeryThirdFragment.newInstance("르디투어 광교", "원하는 페이지 설명");
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "page " + position;
        }
    }
}