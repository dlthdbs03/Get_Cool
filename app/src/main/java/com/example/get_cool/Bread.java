package com.example.get_cool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;


import java.util.ArrayList;

public class Bread {
    private Context context;
    private ArrayList<TMapMarkerItem> MarkerItems = new ArrayList<>();

    TMapMarkerItem bmarkerItem1 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem2 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem3 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem4 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem5 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem6 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem7 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem8 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem9 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem10 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem11 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem12 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem13 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem14 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem15 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem16 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem17 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem18 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem19 = new TMapMarkerItem();
    TMapMarkerItem bmarkerItem20 = new TMapMarkerItem();



    public Bread(Context context) {
        this.context = context;
        setMarkerItems();
    }

    public ArrayList<TMapMarkerItem> getMarkerItems() {
        return MarkerItems;
    }

    public void setMarkerItems() {
        TMapPoint tMapPoint1 = new TMapPoint(37.298316, 127.045018); // 오이소
        bmarkerItem1.setTMapPoint(tMapPoint1);
        bmarkerItem1.setName("오이소"); // 마커의 타이틀 지정
        setMarker(bmarkerItem1);
        MarkerItems.add(bmarkerItem1);

        TMapPoint tMapPoint2 = new TMapPoint(37.299805, 127.042135); // 카페 주인
        bmarkerItem2.setTMapPoint(tMapPoint2);
        bmarkerItem2.setName("카페 주인"); // 마커의 타이틀 지정
        setMarker(bmarkerItem2);
        MarkerItems.add(bmarkerItem2);

        TMapPoint tMapPoint3 = new TMapPoint(37.298670, 127.044270); // 생폴 드 카페
        bmarkerItem3.setTMapPoint(tMapPoint3);
        bmarkerItem3.setName("생폴 드 카페");
        setMarker(bmarkerItem3);
        MarkerItems.add(bmarkerItem3);

        TMapPoint tMapPoint4 = new TMapPoint(37.299716, 127.045714); // 마룬커피로스터즈
        bmarkerItem4.setTMapPoint(tMapPoint4);
        bmarkerItem4.setName("마룬커피로스터즈");
        setMarker(bmarkerItem4);
        MarkerItems.add(bmarkerItem4);

        TMapPoint tMapPoint5 = new TMapPoint(37.298928, 127.046065); // 반하다마카롱
        bmarkerItem5.setTMapPoint(tMapPoint5);
        bmarkerItem5.setName("반하다마카롱"); // 마커의 타이틀 지정
        setMarker(bmarkerItem5);
        MarkerItems.add(bmarkerItem5);

        TMapPoint tMapPoint6 = new TMapPoint(37.298502, 127.045343); // 오카방고
        bmarkerItem6.setTMapPoint(tMapPoint6);
        bmarkerItem6.setName("오카방고"); // 마커의 타이틀 지정
        setMarker(bmarkerItem6);
        MarkerItems.add(bmarkerItem6);

        TMapPoint tMapPoint7 = new TMapPoint(37.300210, 127.044544); // 쿠키란
        bmarkerItem7.setTMapPoint(tMapPoint7);
        bmarkerItem7.setName("쿠키란"); // 마커의 타이틀 지정
        setMarker(bmarkerItem7);
        MarkerItems.add(bmarkerItem7);

        TMapPoint tMapPoint8 = new TMapPoint(37.300507, 127.043285); // 카페 오르카
        bmarkerItem8.setTMapPoint(tMapPoint8);
        bmarkerItem8.setName("카페 오르카"); // 마커의 타이틀 지정
        setMarker(bmarkerItem8);
        MarkerItems.add(bmarkerItem8);

        TMapPoint tMapPoint9 = new TMapPoint(37.300177, 127.030716); // 행복한찹쌀꽈배기
        bmarkerItem9.setTMapPoint(tMapPoint9);
        bmarkerItem9.setName("행복한찹쌀꽈배기"); // 마커의 타이틀 지정
        setMarker(bmarkerItem9);
        MarkerItems.add(bmarkerItem9);

        TMapPoint tMapPoint10 = new TMapPoint(37.297429, 127.029241); // 또띠앙과자점
        bmarkerItem10.setTMapPoint(tMapPoint10);
        bmarkerItem10.setName("또띠앙과자점"); // 마커의 타이틀 지정
        setMarker(bmarkerItem10);
        MarkerItems.add(bmarkerItem10);

        TMapPoint tMapPoint11 = new TMapPoint(37.2925, 127.0303); // 파리바게트 연무점
        bmarkerItem11.setTMapPoint(tMapPoint11);
        bmarkerItem11.setName("파리바게트 연무점"); // 마커의 타이틀 지정
        setMarker(bmarkerItem11);
        MarkerItems.add(bmarkerItem11);

        TMapPoint tMapPoint12 = new TMapPoint(37.298565, 127.029945); // 막걸리술빵AND도너츠
        bmarkerItem12.setTMapPoint(tMapPoint12);
        bmarkerItem12.setName("막걸리술빵AND도너츠"); // 마커의 타이틀 지정
        setMarker(bmarkerItem12);
        MarkerItems.add(bmarkerItem12);

        TMapPoint tMapPoint13 = new TMapPoint(37.295327, 127.025280); // 유어마이선샤인
        bmarkerItem13.setTMapPoint(tMapPoint13);
        bmarkerItem13.setName("유어마이선샤인"); // 마커의 타이틀 지정
        setMarker(bmarkerItem13);
        MarkerItems.add(bmarkerItem13);

        TMapPoint tMapPoint14 = new TMapPoint(37.294516, 127.026368); // 밀각시베이커리
        bmarkerItem14.setTMapPoint(tMapPoint14);
        bmarkerItem14.setName("밀각시베이커리"); // 마커의 타이틀 지정
        setMarker(bmarkerItem14);
        MarkerItems.add(bmarkerItem14);

        TMapPoint tMapPoint15 = new TMapPoint(37.294463, 127.025935); // 빵굽는 마을
        bmarkerItem15.setTMapPoint(tMapPoint15);
        bmarkerItem15.setName("빵굽는 마을"); // 마커의 타이틀 지정
        setMarker(bmarkerItem15);
        MarkerItems.add(bmarkerItem15);

        TMapPoint tMapPoint16 = new TMapPoint(37.294936, 127.027179); // 자미당
        bmarkerItem16.setTMapPoint(tMapPoint16);
        bmarkerItem16.setName("자미당"); // 마커의 타이틀 지정
        setMarker(bmarkerItem16);
        MarkerItems.add(bmarkerItem16);

        showBalloon();
    }


    private void showBalloon() {
        for (int i = 0; i<MarkerItems.size(); i++) {
            TMapMarkerItem selectedItem = MarkerItems.get(i);
            selectedItem.setCanShowCallout(true);
            selectedItem.setCalloutTitle(selectedItem.getName());
        }
    }

    private void setMarker(TMapMarkerItem markerItem) {
        if (context != null) {
            // 컨텍스트가 null이 아닌 경우에만 리소스 가져오기
            Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_img);
            bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, false);

            markerItem.setIcon(bitmap);
            markerItem.setPosition(0.5f, 1.0f);

        }
    }

}
