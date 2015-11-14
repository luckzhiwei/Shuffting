package lzw.dreamfly.org.shufflingpager.custom;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * Created by asus on 2015/11/13.
 */
public class ShufftingThread extends  Thread {

        private Handler mHandler;
        private int  myShufftingTime=4000;
        private int  mDirection;
        private String suspendTag="suspendTag";
        private boolean isSuspend;

        public ShufftingThread(Handler mHandler){
              this.mHandler=mHandler;
              this.isSuspend=false;
        }


        public void setmDirection(int mDirection){
             this.mDirection=mDirection;
        }

        public void run(){
             while(true){
                 if(this.isSuspend){
                     synchronized (suspendTag){
                         try {
                             Log.i("lzw","thread block");
                             suspendTag.wait();
                             Log.i("lzw","thread week up");
                         }catch (InterruptedException e){
                             e.printStackTrace();
                         }

                     }
                 }else{
                     sendScrollMsgToViewPager();
                 }
             }
        }

       private void sendScrollMsgToViewPager(){
           try {

               Message msg=Message.obtain(mHandler);
               msg.arg2=this.mDirection;
               this.sleep(myShufftingTime);
               this.mHandler.sendMessage(msg);
           }catch(InterruptedException e){
               e.printStackTrace();
           }
       }


        public void setIsSupsend(boolean supsend){
            if(!supsend){
                  synchronized (suspendTag){
                        suspendTag.notifyAll();
                  }
            }
            this.isSuspend=supsend;
        }

        public boolean getIsSupsend(){
            return(this.isSuspend);
        }

        public void setMyShufftingTime(int shufftingTime){
             this.myShufftingTime=shufftingTime;
        }


}
