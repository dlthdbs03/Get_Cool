package com.example.get_cool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;

import java.util.ArrayList;

public class Cafe {
    private Context context;
    private ArrayList<TMapMarkerItem> MarkerItems = new ArrayList<>();
    TMapMarkerItem cmarkerItem1 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem2 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem3 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem4 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem5 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem6 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem7 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem8 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem9 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem10 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem11 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem12 = new TMapMarkerItem();
    TMapMarkerItem cmarkerItem13 = new TMapMarkerItem();

    public Cafe(Context context) {
        this.context = context;
        setMarkerItems();
    }

    public ArrayList<TMapMarkerItem> getMarkerItems() {
        return MarkerItems;
    }

    private void setMarkerItems() {
        TMapPoint tMapPoint1 = new TMapPoint(37.299805, 127.042135); // 카페 주인
        cmarkerItem1.setTMapPoint(tMapPoint1);
        cmarkerItem1.setName("카페 주인"); // 마커의 타이틀 지정
        setMarker(cmarkerItem1);
        MarkerItems.add(cmarkerItem1);

        TMapPoint tMapPoint2 = new TMapPoint(37.298670, 127.044270); // 생폴 드 카페
        cmarkerItem2.setTMapPoint(tMapPoint2);
        cmarkerItem2.setName("생폴 드 카페");
        setMarker(cmarkerItem2);
        MarkerItems.add(cmarkerItem2);

        TMapPoint tMapPoint3 = new TMapPoint(37.299716, 127.045714); // 마룬커피로스터즈
        cmarkerItem3.setTMapPoint(tMapPoint3);
        cmarkerItem3.setName("마룬커피로스터즈");
        setMarker(cmarkerItem3);
        MarkerItems.add(cmarkerItem3);

        TMapPoint tMapPoint4 = new TMapPoint(37.298502, 127.045343); // 오카방고
        cmarkerItem4.setTMapPoint(tMapPoint4);
        cmarkerItem4.setName("오카방고"); // 마커의 타이틀 지정
        setMarker(cmarkerItem4);
        MarkerItems.add(cmarkerItem4);

        TMapPoint tMapPoint5 = new TMapPoint(37.300210, 127.044544); // 쿠키란
        cmarkerItem5.setTMapPoint(tMapPoint5);
        cmarkerItem5.setName("쿠키란"); // 마커의 타이틀 지정
        setMarker(cmarkerItem5);
        MarkerItems.add(cmarkerItem5);

        TMapPoint tMapPoint6 = new TMapPoint(37.300507, 127.043285); // 카페 오르카
        cmarkerItem6.setTMapPoint(tMapPoint6);
        cmarkerItem6.setName("카페 오르카"); // 마커의 타이틀 지정
        setMarker(cmarkerItem6);
        MarkerItems.add(cmarkerItem6);

        TMapPoint tMapPoint7 = new TMapPoint(37.295224, 127.025105); // 235커피
        cmarkerItem7.setTMapPoint(tMapPoint7);
        cmarkerItem7.setName("235COFFEE"); // 마커의 타이틀 지정
        setMarker(cmarkerItem7);
        MarkerItems.add(cmarkerItem7);

        TMapPoint tMapPoint8 = new TMapPoint(37.294847, 127.026610); // 카페 오르카
        cmarkerItem8.setTMapPoint(tMapPoint8);
        cmarkerItem8.setName("하이오커피"); // 마커의 타이틀 지정
        setMarker(cmarkerItem8);
        MarkerItems.add(cmarkerItem8);

        TMapPoint tMapPoint9 = new TMapPoint(37.297040, 127.030596); // 커피있는하루
        cmarkerItem9.setTMapPoint(tMapPoint9);
        cmarkerItem9.setName("커피있는하루"); // 마커의 타이틀 지정
        setMarker(cmarkerItem9);
        MarkerItems.add(cmarkerItem9);

        TMapPoint tMapPoint10 = new TMapPoint(37.292359, 127.029778); // 몽그르르
        cmarkerItem10.setTMapPoint(tMapPoint10);
        cmarkerItem10.setName("몽그르르"); // 마커의 타이틀 지정
        setMarker(cmarkerItem10);
        MarkerItems.add(cmarkerItem10);

        TMapPoint tMapPoint11 = new TMapPoint(37.294774, 127.024663); // 스니프 스니퍼즈
        cmarkerItem11.setTMapPoint(tMapPoint11);
        cmarkerItem11.setName("스니프 스니퍼즈"); // 마커의 타이틀 지정
        setMarker(cmarkerItem11);
        MarkerItems.add(cmarkerItem11);

        TMapPoint tMapPoint12 = new TMapPoint(37.293744, 127.027014); // 카페 리무
        cmarkerItem12.setTMapPoint(tMapPoint12);
        cmarkerItem12.setName("카페 리무"); // 마커의 타이틀 지정
        setMarker(cmarkerItem12);
        MarkerItems.add(cmarkerItem12);

        TMapPoint tMapPoint13 = new TMapPoint(37.296192, 127.030871); // 와이엇
        cmarkerItem13.setTMapPoint(tMapPoint13);
        cmarkerItem13.setName("와이엇(WYATT)"); // 마커의 타이틀 지정
        setMarker(cmarkerItem13);
        MarkerItems.add(cmarkerItem13);

        for (int i = 0; i<MarkerItems.size(); i++) {
            TMapMarkerItem selectedItem = MarkerItems.get(i);
            selectedItem.setCanShowCallout(true);
            selectedItem.setCalloutTitle(selectedItem.getName());
        }
    }

    private void setMarker(TMapMarkerItem markerItem) {
        // 마커 아이콘
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker_img);
        bitmap = Bitmap.createScaledBitmap(bitmap, 50, 50, false);

        markerItem.setIcon(bitmap); // 마커 아이콘 지정
        markerItem.setPosition(0.5f, 1.0f); // 마커의 중심점을 중앙, 하단으로 설정
    }
}
