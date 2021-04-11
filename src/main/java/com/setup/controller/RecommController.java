package com.setup.controller;

import com.setup.entity.*;
import com.setup.mapper.CommentMapper;
import com.setup.mapper.RecommendMapper;
import com.setup.service.AlbumService;
import com.setup.service.ImageService;
import com.setup.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.crypto.Data;
import java.beans.IntrospectionException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/recomm")
public class RecommController {
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private ImageService imageService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private AlbumService albumService;

    Page page = new Page();
    List<Album> albums;
    //刚进来的页面推荐，这个不用了
  /*  @RequestMapping(value = "/initRe",method = RequestMethod.GET)
    public String initRe(HttpSession session,@RequestParam int start) {
        List<Album> albums = recommendMapper.queryCommAlbum(start, 8);
        //根据输入的页码，改变sql中分页查询的开始值
        start = (start-1)*8;
        //设置相册的session
        session.setAttribute("re_albums",albums);

        return "recommend";
    }*/

    //根据标签进来的，刚进来的页面
    @RequestMapping(value = "/queryTag",method = RequestMethod.GET)
    public String queryTag(HttpSession session,String name,Integer start) {
        System.out.println(name);
        if (start==null) start = 1;

        if ("全部".equals(name) || name==null){  //如果是全部，即查询全部
            albums = recommendMapper.queryCommAlbum((start-1)*page.getLimit(), page.getLimit());
            //session.setAttribute("re_albums",albums);
            //数据总数
            int count = recommendMapper.queryCountAlbum();
            //给page赋值
            payPage(count,name,start,"queryTag");
        }
        else{
             albums = recommendMapper.queryByTag(name,(start-1)*page.getLimit(),page.getLimit());
           // session.setAttribute("re_albums",albums);
            int count = recommendMapper.queryCount(name);  //数据总数

            payPage(count,name,start,"queryTag");

        }

        System.out.println(albums);
        session.setAttribute("re_albums",albums);
      //  session.setAttribute("name",name);//当前类型
        System.out.println("当前类型："+name);
        session.setAttribute("page",page);

        return "recommend";
    }

    //根据搜索框进来的
    @RequestMapping(value = "/queryByName",method = RequestMethod.GET)
    public String queryByName(HttpSession session,@RequestParam String name,int start) {
        if ("".equals(name)){
            queryTag(session,"全部",1);
            return "recommend";
        }
        albums = recommendMapper.queryByName(name,(start-1)*page.getLimit(),page.getLimit());
        int count = recommendMapper.queryCountByName(name);
        session.setAttribute("re_albums",albums);
        payPage(count,name,start,"queryByName");
        System.out.println(albums);
        System.out.println(page);

        return "recommend";
    }


    //根据推荐界面传来的相册 id 查询该相册的所有信息,和相册下的照片信息，和用户信息，评论信息
    @RequestMapping(value = "/queryByAmID",method = RequestMethod.GET)
    public String queryByAmID(HttpSession session,@RequestParam int aid){
        //相册信息
        Album album = recommendMapper.queryByAmID(aid);
        //System.out.println(album);
        session.setAttribute("currentAlbum",album);

        //照片信息
        List<Image> list = imageService.queryImageByAid(aid);
      //  System.out.println(list);
        session.setAttribute("currentImages",list);
        //该相册拥有者的用户信息
        User user = userService.queryOther(album.getUid());
        session.setAttribute("otherUser",user);
        //评论信息
        /*List<Comment> comments = commentMapper.queryComment(aid);
        System.out.println(comments);
        session.setAttribute("comments",comments);*/

        List<Comment> allComments = commentMapper.queryComment(aid);
        System.out.println("全部:"+allComments);
        List<Comment> parents = new ArrayList<>();//最终排好的评论，返回的
        //做成层级评论
        for (Comment comment:allComments){
            if (comment.getParent_id()==null){
              parents.add(comment);
            }else {
              for (Comment parent:parents){
                  System.out.println("现在的parent:"+parent);
                  if (parent.getC_id() == comment.getParent_id()){
                      if (parent.getChild() == null) {
                          parent.setChild(new ArrayList<>());
                      }
                      //添加进入父评论列表
                      parent.getChild().add(comment);
                      break;
                  }
              }
            }
        }
        System.out.println(parents);
        session.setAttribute("comments",parents);


        return "reAlbum";
    }



    //评论功能
    @PostMapping(value = "/comment")
    public String comment(HttpSession session,Integer album_id,Integer userID,String comment){
        //获取到相册id,用户id  评论的内容
        if (userID==null || "".equals(userID)){
            return "login";
        }
       else{
            Comment comment1 = new Comment();
            //获取当前时间
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            comment1.setC_time(formatter.format(calendar.getTime()));
            comment1.setAid(album_id);
            comment1.setUid(userID);
            comment1.setC_content(comment);

            System.out.println(comment1);
            //插入数据库
            commentMapper.insertComment(comment1);

            queryByAmID(session,album_id);

        }
        System.out.println("当前相册id"+album_id);
        System.out.println("当前用户id:"+userID);

        return "reAlbum";
    }

    //回复功能
    @PostMapping(value = "/replyComment")
    public String getComment(HttpSession session,@RequestParam String commentTex,String targetName,Integer album_id,Integer parent_id,Integer userID){
        //接受 相册id,评论对象的id,回复内容
        System.out.println("评论的相册id"+album_id);
        System.out.println("回复发起者："+userID);
        System.out.println("被评论的(父评论)的ID:"+parent_id);
        System.out.println("内容："+commentTex);
        System.out.println("回复的用户名称："+targetName);

        if (userID==null || "".equals(userID)){
            return "login";
        }

        Comment comment = new Comment();
        //获取当前时间
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        comment.setC_time(formatter.format(calendar.getTime()));
        comment.setAid(album_id);
        comment.setUid(userID);
        comment.setParent_id(parent_id);
        comment.setC_content(commentTex);
        comment.setTargetName(targetName);

        System.out.println(comment);
        //插入数据库
        commentMapper.insertReply(comment);

        queryByAmID(session,album_id);



        return "reAlbum";
    }


    //进入其它用户的个人界面
    @GetMapping(value = "/intoOtherView")
    public String intoOtherView(HttpSession session,@RequestParam Integer otherUid){
        System.out.println("其它用户的id是："+otherUid);
        //该用户的信息可能已经存储在了  session 的otherUser中，所以要判断
        User user=(User) session.getAttribute("otherUser");
        if (user==null){
            User user1 = userService.queryOther(otherUid);
            session.setAttribute("otherUser",user1);
        }
        //查询该用户的所有相册信息

        List<Album> albums = albumService.queryAlbum(otherUid);
        session.setAttribute("otherAlbums",albums);

        return "otherUserInfo";
    }




    //公共方法，给page对象赋值，知道了传进来的名称(可以是相册名称name 或 相册类型tag)  和 页码
     public void payPage(int count,String name,int start,String mothName){
         page.setCount(count);
         //页码数
         int rows=0;

         if (count % page.getLimit()>0)  rows = (count/page.getLimit())+1;
         else if (count<page.getLimit())   rows = 1;
         else  rows = count/page.getLimit();

         page.setRows(rows);
         //当前页码
         page.setCurrent(start);
         page.setName(name);
         http://localhost:8770/recomm/queryTag?name=风景&start=1"
         page.setPath("http://localhost:8770/recomm/"+""+mothName+"?name="+""+name+""+"&start=");
     }

}
