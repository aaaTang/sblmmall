package com.mmall.controller.backend;


import com.github.pagehelper.PageInfo;
import com.mmall.common.Const;
import com.mmall.common.ResponseCode;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IOrderService;
import com.mmall.service.IUserService;
import com.mmall.util.CookieUtil;
import com.mmall.util.JsonUtil;
import com.mmall.util.RedisPoolUtil;
import com.mmall.vo.OrderNumVo;
import com.mmall.vo.OrderVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/order")

public class OrderManageController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IOrderService iOrderService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderList(HttpServletRequest httpServletRequest, @RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        String loginToken=CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userStr=RedisPoolUtil.get(loginToken);
        User user=JsonUtil.string2Obj(userStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员账号");
        }
        if (iUserService.checkAdminRole(user).issuccess()){
            return iOrderService.manageList(pageNum,pageSize);

        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse<OrderVo> orderdetail(HttpServletRequest httpServletRequest, Long orderNo){
        String loginToken=CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userStr=RedisPoolUtil.get(loginToken);
        User user=JsonUtil.string2Obj(userStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员账号");
        }
        if (iUserService.checkAdminRole(user).issuccess()){
            return iOrderService.manageDetail(orderNo);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse<PageInfo> orderSearch(HttpServletRequest httpServletRequest,Integer status,String name, Long orderNo,String phone,@RequestParam(value = "pageNum",defaultValue = "1") int pageNum, @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        String loginToken=CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userStr=RedisPoolUtil.get(loginToken);
        User user=JsonUtil.string2Obj(userStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员账号");
        }
        if (iUserService.checkAdminRole(user).issuccess()){
            return iOrderService.manageSearch(status,name,orderNo,phone,pageNum,pageSize);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("send_goods.do")
    @ResponseBody
    public ServerResponse<String> orderSendGoods(HttpServletRequest httpServletRequest, Long orderNo,Integer type,String num){
        String loginToken=CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userStr=RedisPoolUtil.get(loginToken);
        User user=JsonUtil.string2Obj(userStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员账号");
        }
        if (iUserService.checkAdminRole(user).issuccess()){
            return iOrderService.manageSendGoods(orderNo,type,num);
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

    @RequestMapping("getNum.do")
    @ResponseBody
    public ServerResponse<OrderNumVo> getNum(HttpServletRequest httpServletRequest){
        String loginToken=CookieUtil.readLoginToken(httpServletRequest);
        if (StringUtils.isEmpty(loginToken)){
            return ServerResponse.createByErrorMessage("用户未登录，无法获取当前用户的信息");
        }
        String userStr=RedisPoolUtil.get(loginToken);
        User user=JsonUtil.string2Obj(userStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员账号");
        }
        if (iUserService.checkAdminRole(user).issuccess()){
            return iOrderService.getOrderNum();
        }else {
            return ServerResponse.createByErrorMessage("无权限操作");
        }
    }

}
