package com.github.xch168.hooktest;

import android.app.Activity;
import android.app.Instrumentation;

import java.lang.reflect.Field;

/**
 * Created by XuCanHui on 2019/1/16.
 */
public class HookHelper {

    public static void hookActivityInstrumentation(Activity activity) {
        try {
            Field field = Activity.class.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) field.get(activity);
            InstrumentationProxy instrumentationProxy = new InstrumentationProxy(instrumentation);
            field.set(activity, instrumentationProxy);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void hookContextInstrumentation() {
        try {
            Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
            Field currentActivityThreadField = activityThreadClass.getDeclaredField("sCurrentActivityThread");
            currentActivityThreadField.setAccessible(true);
            Object currentActivityThread = currentActivityThreadField.get(null);
            Field mInstrumentationField = activityThreadClass.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) mInstrumentationField.get(currentActivityThread);
            InstrumentationProxy instrumentationProxy = new InstrumentationProxy(instrumentation);
            mInstrumentationField.set(currentActivityThread, instrumentationProxy);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }
}
