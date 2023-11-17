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


public class Fragment_cafe extends Fragment {

    private ViewPager vpPager;
    private Fragment_cafe.MyPagerAdapter adapterViewPager;

    private Handler handler;        // 추가한 것
    private int currentItem = 0;       // 추가한 것
    private static final int NUM_ITEMS = 3;         // 추가한 것
    private static final long AUTO_SWIPE_DELAY = 8000; // 추가한 것 (8 seconds 후 넘어가도록 함)


    public Fragment_cafe() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cafe, container, false);

        vpPager = view.findViewById(R.id.vpPager);
        adapterViewPager = new Fragment_cafe.MyPagerAdapter(getChildFragmentManager()); // getSupportFragmentManager() 대신 getChildFragmentManager() 사용
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
                    return Fragment_cafe_first.newInstance("카페 주인", "내일을 만들어가는 사람들의 공간");
                case 1:
                    return Fragment_cafe_second.newInstance("생폴 드 카페", "수원 광교 경기대 후문 분위기 좋은 유럽풍 브런치 카페");
                case 2:
                    return Fragment_cafe_third.newInstance("커피있는 하루", "공인 바리스타가 내리는 차별화된 커피와 다양한 디저트를 즐길 수 있는 감성 카페");
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