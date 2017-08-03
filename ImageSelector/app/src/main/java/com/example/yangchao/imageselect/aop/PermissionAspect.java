package com.example.yangchao.imageselect.aop;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import com.example.yangchao.imageselect.App;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

/**
 * Created by yang2 on 2017/8/3.
 */
@Aspect
public class PermissionAspect {

    @Around("execution(@com.example.yangchao.imageselect.aop.Permission * *(..)) && @annotation(permission)")
    public void aroundJoinPoint(ProceedingJoinPoint joinPoint, Permission permission) throws Throwable {
        AppCompatActivity ac = (AppCompatActivity) App.getAppContext().getCurActivity();
        new AlertDialog.Builder(ac)
                .setTitle("提示")
                .setMessage("为了应用可以正常使用，请您点击确认申请权限。")
                .setNegativeButton("取消", null)
                .setPositiveButton("允许", ( dialog, which) ->
                        PermissionUtil.requestPermissionsResult(ac, 1, permission.value()
                                , new PermissionUtil.OnPermissionListener() {
                                    @Override
                                    public void onPermissionGranted() {
                                        try {
                                            joinPoint.proceed();//获得权限，执行原方法
                                        } catch (Throwable e) {
                                            e.printStackTrace();
                                        }
                                    }

                                    @Override
                                    public void onPermissionDenied() {
                                        PermissionUtil.showTipsDialog(ac);
                                    }
                                })
                ).create()
                .show();

    }
}
