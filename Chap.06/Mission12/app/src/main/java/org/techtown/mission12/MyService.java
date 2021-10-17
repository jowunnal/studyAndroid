package org.techtown.mission12;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class MyService extends Service {
    public MyService() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Intent intent1=new Intent(getApplicationContext(),MyReceiver.class);
        String txt=intent.getStringExtra("key");
        intent1.putExtra("key",txt);
        sendBroadcast(intent1);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}