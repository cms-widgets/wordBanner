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
import org.apache.http.entity.ContentType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * @author CJ
 */
public class WidgetInfo implements Widget{
    private static final String VALID_TEXT_COLOR = "textColor";//文本颜色
    private static final String VALID_BG_COLOR = "textBgColor";//背景颜色
    private static final String VALID_FONT_SIZE = "textFontSize";//字体大小
    private static final String VALID_CONTENT = "textContent";//文字内容
    private static final String VALID_BOLD = "textBold";//是否粗体

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
        if (locale.equals(Locale.CHINESE)) {
            return "文字条幅";
        }
        return "text banner";
    }

    @Override
    public String description() {
        return "这是一个文字条幅，你可以对组件进行自定义修改。";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINESE)) {
            return "这是一个文字条幅，你可以对组件进行自定义修改。";
        }
        return "This is a text banner,  you can make custom change the component.";
    }

    @Override
    public int dependBuild() {
        return 0;
    }

    @Override
    public Map<String, Resource> publicResources() {
        Map<String, Resource> map = new HashMap<>();
        map.put("thumbnail/wordBannerStyle1Style.png", new ClassPathResource("thumbnail/wordBannerStyle1Style.png"
                , getClass().getClassLoader()));
        return map;
    }

    @Override
    public Resource widgetDependencyContent(ContentType contentType) {
        if (contentType.getMimeType().equalsIgnoreCase("text/css")){
            return  new ClassPathResource("css/wordBanner.css",getClass().getClassLoader());
        }
        return null;
    }

    @Override
    public Resource widgetJs() {
        return new ClassPathResource("js/wordBanner.js", getClass().getClassLoader());
    }

    @Override
    public WidgetStyle[] styles() {
        return new WidgetStyle[]{new DefaultWidgetStyle()};
    }

    @Override
    public void valid(String styleId, ComponentProperties componentProperties) throws IllegalArgumentException {
        WidgetStyle[] widgetStyles = styles();
        boolean flag = false;
        if (widgetStyles == null || widgetStyles.length < 1) {
            throw new IllegalArgumentException();
        }
        for (WidgetStyle ws : widgetStyles) {
            if ((flag = ws.id().equals(styleId))) {
                break;
            }
        }
        if (!flag) {
            throw new IllegalArgumentException("样式不存在");
        }
        String content = (String) componentProperties.get(VALID_CONTENT);
        String textColor = (String) componentProperties.get(VALID_TEXT_COLOR);
        String bgColor = (String) componentProperties.get(VALID_BG_COLOR);
        String fontSize = (String) componentProperties.get(VALID_FONT_SIZE);
        String bold = (String) componentProperties.get(VALID_BOLD);
        if (content == null || textColor == null || bgColor == null || fontSize == null || bold == null
                || content.equals("") || textColor.equals("") || bgColor.equals("") || fontSize.equals("")) {
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
        properties.put(VALID_TEXT_COLOR,"#000000");
        properties.put(VALID_BG_COLOR,"#ffffff");
        properties.put(VALID_FONT_SIZE,"16px");
        properties.put(VALID_CONTENT,description(Locale.CHINESE));
        properties.put(VALID_BOLD,true);
        return properties;
    }

}