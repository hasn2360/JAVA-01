import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) {
        try {
            MyClassLoader myClassLoader = new MyClassLoader();
            Class<?> clazz = myClassLoader.findClass("Hello");
            Method method = clazz.getDeclaredMethod("hello", null);
            method.invoke(clazz.newInstance(), null);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            InputStream inputStream = new FileInputStream(this.getClass().getResource(name + ".xlass").getPath());
            int n = 0;
            while ((n = inputStream.read()) != -1) {
                out.write(n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] bytes = out.toByteArray();
        byte[] resultBytes = new byte[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            resultBytes[i] = (byte) (255 - bytes[i]);
        }
        return super.defineClass(name, resultBytes, 0, resultBytes.length);
    }
}
