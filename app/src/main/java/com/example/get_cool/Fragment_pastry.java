package com.example.get_cool;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;   // 추가한 것
import android.os.Looper;    // 추가한 것


public class Fragment_pastry extends Fragment {

    private ViewPager vpPager;
    private MyPagerAdapter adapterViewPager;

    private Handler handler;        // 추가한 것
    private int currentItem = 0;       // 추가한 것
    private static final int NUM_ITEMS = 3;         // 추가한 것
    private static final long AUTO_SWIPE_DELAY = 8000; // 추가한 것 (8 seconds 후 넘어가도록 함)


    public Fragment_pastry() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pastry, container, false);

        vpPager = view.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager()); // getSupportFragmentManager() 대신 getChildFragmentManager() 사용
        vpPager.setAdapter(adapterViewPager);

        handler = new Handler(Looper.getMainLooper());    // 추가한 것
        startAutoSwipe();           // 추가한 것

        return view;
    }

    private void startAutoSwipe() {              // 추가한 것
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                currentItem = (currentItem + 1) % NUM_ITEMS;
                vpPager.setCurrentItem(currentItem, true);
                startAutoSwipe();
            }
        }, AUTO_SWIPE_DELAY);
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

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return Fragment_pastry_first.newInstance("오카방고", "정성껏 만든 수제 디저트와 맛있는 커피와 음료로 달콤한 시간을 선사하겠습니다.");
                case 1:
                    return Fragment_pastry_second.newInstance("쿠키란", "광교역 쿠키란은 엄선된 최상의 재료를 듬뿍 넣어 만든 수제 쿠키 전문점입니다");
                case 2:
                    return Fragment_pastry_third.newInstance("반하다마카롱", "반하다쿠키(아메리칸쿠키) 클래스 외 구움과자 수업합니다.");
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