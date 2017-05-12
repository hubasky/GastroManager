package hu.hubasky.gastromanager.control;

import android.os.Handler;
import android.os.Looper;

/**
 * Az aszinkron működésű vezérlők alaposztálya.
 * Created by mirso on 2017. 05. 12..
 */

public class AsyncControlBase {
    /**
     * Az UI callback-hez kell!
     */
    private Handler handler;

    /**
     * Visszaadja a kezelőt a UI szálhoz.
     *
     * @return a kezelő.
     */
    private Handler getHandler() {
        synchronized (this) {
            if (handler == null) {
                handler = new Handler(Looper.getMainLooper());
            }
        }
        return handler;
    }

    /**
     * A UI szálból meghívja a paraméterből átadott Runnable interfést.
     *
     * @param runnable az interfész.
     */
    public void callbackUI(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        getHandler().post(runnable);
    }
}
