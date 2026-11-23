package org.shano.assistral;
import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.webkit.JavascriptInterface;
import java.util.Locale;

public class VoiceBridge {
    private TextToSpeech tts;
    private boolean ready = false;

    public VoiceBridge(Context ctx) {
        tts = new TextToSpeech(ctx, status -> {
            if (status == TextToSpeech.SUCCESS) {
                int r = tts.setLanguage(Locale.forLanguageTag("es-MX"));
                if (r == TextToSpeech.LANG_MISSING_DATA || r == TextToSpeech.LANG_NOT_SUPPORTED)
                    tts.setLanguage(Locale.forLanguageTag("es-ES"));
                ready = true;
            }
        });
    }

    @JavascriptInterface
    public void speak(String text) {
        if (!ready || text == null) return;
        String clean = text.replace("☥","").replace("«","").replace("»","").trim();
        tts.speak(clean, TextToSpeech.QUEUE_FLUSH, null, "anubis");
    }

    @JavascriptInterface
    public void setRate(float rate) { if (ready) tts.setSpeechRate(rate); }

    @JavascriptInterface
    public void setPitch(float pitch) { if (ready) tts.setPitch(pitch); }

    @JavascriptInterface
    public void stop() { if (ready) tts.stop(); }
}
