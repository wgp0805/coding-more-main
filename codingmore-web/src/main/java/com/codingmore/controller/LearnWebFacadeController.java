package com.codingmore.controller;

import com.codingmore.service.ILearnWebRequestStrategy;
import com.codingmore.util.WebRequestParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Api(tags = "学习网站前端入口")
public class LearnWebFacadeController {
    private static Logger LOGGER = LoggerFactory.getLogger(LearnWebFacadeController.class);
    @Resource(name = "channelPageRequestStrategy")
    private ILearnWebRequestStrategy channelPageRequestStrategy;
    @Resource(name = "contentPageRequestStrategy")
    private ILearnWebRequestStrategy contentPageRequestStrategy;

    @Resource(name = "indexPageRequestStrategy")
    private ILearnWebRequestStrategy indexPageRequestStrategy;

    /*
        这里是配置的请求多个uri
        这里的RequestMapping(value= {"要返回的页面"，“项目访问的路径”},method = "请求方法")
        requestmapping这个博客讲的很全面，可以参考https://blog.csdn.net/swebin/article/details/90634310
     */
//    @RequestMapping(value = {"/index.html","/index"}, method = RequestMethod.GET)
    @ApiOperation("首页页入口")
    //此处这样写也有效
    @GetMapping(value = {"/index.html","/"})
    //这里的modelMap就相当于request.setAttribute方法，如果没有这个前端模板是不能进行取值的，https://blog.csdn.net/qq_41753340/article/details/121793399
    public String index(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        //将request response modelMap 进行构建查询参数，下边的设计不错
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).model(model).build();
        return indexPageRequestStrategy.handleRequest(webRequestParam);
    }

    @ApiOperation("内容动态页入口")
    @RequestMapping(value = {"/{postId:[0-9]+}.html"}, method = RequestMethod.GET)
    public String post( @PathVariable Long postId, HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).postId(postId).model(model).build();
        return contentPageRequestStrategy.handleRequest(webRequestParam);
    }


    @ApiOperation("内容动态页入口")
    @RequestMapping(value = {"/{channelId:[0-9]+}/{postId:[0-9]+}.html"}, method = RequestMethod.GET)
    public String content( @PathVariable Long channelId,@PathVariable Long postId, HttpServletRequest request,
                          HttpServletResponse response, ModelMap model) {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).channelId(channelId).postId(postId).model(model).build();
        return contentPageRequestStrategy.handleRequest(webRequestParam);
    }


    @ApiOperation("内容动态分页入口")
    @RequestMapping(value = {"/{channelId:[0-9]+}/postpage_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String contentPage( @PathVariable Long channelId, @PathVariable Integer page,
                              HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).channelId(channelId).page(page).model(model).page(page).build();
        return contentPageRequestStrategy.handleRequest(webRequestParam);
    }

    /**
     * 栏目动态页入口(外网)
     */
    /*@ApiOperation("栏目动态页入口")
    @RequestMapping(value = {"/{channelId:[0-9]+}.html"}, method = RequestMethod.GET)
    public String channel(@PathVariable Long channelId, HttpServletRequest request, HttpServletResponse response,
                          ModelMap model){
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).channelId(channelId).model(model).build();
        return channelPageRequestStrategy.handleRequest(webRequestParam);
    }*/


    /**
     * 栏目动态分页入口
     */
    @ApiOperation("栏目动态分页入口")
    @RequestMapping(value = {"/{channelId:[0-9]+}_{page:[0-9]+}.html"}, method = RequestMethod.GET)
    public String channelPage( @PathVariable Long channelId,  @PathVariable Integer page, HttpServletRequest request,
                              HttpServletResponse response, ModelMap model) /*throws GlobalException*/ {
        WebRequestParam webRequestParam = new WebRequestParam.Builder().request(request).response(response).channelId(channelId).model(model).page(page).build();
        return channelPageRequestStrategy.handleRequest(webRequestParam);
    }


}
