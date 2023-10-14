package com.example.get_cool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPoint;

import java.util.ArrayList;

public class Grocery {
    private Context context;
    private ArrayList<TMapMarkerItem> MarkerItems = new ArrayList<>();

    TMapMarkerItem gmarkerItem1 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem2 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem3 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem4 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem5 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem6 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem7 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem8 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem9 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem10 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem11 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem12 = new TMapMarkerItem();
    TMapMarkerItem gmarkerItem13 = new TMapMarkerItem();

    public Grocery(Context context) {
        this.context = context;
        setMarkerItems();
    }

    public ArrayList<TMapMarkerItem> getMarkerItems() {
        return MarkerItems;
    }

    private void setMarkerItems() {

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
