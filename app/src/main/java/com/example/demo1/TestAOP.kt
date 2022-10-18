package com.example.demo1

import android.util.Log
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect

/**
 * Created by LanceWu on 2022/9/6
 *
 * 函数切面
 */
@Aspect
class TestAOP {

    @Around("call(* com.lancewu.aspectj.testlibrary.TestLibrary.printLog(..))")
    fun around_TestLibrary_printLog(joinPoint: ProceedingJoinPoint): Any? {
        Log.d("LibraryMethodAspect", "before printLog")
        return aroundOldResult(joinPoint)
    }

    @Around("call(* android.widget.TextView.setText(..))")
    fun around_TestLibrary_setText(joinPoint: ProceedingJoinPoint): Any? {
        Log.d("around_TestLibrary_setText", "before printLog")
        return aroundOldResult(joinPoint)
    }

    private fun aroundOldResult(joinPoint: ProceedingJoinPoint): Any? {
        try {
            val args = joinPoint.args
            args[0] = args[0].toString() + ",arround"

            return joinPoint.proceed(args)
        } catch (throwable: Throwable) {
            throwable.printStackTrace()
        }
        return null
    }
}