package com.trypayment;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.facebook.react.ReactActivity;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;

public class MainActivity extends ReactActivity {

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
    super.onCreate(savedInstanceState, persistentState);
  }

//  public void emitter(WritableMap payload){
//    (getReactNativeHost().getReactInstanceManager().getCurrentReactContext()).getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
//            .emit("MyCustomEvent", payload);
//
//  }

  /**
   * Returns the name of the main component registered from JavaScript. This is used to schedule
   * rendering of the component.
   */


  @Override
  protected String getMainComponentName() {
    return "trypayment";
  }

}
