package org.android10.gintonic.aspect;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by liuxiaofeng02 on 2017/4/9.
 */
@Aspect
public class OnClickEventAspect {
    private static final String TAG = OnClickEventAspect.class.getSimpleName();
    //withincode(* android.view.View.OnClickListener.onClick(android.view.View))
    private static final String POINTCUT_METHOD =
            "execution(public void android.view.View.OnClickListener.onClick(android.view.View))";

    @Pointcut(POINTCUT_METHOD)
    public void traceOnClickEvent() {}

    @Before("traceOnClickEvent()")
    public void traceBefore(JoinPoint joinPoint){
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        Log.d(TAG, className + "-" + methodName);

        Object[] args = joinPoint.getArgs();
        View view = (View)args[0];
        Log.d(TAG, view.getContext().getClass().getSimpleName());
        Log.d(TAG, view.getClass().getSimpleName() + "->" + view.getResources().getResourceEntryName(view.getId()));
    }

}
