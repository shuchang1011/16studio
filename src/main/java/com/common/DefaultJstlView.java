package com.common;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.view.JstlView;

/**
*
 解决多个ViewResolver时jsp获取不到时,跳转到下一个ViewResolver
*/
 
public class DefaultJstlView extends JstlView {
    public boolean checkResource(Locale locale) throws Exception {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();//判断该jsp页面是否存在
    }
}
