package com.setup.utils;

import java.io.File;

public  class addAlbum {

    public static final String a = "D:\\setUp";

    //创建用户的时候创建一个用户的相册路径 和 一个 默认相册
    public static String addOriginal(String phone){
        String str = a + File.separator+phone +File.separator +"default";
        String str1 = a + File.separator+phone +File.separator +"head";
        File file = new File(str);
        File file1 = new File(str1);
        if (!file.exists()){
            file.mkdirs();
        }
        if (!file1.exists()){
            file1.mkdirs();
        }
        //System.out.println(file);
        return null;
    }


}
