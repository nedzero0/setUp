package com.setup.controller;

import com.setup.entity.Album;
import com.setup.entity.Page;
import com.setup.mapper.RecommendMapper;
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

    Page page = new Page();

    //刚进来的页面推荐
    @RequestMapping(value = "/initRe",method = RequestMethod.GET)
    public String initRe(HttpSession session,@RequestParam int start) {
        List<Album> albums = recommendMapper.queryCommAlbum(start, 8);
        //根据输入的页码，改变sql中分页查询的开始值
        start = (start-1)*8;
        //设置相册的session
        session.setAttribute("re_albums",albums);

        return "recommend";
    }

    //根据标签进来的

    @RequestMapping(value = "/queryTag",method = RequestMethod.GET)
    public String queryTag(HttpSession session,@RequestParam String tag,int start) {
        List<Album> albums;
        if ("全部".equals(tag)){  //如果是全部，即查询全部
            albums = recommendMapper.queryCommAlbum((start-1)*page.getLimit(), page.getLimit());
            session.setAttribute("re_albums",albums);
            //数据总数
            int count = recommendMapper.queryCountAlbum();
            page.setCount(count);
            //页码总数
            int rows=0;
            if (count % page.getLimit()>0){
                rows = count/page.getLimit()+1;
            }else {
                rows = count/page.getLimit();
            }
            page.setRows(rows);
            //当前页码
            page.setCurrent(start);
            page.setPath("http://localhost:8770/recomm/queryTag?tag="+""+tag+""+"&start=");
        }
        else{
             albums = recommendMapper.queryByTag(tag,(start-1)*page.getLimit(),page.getLimit());
            session.setAttribute("re_albums",albums);
            int count = recommendMapper.queryCount(tag);  //数据总数
            page.setCount(count);
            //页码数
            int rows=0;
            if (count % page.getLimit()>0){
                rows = count/page.getLimit()+1;
            }else {
                rows = count/page.getLimit();
            }
            page.setRows(rows);
            //当前页码
            page.setCurrent(start);
            page.setPath("http://localhost:8770/recomm/queryTag?tag="+""+tag+""+"&start=");
        }


     /*   String tag_url = "http://localhost:8770/recomm/queryTag?tag="+""+tag+""+"&start=";
        System.out.println(tag_url);*/

        System.out.println(albums);
        session.setAttribute("re_albums",albums);
       // session.setAttribute("count",count);
       // session.setAttribute("start",start);
       // session.setAttribute("sum",sum);
        //session.setAttribute("tag_url",tag_url);
        session.setAttribute("tag",tag);//当前类型

        session.setAttribute("page",page);

        return "recommend";
    }


    }
