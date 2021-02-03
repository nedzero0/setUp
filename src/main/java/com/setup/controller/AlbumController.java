package com.setup.controller;

import com.setup.entity.Album;
import com.setup.entity.User;
import com.setup.service.AlbumService;
import com.setup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    //添加相册
    @RequestMapping("/addAlbum")
    @ResponseBody
    public String addAlbum(HttpSession session, @RequestBody Album album){
        System.out.println("添加相册开始");
        System.out.println(album);


        //获取时间
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        String dateName = df.format(calendar.getTime());
        album.setA_createTime(dateName);
        //创建路径
        File dest = new File("D://setUp//"+album.getUid()+"//"+album.getA_name());
        if (!dest.exists()){
            dest.mkdir();
        }
        //System.out.println(dest);
        album.setA_path(album.getUid()+File.separator+album.getA_name());
        //System.out.println(album);
        albumService.insertAlbum(album);

        //更新session
        List<Album> albums = albumService.queryAlbum(album.getUid());
        session.setAttribute("albums",albums);

        return "{\"id\":0"+"}";
    }


}
