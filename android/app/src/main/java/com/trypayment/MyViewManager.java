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

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.paymentez.android.Paymentez;
import com.paymentez.android.model.Card;
import com.paymentez.android.rest.TokenCallback;
import com.paymentez.android.view.CardMultilineWidget;
import java.util.Map;

public class MyViewManager extends SimpleViewManager<LinearLayout> {
    Button btn;
    public static final String REACT_NAME="MyViewManager";
//    public int COMMAND_CREATE=1;
    ReactApplicationContext reactContext;
    private ReactApplicationContext context;
    public MyViewManager(ReactApplicationContext reactContext) {
        this.reactContext = reactContext;

    }

    @NonNull
    @Override
    public String getName() {
        return REACT_NAME;
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
        linearLayout.getChildAt(0);
        btn=linearLayout.findViewById(R.id.btn);
        CardMultilineWidget card= (CardMultilineWidget) linearLayout.getChildAt(0);
        Card cardToSave = card.getCard();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cardToSave == null) {
                    Log.d("test" , " if");

                    //return linearLayout; //If your customview has more constructor parameters pass it from here.
                }else{
                    Log.d("test" , card+" else");
                }
            }
        });

        return linearLayout;
    }

}
