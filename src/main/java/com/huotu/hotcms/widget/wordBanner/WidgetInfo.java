/*
 * 版权所有:杭州火图科技有限公司
 * 地址:浙江省杭州市滨江区西兴街道阡陌路智慧E谷B幢4楼
 *
 * (c) Copyright Hangzhou Hot Technology Co., Ltd.
 * Floor 4,Block B,Wisdom E Valley,Qianmo Road,Binjiang District
 * 2013-2016. All rights reserved.
 */

package com.huotu.hotcms.widget.wordBanner;

import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import me.jiangcai.lib.resource.service.ResourceService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author CJ
 */
public class WidgetInfo implements Widget{
    public static final String VALID_TITLE_TEXT_COLOR = "titleTextColor";//文本颜色
    public static final String VALID_SUB_TITLE_TEXT_COLOR = "subTitleTextColor";//文本颜色
    public static final String VALID_BG_COLOR = "bgColor";//背景颜色
    public static final String VALID_TITLE_FONT_SIZE = "titleFontSize";//字体大小
    public static final String VALID_SUB_TITLE_FONT_SIZE = "subTitleFontSize";//字体大小
    public static final String VALID_TITLE = "textTitle";//文字标题
    public static final String VALID_SUB_TITLE = "textSubTitle";//文字副标题
    public static final String VALID_BOLD = "textBold";//是否粗体
    public static final String VALID_LINK_URL = "linkUrl";//是否粗体

    @Override
    public String groupId() {
        return "com.huotu.hotcms.widget.wordBanner";
    }

    @Override
    public String widgetId() {
        return "wordBanner";
    }

    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "文字条幅";
        }
        return "text banner";
    }


    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "这是一个文字条幅，你可以对组件进行自定义修改。";
        }
        return "This is a text banner,  you can make custom change the component.";
    }

    @Override
    public String dependVersion() {
        return "1.0-SNAPSHOT";
    }

    @Override
    public Map<String, Resource> publicResources() {
        Map<String, Resource> map = new HashMap<>();
        map.put("thumbnail/wordBannerStyle1Style.png", new ClassPathResource("thumbnail/wordBannerStyle1Style.png"
                , getClass().getClassLoader()));
        map.put("js/wordBanner.js", new ClassPathResource("js/wordBanner.js"  , getClass().getClassLoader()));
        return map;
    }

    @Override
    public Resource widgetDependencyContent(MediaType mediaType) {
        if (mediaType.isCompatibleWith(CSS)){
            return  new ClassPathResource("css/wordBanner.css",getClass().getClassLoader());
        }
        if (mediaType.isCompatibleWith(Javascript)){
            return  new ClassPathResource("js/wordBanner.js",getClass().getClassLoader());
        }
        return null;
    }

    @Override
    public WidgetStyle[] styles() {
        return new WidgetStyle[]{new DefaultWidgetStyle()};
    }

    @Override
    public void valid(String styleId, ComponentProperties componentProperties) throws IllegalArgumentException {
        WidgetStyle style = WidgetStyle.styleByID(this,styleId);
        String textTitle = (String) componentProperties.get(VALID_TITLE);
        String textSubTitle =  componentProperties.get(VALID_SUB_TITLE).toString();
        String textColor = (String) componentProperties.get(VALID_TITLE_TEXT_COLOR);
        String subTextColor = (String) componentProperties.get(VALID_SUB_TITLE_TEXT_COLOR);
        String bgColor = (String) componentProperties.get(VALID_BG_COLOR);
        String titleFontSize = (String) componentProperties.get(VALID_TITLE_FONT_SIZE);
        String subTitlefontSize = (String) componentProperties.get(VALID_SUB_TITLE_FONT_SIZE);
        String bold =  componentProperties.get(VALID_BOLD).toString();
        String linkUrl = (String) componentProperties.get(VALID_LINK_URL);

       if (textTitle == null || subTitlefontSize==null || textSubTitle==null|| textColor == null || subTextColor==null ||
               bgColor == null || titleFontSize == null || bold == null ||linkUrl==null  || subTextColor.equals("")
                || textTitle.equals("") || textColor.equals("") || bgColor.equals("") || titleFontSize.equals("") ||subTitlefontSize.equals("")   ) {
            throw new IllegalArgumentException("控件属性缺少");
        }
    }

    @Override
    public Class springConfigClass() {
        return null;
    }

    @Override
    public ComponentProperties defaultProperties(ResourceService resourceService) {
        ComponentProperties properties = new ComponentProperties();
        properties.put(VALID_TITLE_TEXT_COLOR,"#000000");
        properties.put(VALID_SUB_TITLE_TEXT_COLOR,"#000000");
        properties.put(VALID_BG_COLOR,"#ffffff");
        properties.put(VALID_TITLE_FONT_SIZE,"16");
        properties.put(VALID_SUB_TITLE_FONT_SIZE,"16");
        properties.put(VALID_TITLE, description(Locale.CHINA));
        properties.put(VALID_SUB_TITLE, description(Locale.CHINA));
        properties.put(VALID_BOLD,"true");
        properties.put(VALID_LINK_URL,"http://www.baidu.com");
        return properties;
    }

}