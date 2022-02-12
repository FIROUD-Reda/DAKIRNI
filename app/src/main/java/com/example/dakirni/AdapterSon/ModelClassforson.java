package com.example.dakirni.AdapterSon;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class ModelClassforson {

  private String imageview;
  private String textview1;
  private String textview2;
    private String divider;



    public ModelClassforson(String imageview, String textview1, String textview2, String divider)
   {
       this.imageview=imageview;
       this.textview1=textview1;
       this.textview2=textview2;
       this.divider=divider;
   }

    public String getImageview() {
        return imageview;
    }

        public String getTextview1() {
        return textview1;
    }

    public String getDivider()
    {
        return divider;
    }


    public String getTextview2() {
        return textview2;
    }



}
