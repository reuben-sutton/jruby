package org.jruby.javasupport;

import java.lang.reflect.Method;
import org.junit.Test;

import org.jruby.Ruby;

class A {
    public static class C extends B {}
}

class B extends A {}

public class TestJava extends junit.framework.TestCase {

    @Test
    public void testProxyCreation() {
        final Ruby runtime = Ruby.newInstance();
        try {
            Java.getProxyClass(runtime, B.class);
            assert(true);
        }
        catch (AssertionError ae) {
            fail(ae.toString());
        }
    }

    @Test
    public void testGetFunctionInterface() {
        Method method;
        method = Java.getFunctionalInterfaceMethod(java.lang.Runnable.class);
        assertNotNull(method);
        assertEquals("run", method.getName());

        method = Java.getFunctionalInterfaceMethod(java.io.Serializable.class);
        assertNull(method);

        //if ( Java.JAVA8 ) { // compare and equals both abstract
        method = Java.getFunctionalInterfaceMethod(java.util.Comparator.class);
        assertNotNull(method);
        assertEquals("compare", method.getName());
        //}

        method = Java.getFunctionalInterfaceMethod(java.lang.Comparable.class);
        assertNotNull(method);
        assertEquals("compareTo", method.getName());

        method = Java.getFunctionalInterfaceMethod(java.lang.Iterable.class);
        assertNotNull(method);
        assertEquals("iterator", method.getName());

        method = Java.getFunctionalInterfaceMethod(java.util.concurrent.ThreadFactory.class);
        assertNotNull(method);

        method = Java.getFunctionalInterfaceMethod(java.util.Enumeration.class);
        assertNull(method);

        method = Java.getFunctionalInterfaceMethod(FxRunnable1.class);
        assertNotNull(method);
        assertEquals("run", method.getName());

        method = Java.getFunctionalInterfaceMethod(FxRunnable2.class);
        assertNotNull(method);
        assertEquals("run", method.getName());

        method = Java.getFunctionalInterfaceMethod(NonFxRunnable.class);
        assertNull(method);
    }

    private static interface FxRunnable1 extends Runnable { abstract void run() ; }

    private static interface FxRunnable2 extends Runnable { /* inherited run() */ }

    private static interface NonFxRunnable extends Runnable { public void doRun() ; }

}
