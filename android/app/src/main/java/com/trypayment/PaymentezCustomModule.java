package com.trypayment;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactMethod;
import java.util.Map;
import java.util.HashMap;


public class PaymentezCustomModule extends ReactContextBaseJavaModule {
    PaymentezCustomModule(ReactApplicationContext context) {
        super((context));
    }

    @NonNull
    @Override
    public String getName() {
        return "PaymentezCustomModule";
    }

    static String user_id,email;

    @ReactMethod
    public void passValue(String user_id, String email, Callback promise) {
        //Log.d("CalendarModule", "Create event called with name: " + name + " and location: " + location);
        this.user_id=user_id;
        this.email=email;
        promise.invoke(user_id + email);
    }

}
