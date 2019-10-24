package com.alston.cuteweatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.widget.AppCompatEditText;

public class ClearableEditText extends AppCompatEditText {
    private EditText mView;

    public ClearableEditText(Context context, EditText view) {
        super(context);
        mView = view;
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setOnTouchListener(final EditText view){
        view.setOnTouchListener(new OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(event.getRawX()>=(view.getWidth()-view.getTotalPaddingRight())){
                        view.setText("");
                        return true;
                    }
                }
                return false;
            }
        });
    }



//    View.OnTouchListener { _, event ->
//        if (event.action == MotionEvent.ACTION_UP) {
//            if (event.rawX >= (this.right - this.compoundPaddingRight)) {
//                this.setText("")
//                return@OnTouchListener true
//            }
//        }
//        return@OnTouchListener false
}
