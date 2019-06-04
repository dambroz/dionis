package com.dambroz.dionis.main.adapters.listeners;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.dambroz.dionis.main.adapters.listeners.RecyclerViewClickListener;

public class GestureListener extends GestureDetector.SimpleOnGestureListener {
    private RecyclerViewClickListener clickListener;
    private RecyclerView recyclerView;

    public GestureListener(RecyclerView recyclerView, RecyclerViewClickListener clickListener
                           ) {
        this.clickListener = clickListener;
        this.recyclerView = recyclerView;
    }
    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_UP) {
            View child = getView(e);
            int pos = getPosition(child);
            if (clickListener != null) {
                clickListener.onClick(child, pos);
            }
        }
        return true;
    }


    private View getView(MotionEvent e) {
        return recyclerView.findChildViewUnder(e.getX(), e.getY());
    }

    private int getPosition(View child) {
        //TODO: разобраться почему приходит -1
        return recyclerView.getChildAdapterPosition(child);
    }


}


