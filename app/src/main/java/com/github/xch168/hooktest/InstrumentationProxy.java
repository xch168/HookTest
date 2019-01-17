package com.github.xch168.hooktest;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Method;

/**
 * Created by XuCanHui on 2019/1/16.
 */
public class InstrumentationProxy extends Instrumentation {
    private static final String TAG = "InstrumentationProxy";

    private Instrumentation mInstrumentation;

    public InstrumentationProxy(Instrumentation instrumentation) {
        mInstrumentation = instrumentation;
    }

    public ActivityResult execStartActivity(Context who, IBinder contextThread, IBinder token, Activity target,
            Intent intent, int requestCode, Bundle options) {

        Log.i(TAG, "Hook成功" + "--who:" + who);
        try {
            Method execStartActivityMethod = Instrumentation.class.getDeclaredMethod("execStartActivity",
                    Context.class, IBinder.class, IBinder.class, Activity.class,
                    Intent.class, int.class, Bundle.class);
            return (ActivityResult) execStartActivityMethod.invoke(mInstrumentation, who, contextThread, token, target,
                    intent, requestCode, options);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
