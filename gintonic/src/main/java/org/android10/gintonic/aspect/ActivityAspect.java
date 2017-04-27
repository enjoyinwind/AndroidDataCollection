package org.android10.gintonic.aspect;

import android.util.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by liuxiaofeng02 on 2017/4/9.
 */
@Aspect
public class ActivityAspect {
    private static final String TAG = ActivityAspect.class.getSimpleName();
    private static final String ON_CREATE_POINTCUT_METHOD =
            "execution(void *.onCreate(android.os.Bundle)) && this(android.app.Activity)";
    private static final String ON_DESTROY_POINTCUT_METHOD =
            "execution(void *.onDestroy()) && this(android.app.Activity)";

    @Pointcut(ON_CREATE_POINTCUT_METHOD)
    public void onCreateEvent() {}

    @Pointcut(ON_DESTROY_POINTCUT_METHOD)
    public void onDestroyEvent() {}

    @Before("onCreateEvent()")
    public void traceOnCreateBefore(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Log.d(TAG, className + "-" + methodName);
    }

    @Before("onDestroyEvent()")
    public void traceOnDestoryBefore(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Log.d(TAG, className + "-" + methodName);
    }
}
