package uk.co.ribot.androidboilerplate.util;

import android.app.Application;
import android.app.KeyguardManager;
import android.content.Context;
import android.os.PowerManager;
import android.support.test.runner.AndroidJUnitRunner;

public class UnlockDeviceTestRunner extends AndroidJUnitRunner {

    @Override
    public void onStart() {
        runOnMainSync(new Runnable() {
            @Override
            public void run() {
                Application app = (Application) getTargetContext().getApplicationContext();
                String simpleName = UnlockDeviceTestRunner.class.getSimpleName();

                // Unlock the device so that the tests can input keystrokes.
                ((KeyguardManager) app.getSystemService(Context.KEYGUARD_SERVICE)) //
                        .newKeyguardLock(simpleName) //
                        .disableKeyguard();
                // Wake up the screen.
                ((PowerManager) app.getSystemService(Context.POWER_SERVICE))
                        .newWakeLock(PowerManager.FULL_WAKE_LOCK |
                                PowerManager.ACQUIRE_CAUSES_WAKEUP |
                                PowerManager.ON_AFTER_RELEASE,
                                simpleName)
                        .acquire();

            }
        });
        super.onStart();
    }
}
