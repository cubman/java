package com.sevice.inter;

import com.service.annotation.Cache;

import java.io.Serializable;
import java.util.List;

import static com.service.annotation.CacheType.FILE;
import static com.service.annotation.CacheType.IN_MEMORY;

public interface IService extends Serializable {
    @Cache(cacheType = FILE, fileNamePrefix = "data", identityBy = {String.class, double.class})
    List<String> run(String item, double value);

    @Cache(cacheType = IN_MEMORY, listList = 100_000)
    List<String> work(String item);

}
