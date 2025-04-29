package org.example.classes;

import org.junit.jupiter.api.Test;

public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader cl = ClassLoaderTest.class.getClassLoader();
        System.out.println("cl is "+cl);

        ClassLoader parent = cl.getParent();
        System.out.println("parent is "+parent);

        ClassLoader boot_strap = parent.getParent();
        System.out.println("boot_strap is "+boot_strap);

    }

    @Test
    public void transferTest(){
         Transfer a = new Transfer<String>();
         a.show("1");
    }
}
