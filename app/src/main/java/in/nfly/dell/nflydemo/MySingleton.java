package in.nfly.dell.nflydemo;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mInstance;
    private RequestQueue requestQueue;
    private Context mCtx;

    private MySingleton(Context context){
        mCtx=context;
        requestQueue=getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MySingleton getmInstance(Context context){
        if(mInstance==null){
            mInstance=new MySingleton(context);
        }
        return mInstance;
    }
    public void addToRequestQueue(Request request){
        requestQueue.add(request);
    }

    public static void release(){
        if(mInstance!=null){
            mInstance.releaseContext();
            mInstance=null;
        }
    }
    private void releaseContext(){
        mCtx=null;
    }
}
