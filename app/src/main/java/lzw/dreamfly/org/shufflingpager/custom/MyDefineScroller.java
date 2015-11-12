package lzw.dreamfly.org.shufflingpager.custom;

import android.content.Context;
import android.view.animation.Interpolator;
import android.widget.Scroller;

import java.util.jar.Attributes;

/**
 * Created by asus on 2015/11/12.
 */
public class MyDefineScroller extends Scroller {

       private int mDefinefDuration=2000;

       public MyDefineScroller(Context context){
           super(context);
       }

       public MyDefineScroller(Context context, Interpolator interpolator){
           super(context,interpolator);
       }

       @Override
       public void startScroll(int startX,int startY,int dx,int dy,int duration){
             super.startScroll(startX,startY,dx,dy,this.mDefinefDuration);
       }

       @Override
       public void startScroll(int startX,int startY,int dx,int dy){
             super.startScroll(startX,startY,dx,dy,this.mDefinefDuration);
       }

       public void setmDefinefDuration(int definefDuration){
             this.mDefinefDuration=definefDuration;
       }

}
