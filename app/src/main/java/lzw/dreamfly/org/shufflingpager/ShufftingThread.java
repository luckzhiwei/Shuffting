package lzw.dreamfly.org.shufflingpager;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by asus on 2015/11/12.
 */
public class ShufftingThread extends Thread {

       private Handler mHandler;
       public ShufftingThread(Handler mHandler){
           this.mHandler=mHandler;
       }

       public void run(){
           for(int index=90;index>0;index--){
               try {
                   Message msg = new Message();
                   msg.what = index;
                   msg.arg1 = index;
                   this.sleep(3000);
                   this.mHandler.sendMessage(msg);
               }catch(InterruptedException e){
                   e.printStackTrace();
               }
           }
       }
}
