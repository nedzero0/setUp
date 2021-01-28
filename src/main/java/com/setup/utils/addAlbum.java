package com.setup.utils;

import java.io.File;

public  class addAlbum {

    public static final String a = "D:\\setUp";

    //创建用户的时候创建一个用户的相册路径 和 一个 默认相册
    public static String addOriginal(String phone){
        String str = a + File.separator+phone +File.separator +"default";
        File file = new File(str);
        if (!file.exists()){
            file.mkdirs();
        }
        System.out.println(file);
        return null;
    }


}
