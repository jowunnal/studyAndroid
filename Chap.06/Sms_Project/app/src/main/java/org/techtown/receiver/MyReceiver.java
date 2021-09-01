package org.techtown.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyReceiver extends BroadcastReceiver {
public static final String TAG="SMSRECEIVER";
public SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i(TAG,"onReceived() 호출됨");

        Bundle bundle=intent.getExtras();
        SmsMessage[] smsMessages=parseSmsMessage(bundle);
        if((smsMessages!=null)&&smsMessages.length>0){
            String sender=smsMessages[0].getOriginatingAddress();
            Log.i(TAG,"SMS sender:"+sender);

            String content=smsMessages[0].getDisplayMessageBody();
            Log.i(TAG,"SMS content:"+content);

            Date date=new Date(smsMessages[0].getTimestampMillis());
            Log.i(TAG,"SMS received Date:"+date.toString());

            sendToActivity(context,sender,content,date);
        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle){
        Object[] objects=(Object[])bundle.get("pdus");
        SmsMessage[] smsMessages=new SmsMessage[objects.length];

        int smsCount=objects.length;
        for(int i=0;i<smsCount;i++){
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){
                String format=bundle.getString("format");
                smsMessages[i]=SmsMessage.createFromPdu((byte[])objects[i],format);
            }
            else{
                smsMessages[i]=SmsMessage.createFromPdu((byte[])objects[i]);
            }
        }
        return smsMessages;
    }

    private void sendToActivity(Context context, String sender,String content,Date date){
        Intent intent=new Intent(context,MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

        intent.putExtra("sender",sender);
        intent.putExtra("content",content);
        intent.putExtra("date",format.format(date));

        context.startActivity(intent);
    }
}