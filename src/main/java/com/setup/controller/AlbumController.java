package com.setup.controller;

import com.setup.entity.Album;
import com.setup.entity.Image;
import com.setup.entity.User;
import com.setup.service.AlbumService;
import com.setup.service.ImageService;
import com.setup.service.UserService;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ImageService imageService;
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
        album.setA_path(album.getUid()+"//"+album.getA_name());
        //System.out.println(album);
        albumService.insertAlbum(album);

        //更新session
        List<Album> albums = albumService.queryAlbum(album.getUid());
        session.setAttribute("albums",albums);

        return "{\"id\":0"+"}";
    }

    //更新相册信息
    @RequestMapping("/updateAlbum")
    @ResponseBody
    public String updateAlbum(HttpSession session, @RequestBody Album album){
        System.out.println("修改中");
        System.out.println(album);
        //更新相册的物理路径名称
        int i = albumService.updateAlbum(album);

        //更新session
        List<Album> albums = albumService.queryAlbum(album.getUid());
        session.setAttribute("albums",albums);

        return "{\"id\":0"+"}";
    }


    //查看相册下的图片
    @RequestMapping("/queryAlbum")
    public String queryAlbum(HttpSession session,HttpServletRequest request){
        int aid = Integer.parseInt(request.getParameter("aid"));
        int index = Integer.parseInt(request.getParameter("index"));
        System.out.println(aid+" "+index);
        //System.out.println(aid);
        List<Image> images = imageService.queryImageByAid(aid);
        //System.out.println(images.size()+"\n"+images);
        session.setAttribute("images",images);

        session.setAttribute("index",index);

        return "redirect:/own/album.html";
    }

    //上传图片到相册
    @RequestMapping("/addImages")
    @ResponseBody
    public String addImages(MultipartFile file, HttpSession session) {
        System.out.println("开始添加图片");
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        //String prefix = fileName.substring(fileName.lastIndexOf("."));
        //获得相册信息
        //获得该相册的index下标
        int index = (int)session.getAttribute("index");
        List<Album> albums= (List<Album>)session.getAttribute("albums");
        Album album = albums.get(index);
       // System.out.println(album);
        try {
            //创建临时file1,用来获取File的信息
           /* File file1 = File.createTempFile(fileName, prefix);
            file.transferTo(file1);*/

            //设置上传路径
            String filePath ="setUp//" +album.getA_path()+"//"+fileName;
            System.out.println("filePath的路径："+filePath);
            File dest = new File("D://"+filePath);
            //存储图片操作
            Image image=new Image();
            //获取当前时间
            Date date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String time = sdf.format(date);

            //设置image参数
            image.setAid(album.getAid());
            image.setI_name(fileName);
            image.setI_size((int) file.getSize()/1024);
            image.setI_uploadTime(time);
            image.setI_path(filePath);
            System.out.println("该图片信息："+image);
            System.out.println(file.getSize()+" "+file.getBytes());
            if (!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                //5.存储新的头像到文件夹中
                file.transferTo(dest);
                //6.存储image信息到数据库中
                imageService.insertImage(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //7.更新session中的images
            List<Image> images = imageService.queryImageByAid(album.getAid());
            session.setAttribute("images",images);

            //删除临时文件file1
            //file1.deleteOnExit();

        } catch (Exception e) {
            e.printStackTrace();
        }




        return "{\"success\":1"+"}";
    }

}
