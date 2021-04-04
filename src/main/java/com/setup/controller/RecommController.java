package com.setup.controller;

import com.setup.entity.Album;
import com.setup.entity.Image;
import com.setup.entity.Page;
import com.setup.mapper.RecommendMapper;
import com.setup.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/recomm")
public class RecommController {
    @Autowired
    private RecommendMapper recommendMapper;
    @Autowired
    private ImageService imageService;

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

    //根据标签进来的
    @RequestMapping(value = "/queryTag",method = RequestMethod.GET)
    public String queryTag(HttpSession session,@RequestParam String name,int start) {
        System.out.println(name);

        if ("全部".equals(name)){  //如果是全部，即查询全部
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


    //根据推荐界面传来的相册 id 查询相册的所有信息,和相册下的照片信息，和用户信息，评论信息
    @RequestMapping(value = "/queryByAmID",method = RequestMethod.GET)
    public String queryByAmID(HttpSession session,@RequestParam int aid){
        //相册信息
        Album album = recommendMapper.queryByAmID(aid);
        System.out.println(album);
        session.setAttribute("currentAlbum",album);

        //照片信息
        List<Image> list = imageService.queryImageByAid(aid);
        System.out.println(list);
        session.setAttribute("currentImages",list);


        return "reAlbum";
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
