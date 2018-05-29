package com.task7.loader;

public class AppClassloader extends ApiClassloader {
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        String[] names = name.split("\\.");

        if(names.length > 1 && names[names.length - 2].equals("app"))
            return super.loadClass(name);

        throw new ClassNotFoundException("пакет не app: " + name);
    }
}
