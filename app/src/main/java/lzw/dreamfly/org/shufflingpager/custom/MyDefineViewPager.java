package lzw.dreamfly.org.shufflingpager.custom;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
        public static final int DEAFAULT_TIME=1500;
        //默认滑动的动画的时间
        public static final  int DEFAULT_SHUFFING_ITME=4000;
        //默认轮播的时间间隔
        private int currentItemCount;
       //总共有多少页


        private ShufftingThread mShufftingThread;

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
            this.myDefineScroller.setmDefinefDuration(DEAFAULT_TIME);
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
        }

        /**
         * 设置每次滑动动画的时间
         * @param scrollTime
         */
        public void setScrollTime(int scrollTime){
              this.myDefineScroller.setmDefinefDuration(scrollTime);
        }

        public void startAutoScroll(int direction){
              startAutoScroll(direction,DEFAULT_SHUFFING_ITME);
        }

        public void startAutoScroll(int direction,int shufftingTime){
            if(this.mShufftingThread==null){
                this.mShufftingThread=new ShufftingThread(mHandler);
                this.mShufftingThread.setmDirection(direction);
                this.mShufftingThread.setMyShufftingTime(shufftingTime);
                this.mShufftingThread.start();
            }
        }


        private Handler mHandler=new Handler(Looper.getMainLooper()){
            public void handleMessage(Message msg) {
                    if (msg.arg2 == LEFT_SCROLL) {
                        scrollLeft();
                    } else if (msg.arg2 == RIGHT_SCROLL) {
                        scrollRight();
                    }
            }

        };

        /**
         * 向左滑动
         */
        private void scrollLeft(){
              int total=ifIitemCountNouCal();
              int currentIndex=this.getCurrentItem();
              currentIndex=(currentIndex-1)%total;
              if(currentIndex==-1){
                   currentIndex=total-1;
                   this.setCurrentItem(currentIndex,false);
              }else{
                  this.setCurrentItem(currentIndex,true);
              }
        }

        /**
         * 向右滑动
         */
        private void scrollRight(){
             int total=this.ifIitemCountNouCal();
             int currentIndex=this.getCurrentItem();
             currentIndex=(currentIndex+1)%total;
             if(currentIndex==0){
                   this.setCurrentItem(currentIndex,false);
             }else{
                   this.setCurrentItem(currentIndex,true);
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
     * 如果有按下和滑动的手势，则告暂停线程
     * @param event
     * @return
     */
        @Override
        public boolean onTouchEvent(MotionEvent event){
               switch (event.getAction()){
                   case MotionEvent.ACTION_DOWN:
                      if(!mShufftingThread.getIsSupsend()) {
                          mShufftingThread.setIsSupsend(true);
                      }
                       break;
                   case MotionEvent.ACTION_UP:
                       mShufftingThread.setIsSupsend(false);
                       break;
                   case MotionEvent.ACTION_MOVE:
                      mShufftingThread.setIsSupsend(true);
                      break;
               }
               return(super.onTouchEvent(event));
        }

        /**
         * 获取当前页面所在索引值
         * @return
         */
        public int getCurrentPageIndex(){
              return(this.getCurrentItem());
        }



}
