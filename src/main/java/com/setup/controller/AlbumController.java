package com.setup.controller;

import com.setup.entity.Album;
import com.setup.entity.Image;
import com.setup.entity.User;
import com.setup.service.AlbumService;
import com.setup.service.ImageService;
import com.setup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
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

    //删除相册和相册下的图片
    @RequestMapping("/deleteAlbum")
    @ResponseBody
    public String deleteAlbum(HttpSession session, @RequestBody String aid){
        System.out.println("要删除的相册id是"+aid);
        //删除相册，假删除  更新相册和其下图片的状态码为0
        System.out.println(aid.substring(4));
        int i = albumService.deAlbum(Integer.parseInt(aid.substring(4)));
        User user = (User)session.getAttribute("user");
        //更新相册的session
        List<Album> albums = albumService.queryAlbum(user.getUid());
        session.setAttribute("albums",albums);
        //更新回收站的图片session
        List<Image> reImages = imageService.queryReImage(user.getUid());
        session.setAttribute("reImages",reImages);
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
        //设置现在操作的相册newAid和该相册在list中的index下标
        session.setAttribute("newAid",aid);
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

    //批量删除图片,到回收站
    @RequestMapping("/deleteItems")
    public String deleteItems(String[] chkIds,HttpSession session,HttpServletRequest request){
        System.out.println("开始批量删除");
        String uid = request.getParameter("uid");
        System.out.println("该用户id为"+uid);
        int id = Integer.parseInt(uid);
        if (chkIds!=null){
            //删除
            int i = imageService.recoveryImages(chkIds);
            int newAid = (int)session.getAttribute("newAid");
            if (i>0){
                System.out.println("更新session 现在的相册id是"+newAid);
                //更新相册下的图片session
                List<Image> images = imageService.queryImageByAid(newAid);
                session.setAttribute("images",images);
                //更新回收站的session
                List<Image> reImages = imageService.queryReImage(id);
                session.setAttribute("reImages",reImages);

            }
        }
        return "redirect:/own/album.html";
    }

    //批量还原到相册
    @RequestMapping("/reductionItems")
    public String reductionItems(String[] redIds,HttpSession session,HttpServletRequest request){
        System.out.println("开始批量还原");
        String uid = request.getParameter("uid");
        int id = Integer.parseInt(uid);
        if (redIds!=null){
            //要判断原相册状态是否为1
            Album album = albumService.queryOldAlbum(redIds[0]);
            if (album.getA_status()==0){
                //还原相册状态为1
               albumService.updateStatus(album.getAid());
                //更新相册的session
                List<Album> albums = albumService.queryAlbum(album.getUid());
                session.setAttribute("albums",albums);
            }
            //还原到相册
            int i = imageService.updateRecoverys(redIds);
            if (i>0){
                //更新回收站的session
                List<Image> reImages = imageService.queryReImage(id);
                session.setAttribute("reImages",reImages);
            }
        }
        return "redirect:/own/recycle.html";
    }

    //彻底删除图片，从回收站中
    @RequestMapping("/delForRe")
    public String delForRe(String[] redIds,HttpSession session){
        System.out.println("彻底删除");
        //System.out.println(Arrays.toString(redIds));
        User user = (User)session.getAttribute("user");
        if (redIds!=null){

            //删除文件夹中的
            //获得要删除的文件的位置信息
            List<String> list =  imageService.queryAddress(redIds);
            System.out.println(list);
            if (list!=null){
                //循环删除
                for (String str:list) {
                    File file = new File("D://"+str);
                    file.delete();
                 }
            }

            //删除数据库中的
            int i = imageService.delImages(redIds);

            if (i>0){
                //更新回收站的session
                List<Image> reImages = imageService.queryReImage(user.getUid());
                session.setAttribute("reImages",reImages);
            }
        }

        return "redirect:/own/recycle.html";
    }


    //设置照片为相册封面，传入参数 当前相册的id，照片的地址信息
    @PostMapping("/setCover")
    @ResponseBody
    public String setCover(String aid,String image_path){
        System.out.println("相册id为："+aid);
        System.out.println("该图片的物理地址是："+image_path);
        //设置相册封面
        int i = imageService.setCover(aid, image_path);
        if (i>0){
            return "设置成功";
        }
        return "设置失败";
    }

    //删除单个照片
    @RequestMapping("/deleteItem")
    public String deleteItem(Integer i_id, Integer uid, HttpSession session){ //传入照片id  用户id
        System.out.println("照片id:"+i_id+"   用户id:"+uid);
        //删除照片到回收站
        imageService.recoveryImage(i_id);
        //更新相册和回收站
        int newAid = (int)session.getAttribute("newAid");
        //更新相册下的图片session
        List<Image> images = imageService.queryImageByAid(newAid);
        session.setAttribute("images",images);
        //更新回收站的session
        List<Image> reImages = imageService.queryReImage(uid);
        session.setAttribute("reImages",reImages);
        return "redirect:/own/album.html";
    }
}
