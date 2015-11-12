package lzw.dreamfly.org.shufflingpager;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.List;

/**
 * Created by asus on 2015/11/11.
 */
public class MyPagerAdapter extends PagerAdapter {

    private List<View> mListViews;
    private int mViewCount;

    public MyPagerAdapter(List<View> mListViews){
          this.mListViews=mListViews;
          this.mViewCount=this.mListViews.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)   {
          container.removeView(this.mListViews.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {  //这个方法用来实例化页卡
        View pager=this.mListViews.get(position);
        LinearLayout picLayout=(LinearLayout)pager.findViewById(R.id.page_layout);
        if(position==0){
              picLayout.setBackgroundResource(R.drawable.pic_one);
        }else if(position==1){
              picLayout.setBackgroundResource(R.drawable.pic_two);
        }else if(position==2) {
            picLayout.setBackgroundResource(R.drawable.pic_three);
        }
        container.addView(pager);
        return (mListViews.get(position));
    }

    @Override
    public int getCount() {
        return  (this.mViewCount);
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0==arg1;//官方提示这样写
    }
}
