package com.task7.loader;

public class ImplClassLoader extends ApiClassloader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String[] names = name.split("\\.");

        if(names.length > 1 && names[names.length - 2].equals("impl"))
            return super.loadClass(name);

        throw new ClassNotFoundException("пакет не impl: " + name);
    }
}
