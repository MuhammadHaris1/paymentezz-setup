package com.trypayment;

import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.facebook.react.ReactNativeHost;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import com.paymentez.android.Paymentez;
import com.paymentez.android.model.Card;
import com.paymentez.android.rest.TokenCallback;
import com.paymentez.android.rest.model.PaymentezError;
import com.paymentez.android.view.CardMultilineWidget;
import java.util.Map;
import java.util.function.Function;

public class MyViewManager extends SimpleViewManager<LinearLayout> {
    Button btn;
    public static final String REACT_NAME="MyViewManager";
    public static ReactNativeHost mReactNativeHost;
//    public int COMMAND_CREATE=1;
    ReactApplicationContext reactContext;
    private String user_id="1";
    private String user_email="user@gmail.com";
    private ReactApplicationContext context;
    public MyViewManager(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;
        user_id=PaymentezCustomModule.user_id;
        user_email=PaymentezCustomModule.email;
        //Log.d("test",user_id+"");
    }

    @NonNull
    @Override
    public String getName() {
        return REACT_NAME;
    }

    public void callback(String user_id,String user_email){
        WritableMap payload = Arguments.createMap();
        // Put data to map
        payload.putString("MyCustomEventUser", user_id);
        payload.putString("MyCustomEventEmail", user_email);

        // Emitting event from java code
        (mReactNativeHost.getReactInstanceManager().getCurrentReactContext()).getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit("MyCustomEvent", payload);
    }




    public LinearLayout createViewInstance(ThemedReactContext context) {
        LayoutInflater inflater =(LayoutInflater) reactContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.fragment_face_capture,null);
//        CardMultilineWidget card=linearLayout.findViewById(R.id.card_multiline_widget);
//        linearLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context,"Hey I am clicked",Toast.LENGTH_LONG).show();
//            }
//        });
       // Paymentez.setEnvironment(true, Constan,"");
       // Log.d("childCount",""+linearLayout.getChildCount());
        Paymentez.setEnvironment(true, "IOS-CO-CLIENT", "AKKqsezFDHtanNv1G0ARyxb8DiYARE");
        linearLayout.getChildAt(0);
        btn=linearLayout.findViewById(R.id.btn);
        CardMultilineWidget card= (CardMultilineWidget) linearLayout.getChildAt(0);
        Card cardToSave = card.getCard();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card cardToSave = card.getCard();
                if (cardToSave == null) {
                    Log.d("test" , " if");
                    //return linearLayout; //If your customview has more constructor parameters pass it from here.
                }else{

//                    user_id=PaymentezCustomModule.name;
//                    user_email=PaymentezCustomModule.location;

                    user_id=PaymentezCustomModule.user_id;
                    user_email=PaymentezCustomModule.email;

                    Log.d("test",user_id+"");
                    Paymentez.addCard(reactContext,user_id ,user_email, cardToSave, new TokenCallback() {
                        @Override
                        public void onError(PaymentezError error) {
                            Log.d("test" , error+"");
                        }

                        @Override
                        public void onSuccess(Card card) {
//                            WritableMap payload = Arguments.createMap();
//                            // Put data to map
//                            payload.putString("MyCustomEventParam", "12345");
//
//                            MainActivity mainClass = new MainActivity();
//                            mainClass.emitter(payload);

                            // Emitting event from java code
                            Log.d("test" , card.getToken()+"");
                            Log.d("test" , card.getLast4()+"");
                            Log.d("test" , card.getType()+"");
                            Log.d("test" , Paymentez.getSessionId(reactContext)+"");
                            callback(card.getLast4(),card.getType());
                        }
                    });

                }
            }
        });

        return linearLayout;
    }

}
