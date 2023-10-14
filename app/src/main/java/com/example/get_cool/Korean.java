package com.example.get_cool;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.skt.Tmap.TMapMarkerItem;

import java.util.ArrayList;

public class Korean {
    private Context context;
    private ArrayList<TMapMarkerItem> MarkerItems = new ArrayList<>();

    TMapMarkerItem markerItem1 = new TMapMarkerItem();
    TMapMarkerItem markerItem2 = new TMapMarkerItem();
    TMapMarkerItem markerItem3 = new TMapMarkerItem();
    TMapMarkerItem markerItem4 = new TMapMarkerItem();
    TMapMarkerItem markerItem5 = new TMapMarkerItem();
    TMapMarkerItem markerItem6 = new TMapMarkerItem();
    TMapMarkerItem markerItem7 = new TMapMarkerItem();
    TMapMarkerItem markerItem8 = new TMapMarkerItem();
    TMapMarkerItem markerItem9 = new TMapMarkerItem();
    TMapMarkerItem markerItem10 = new TMapMarkerItem();
    TMapMarkerItem markerItem11 = new TMapMarkerItem();
    TMapMarkerItem markerItem12 = new TMapMarkerItem();
    TMapMarkerItem markerItem13 = new TMapMarkerItem();

    public Korean(Context context) {
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

