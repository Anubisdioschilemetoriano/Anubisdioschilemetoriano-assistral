package org.shano.assistral;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import java.util.ArrayList;

public class MicBridge {
    private Activity act; private WebView wv;
    public MicBridge(Context c, WebView wv){ this.act=(Activity)c; this.wv=wv; }
    @JavascriptInterface
    public void listen(){
        Intent i=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        i.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"es-MX");
        i.putExtra(RecognizerIntent.EXTRA_PROMPT,"☥ Habla, Arquitecto");
        try{ act.startActivityForResult(i,1337); }catch(Exception e){}
    }
    public void onResult(ArrayList<String> res){
        String t=(res!=null&&!res.isEmpty())?res.get(0):"";
        wv.post(()->wv.evaluateJavascript("window.__onMic("+Json(t)+")",null));
    }
    private String Json(String s){return "\""+s.replace("\\","\\\\").replace("\"","\\\"").replace("\n"," ")+"\"";}
}
