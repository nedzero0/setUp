package com.setup.controller;

import com.google.gson.Gson;
import com.setup.entity.*;
import com.setup.service.AlbumService;
import com.setup.service.CollectService;
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
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins ="*",maxAge = 3600)
@RequestMapping("/album")
public class AlbumController {
    @Autowired
    private UserService userService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ImageService imageService;
    @Autowired
    private CollectService collectService;

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



    /**
     * 下面是前端用vue重写的   后端也做出了调整
     * **/

    //相册id
    private Integer aid;
    private Integer index;

    //查看相册下的图片,Vue的方式
    //先从前端获取到相册id，然后存储起来  前端自动调用queryAlbumVue方法
    @RequestMapping("/queryAidVue")
    public String queryAidVue(HttpSession session,Integer id,Integer index){
          this.aid = id;
          this.index = index;
        session.setAttribute("newAid",aid);
        session.setAttribute("index",index);
          return "albumVue";
    }
    //查询相册下的照片
    @CrossOrigin(origins ="*",maxAge = 3600)
    @RequestMapping("/queryAlbumVue")
    @ResponseBody
    public String queryAlbumVue(){
        List<Image> images = imageService.queryImageByAid(aid);
        //改为json格式传输返回
        Gson gson = new Gson();
        String result = gson.toJson(images);
        return result;
    }

    //设置照片为相册封面，传入 照片的地址信息
    @GetMapping("/setCoverVue")
    @ResponseBody
    public void setCoverVue(HttpSession session,String coverPath){
        //设置相册封面
        int i = imageService.setCover(aid, coverPath);
        //获取user session
        User user = (User)session.getAttribute("user");
        //更新session
        List<Album> albums = albumService.queryAlbum(user.getUid());
        session.setAttribute("albums",albums);
    }

    //删除单/多个照片
    @GetMapping("/deleteItemVue")
    @ResponseBody
    public void deleteItem(String[] vals,HttpSession session){ //传入照片id  用户id
        System.out.println("vals："+ Arrays.toString(vals));
        //获取user session
        User user = (User)session.getAttribute("user");
        if (vals!=null){
            //删除
            int i = imageService.recoveryImages(vals);
            int newAid = (int)session.getAttribute("newAid");
            if (i>0){
                System.out.println("更新session 现在的相册id是"+newAid);
                //更新相册下的图片session
                List<Image> images = imageService.queryImageByAid(newAid);
                session.setAttribute("images",images);
                //更新回收站的session
                List<Image> reImages = imageService.queryReImage(user.getUid());
                session.setAttribute("reImages",reImages);
            }
        }
    }

    /**
     *查看收藏  删除收藏  添加收藏
     * */

    @PostMapping("/addCollect")
    @ResponseBody
    public void addCollect(Collect collect){
       collectService.addCollect(collect);
    }

    //删除收藏
    @GetMapping("/deleteCollect")
    @ResponseBody
    public void deleteCollect(Integer id){
        collectService.deleteCollect(id);
    }

    //分页查询
    @GetMapping("/page")
    @ResponseBody
    public PageVue<Album> page(@RequestParam(value = "uid") Integer uid,
                                 @RequestParam(value = "pageSize") Integer pageSize,
                                 @RequestParam(value = "pageNum") Integer pageNum){
        return collectService.query(uid,pageSize,pageNum);
    }


}
