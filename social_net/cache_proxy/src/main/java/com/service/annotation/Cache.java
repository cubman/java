package com.service.annotation;

import java.util.List;

public @interface Cache {
    public CacheType cacheType();
    public String fileNamePrefix() default "data";
    public Class<?>[] identityBy() default {};

    public int listList() default 0;
}
