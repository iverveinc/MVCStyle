package com.mvcstyle.AppUtils;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Android on 6/15/2015.
 */


public class Fonts {


// private static Context context;
//    public Fonts(Context context){
//this.context=context;
//    }

    public  static  Typeface robotMedium(Context context){
        Typeface  tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Medium_1.ttf");

        return tf;
    }
    public  static  Typeface robotRegular(Context context){
        Typeface  tf = Typeface.createFromAsset(context.getAssets(), "fonts/Roboto-Regular_1.ttf");

        return tf;
    }

}
