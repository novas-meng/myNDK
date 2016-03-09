package com.example.novas.myndk;

/**
 * Created by novas on 2016/1/24.
 */
public class NDK {
    static
    {
        System.loadLibrary("NDK");
    }
    public native String getMessage();
    public native int getSum(int[] array);
    public native int printObject(Shape shape);
    public native void changeM(Shape shape);
}
