package cn.connie.algorithm.jna;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface JNAInterface extends Library {
    Pointer MyClass_ctor(int nb);

    int MyClass_getValue(Pointer self);

    void MyClass_increment(Pointer self);


}
