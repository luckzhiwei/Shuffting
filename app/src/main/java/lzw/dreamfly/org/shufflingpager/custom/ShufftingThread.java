package lzw.dreamfly.org.shufflingpager.custom;

import android.os.Handler;
import android.os.Message;

/**
 * Created by asus on 2015/11/13.
 */
public class ShufftingThread extends  Thread {

        private Handler mHandler;
        private int  myShufftingTime=2000;
        private int  mDirection;


        public ShufftingThread(Handler mHandler){
              this.mHandler=mHandler;
        }


        public void setmDirection(int mDirection){
             this.mDirection=mDirection;
        }

        public void run(){
             while(true){
                 try {

                     Message msg=Message.obtain(mHandler);
                     msg.arg2=this.mDirection;
                     this.sleep(myShufftingTime);
                     this.mHandler.sendMessage(msg);
                 }catch(InterruptedException e){
                     e.printStackTrace();
                 }
             }
        }
}
