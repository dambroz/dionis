package com.dambroz.dionis.main.adapters.listeners;

import android.view.MotionEvent;
import android.view.View;

import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewTouchListener implements RecyclerView.OnItemTouchListener {
    private GestureDetectorCompat gestureDetector;

    public RecyclerViewTouchListener(GestureDetectorCompat gestureDetectorCompat) {
        gestureDetector = gestureDetectorCompat;
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {


        return gestureDetector.onTouchEvent(e);
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
