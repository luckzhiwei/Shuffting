package lzw.dreamfly.org.shufflingpager.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;
import java.util.List;
import java.util.jar.Attributes;

/**
 * Created by asus on 2015/11/12.
 */
public class MyDefineViewPager extends ViewPager  {

        private MyDefineScroller myDefineScroller;

        public static final int LEFT_SCROLL=1;
        //向左滑动
        public static final int RIGHT_SCROLL=2;
        //向右滑动
        public static final int DEAFAULT_TIME=2000;
        //默认滑动的时间
        public static final int START_SCROLL=3;

        public static final int PAUSE_SCROLL=4;

        private int currentPageIndex;
        //当前页面的索引值
        private int currentItemCount;

        private int scrollIndex;
        //滑动时候的索引的数值

        public MyDefineViewPager(Context context){
            super(context);
            init(context);
        }

        public MyDefineViewPager(Context context,AttributeSet attrs){
            super(context,attrs);
            init(context);
        }

        private void init(Context context){
            this.myDefineScroller=new MyDefineScroller(context);
            try {
                Field scrollFiled = ViewPager.class.getDeclaredField("mScroller");
                scrollFiled.setAccessible(true);
                scrollFiled.set(this,myDefineScroller);
            }catch(NoSuchFieldException e){
                e.printStackTrace();
            }catch(IllegalAccessException e){
                e.printStackTrace();
            }
            this.currentItemCount=-1;
            this.scrollIndex=0;
        }

        /**
         * 设置每次动画的时间
         * @param scrollTime
         */
        public void setScrollTime(int scrollTime){
              this.myDefineScroller.setmDefinefDuration(scrollTime);
        }
        public void startAutoScroll(int direction){
              Message msg=Message.obtain(mHandler);
              msg.arg1=START_SCROLL;
              msg.arg2=direction;
              this.mHandler.sendMessageDelayed(msg,3000);
        }

        public void stopAutoScroll(){
              Message msg=Message.obtain(mHandler);
              msg.arg1=PAUSE_SCROLL;
              this.mHandler.sendMessage(msg);
        }



        private Handler mHandler=new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg) {
                if (msg.arg1 == START_SCROLL) {
                     if(msg.arg2==LEFT_SCROLL){
                          scrollLeft();
                          startAutoScroll(LEFT_SCROLL);
                     }else if(msg.arg2==RIGHT_SCROLL){
                          scrollRight();
                          startAutoScroll(RIGHT_SCROLL);
                     }
                } else if (msg.arg1 == PAUSE_SCROLL) {

                }
            }
        };




        /**
         * 向左滑动
         */
        private void scrollLeft(){
              int total=ifIitemCountNouCal();
              this.scrollIndex=(scrollIndex-1)%total;
              if(this.scrollIndex==-1){
                   this.scrollIndex=total-1;
                   this.setCurrentItem(scrollIndex,false);
              }else{
                  this.setCurrentItem(scrollIndex,true);
              }

        }

        /**
         * 向右滑动
         */
        private void scrollRight(){
             int total=this.ifIitemCountNouCal();
             this.scrollIndex=(scrollIndex+1)%total;
             if(this.scrollIndex==0){
                   this.setCurrentItem(scrollIndex,false);
             }else{
                   this.setCurrentItem(scrollIndex,true);
             }
        }

        private int ifIitemCountNouCal(){
              if(this.currentItemCount==-1){
                  PagerAdapter mPagerApdater=this.getAdapter();
                  if(mPagerApdater!=null){
                      this.currentItemCount=mPagerApdater.getCount();
                  }
              }
              return(this.currentItemCount);
        }

        /**
         * 获取当前页面所在索引值
         * @return
         */
        public int getCurrentPageIndex(){
              return(this.currentPageIndex);
        }




}
