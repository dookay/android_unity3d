package com.example.admin.threedemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;


public class BaseApplication extends MultiDexApplication {
    /**
     * 全局Context，原理是因为Application类是应用最先运行的，所以在我们的代码调用时，该值已经被赋值过了
     */
    private static BaseApplication mInstance;

    private static Context context;


    public static String VERSION, PACKAGENAME;
    public static int    VERSION_CODE = 1;
    public static String read_config  = "read_config";
    public static String user_config  = "user_config";

    public static int StatusBarLength = 0;


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        context = this;
        mInstance = this;

        PackageManager manager = getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(getPackageName(), 0);
            VERSION = info.versionName;
            VERSION_CODE = info.versionCode;
            PACKAGENAME = info.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


//    /**
//     * 分割 Dex 支持
//     * @param base
//     */
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }


    public static Context getContext() {
        return context;
    }


    public static BaseApplication getApplication() {
        return mInstance;
    }



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

}


