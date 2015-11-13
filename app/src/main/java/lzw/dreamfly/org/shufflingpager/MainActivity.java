package lzw.dreamfly.org.shufflingpager;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import lzw.dreamfly.org.shufflingpager.custom.MyDefineScroller;
import lzw.dreamfly.org.shufflingpager.custom.MyDefineViewPager;
import lzw.dreamfly.org.shufflingpager.custom.MyPagerAdapter;



public class MainActivity extends Activity {


    private MyDefineViewPager myDefineViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        this.myDefineViewPager=(MyDefineViewPager)this.findViewById(R.id.defviewpager_mainactivity_autoscroll);
        List<View> view=new ArrayList<>();
        view.add(LayoutInflater.from(this).inflate(R.layout.one_pager_layout,null));
        view.add(LayoutInflater.from(this).inflate(R.layout.one_pager_layout,null));
        view.add(LayoutInflater.from(this).inflate(R.layout.one_pager_layout,null));
        MyPagerAdapter myPagerAdapter=new MyPagerAdapter(view);
        myDefineViewPager.setAdapter(myPagerAdapter);
        myDefineViewPager.startAutoScroll(MyDefineViewPager.RIGHT_SCROLL);
        myDefineViewPager.setScrollTime(2000);

    }


}
