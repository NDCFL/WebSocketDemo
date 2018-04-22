package com.dt.filter;

import com.dt.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.http.*;
import java.util.ArrayList;
@Component
public class OnlineListener implements HttpSessionListener,HttpSessionAttributeListener {

    // 参数
    ServletContext sc;
    ArrayList list = new ArrayList();
    // 新建一个session时触发此操作
    public void sessionCreated(HttpSessionEvent se) {
        sc = se.getSession().getServletContext();
        UserVo userVo = (UserVo) se.getSession().getAttribute("userVo");
    }
    // 销毁一个session时触发此操作
    public void sessionDestroyed(HttpSessionEvent se) {
        UserVo userVo = (UserVo) se.getSession().getAttribute("userVo");
        System.out.println(userVo.getId()+"=========马上销毁");
        if (!list.isEmpty()) {
            list.remove((String) se.getSession().getAttribute("userName"));
            sc.setAttribute("list", list);
        }
    }
    // 在session中添加对象时触发此操作，在list中添加一个对象
    public void attributeAdded(HttpSessionBindingEvent sbe) {
        list.add(sbe.getValue());
        System.out.println(sbe.getValue());
        sc.setAttribute("list", list);
    }

    // 修改、删除session中添加对象时触发此操作
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
        UserVo userVo = (UserVo) arg0.getSession().getAttribute("userVo");
    }

    public void attributeReplaced(HttpSessionBindingEvent arg0) {
    }
}