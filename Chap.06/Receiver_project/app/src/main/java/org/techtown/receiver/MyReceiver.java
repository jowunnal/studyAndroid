package org.techtown.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
public static final String TAG="SMSRECEIVER";
    @Override
    public void onReceive(Context context, Intent intent) {
       Log.i(TAG,"onReceived() 호출");

       Bundle bundle=intent.getExtras();
       SmsMessage[] messages=parseSmsMessage(bundle);

       if((messages!=null)&&messages.length>0){
           String sender=messages[0].getOriginatingAddress();
           Log.i(TAG,"SMS sender: "+sender);

           String contents=messages[0].getMessageBody();
           Log.i(TAG,"SMS contents: "+contents);

           Date receivedDate=new Date(messages[0].getTimestampMillis());
           Log.i(TAG,"SMS received Date: "+receivedDate.toString());
       }
    }
    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objs=(Object[]) bundle.get("pdus");
        SmsMessage[] messages=new SmsMessage[objs.length];

        int smsCount=objs.length;
        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                String format=bundle.getString("format");
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i],format);
            }else{
                messages[i]=SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;
    }
}