package cn.connie.algorithm.jna;

import com.sun.javafx.PlatformUtil;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

public class JNATest {

    public static void main(String[] args) {
        String resourceName = PlatformUtil.isLinux() ? "test.so" : "libtest01.dylib";
        String libPath = JNATest.class.getClassLoader().getResource(resourceName).getPath();
        JNAInterface INSTANCE = (JNAInterface) Native.loadLibrary(libPath, JNAInterface.class);
        Pointer pointer = INSTANCE.MyClass_ctor(10);
        int value = INSTANCE.MyClass_getValue(pointer);
        System.out.println("value = " + value);

    }
}
