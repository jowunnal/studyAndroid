package org.techtown.mission11;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {
    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("TAG","Service 클래스의 onCreate() 호출됨");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("TAG","Service 클래스의 onStartCommand() 호출됨");
        Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
        String txt=intent.getStringExtra("key");
        intent1.putExtra("key",txt);
        startActivity(intent1);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}