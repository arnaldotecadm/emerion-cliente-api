package br.com.arcasoftware.comercialapi.utils;

import java.util.Collection;

public class ObjectUtils {

    private ObjectUtils(){
        /*
        just to hide the private one
         */
    }

    public static boolean isCollectionValid(Collection<?> c) {
        return (null != c && !c.isEmpty());
    }
}
