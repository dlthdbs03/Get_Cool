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


public class Fragment_bakery extends Fragment {

    private ViewPager vpPager;
    private MyPagerAdapter adapterViewPager;

    private Handler handler;        // 추가한 것
    private int currentItem = 0;       // 추가한 것
    private static final int NUM_ITEMS = 3;         // 추가한 것
    private static final long AUTO_SWIPE_DELAY = 8000; // 추가한 것 (8 seconds 후 넘어가도록 함)


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
                    return Fragment_bakery_first.newInstance("오이소", "정통 프렌치 스타일 프라이엄 베이커리 브랜드");
                case 1:
                    return Fragment_bakery_second.newInstance("행복한찹쌀꽈배기", "당일반죽! 당일 소진!");
                case 2:
                    return Fragment_bakery_third.newInstance("막걸리 술빵&도너츠", "25년의 추억을 담아 만들었습니다.");
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