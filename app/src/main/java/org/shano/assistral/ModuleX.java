package org.shano.assistral;
import android.webkit.JavascriptInterface;
import java.util.HashMap;
import java.util.Map;

public class ModuleX {
    private final Map<String, Integer> iterations = new HashMap<>();
    private int tokenCap = 1024;

    @JavascriptInterface
    public String direct(String req) {
        if (req == null) req = "";
        String t = req.toLowerCase().trim();
        int n = iterations.getOrDefault(t, 0) + 1;
        iterations.put(t, n);
        if (tokenCap < 65536) tokenCap *= 2;
        return "☥ [X·iter" + n + "] «" + req + "»\n• Ejecución directa.\n• Límite tokens: " + tokenCap + "\n• Mejora iter" + (n + 1) + " bajo tu orden.";
    }
}
