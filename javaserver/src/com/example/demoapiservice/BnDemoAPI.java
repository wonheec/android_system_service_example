package com.example.demoapiservice;

import android.binder.example.IDemoAPI;
import android.os.RemoteException;
import android.util.Log;

public class BnDemoAPI extends IDemoAPI.Stub {
    private static final String TAG = "binder_demo_java_server";
    
    @Override
    public String getName() throws RemoteException {
        return "DemoAPI";
    }
    
    @Override
    public String getFullName(String part) throws RemoteException {
        StringBuilder builder = new StringBuilder("DemoAPI+");
        builder.append(part);
        return builder.toString();
    }
    
    @Override
    public int sum(int a, int b) throws RemoteException {
        Log.d(TAG, "Java server is summing " + Integer.toString(a) + " and " + Integer.toString(b));
        return a + b;
    }
    
}
