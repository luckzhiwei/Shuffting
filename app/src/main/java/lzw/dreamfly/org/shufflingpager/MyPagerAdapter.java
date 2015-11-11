package lzw.dreamfly.org.shufflingpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by asus on 2015/11/11.
 */
public class MyPagerAdapter extends PagerAdapter {


    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        return null;
    }

    @Override
    public int getCount() {
        return  0;
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;//官方提示这样写
    }
}
