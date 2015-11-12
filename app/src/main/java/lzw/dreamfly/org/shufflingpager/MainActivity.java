package lzw.dreamfly.org.shufflingpager;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Activity {

    private ViewPager mViewPager;
    private Button btn;
    private int mIndex;
    private int mViewCount;
    private ShufftingThread mShufftingThread;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

    }

    private void init(){
             mViewPager=(ViewPager)this.findViewById(R.id.viewpager_mainactivity_shuffling);
             List<View> view=new ArrayList<>();
             for(int i=0;i<3;i++) {
              view.add(LayoutInflater.from(this).inflate(R.layout.one_pager_layout, null));
             }
             this.mIndex=0;
             this.mViewCount=3;
             MyPagerAdapter pagerAdapter=new MyPagerAdapter(view);
             this.mViewPager.setAdapter(pagerAdapter);
             this.mShufftingThread=new ShufftingThread(mHandler);
             this.mShufftingThread.start();
             this.mViewPager.setCurrentItem(0,true);
    }


    private Handler mHandler=new Handler(Looper.getMainLooper()){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            mIndex=(mIndex+1)%mViewCount;
            mViewPager.setCurrentItem(mIndex,true);
        }
    };
}
