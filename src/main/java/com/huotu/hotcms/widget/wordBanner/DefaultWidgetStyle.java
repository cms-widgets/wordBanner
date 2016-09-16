package com.huotu.hotcms.widget.wordBanner;

import com.huotu.hotcms.widget.WidgetStyle;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.util.Locale;

/**
 * Created by lhx on 2016/6/23.
 */

public class DefaultWidgetStyle implements WidgetStyle {


    @Override
    public String id() {
        return "wordBannerStyle1";
    }

    @Override
    public String name() {
        return "默认";
    }

    @Override
    public String name(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return name();
        }
        return "default style";
    }

    @Override
    public String description() {
        return "基于bootstrap样式的文字条幅";
    }

    @Override
    public String description(Locale locale) {
        if (locale.equals(Locale.CHINA)) {
            return "基于bootstrap样式的文字条幅";
        }
        return "Based on the bootstrap style by Friendship text banner";
    }

    @Override
    public Resource thumbnail() {
        return new ClassPathResource("/thumbnail/wordBannerStyle1Style.png", getClass().getClassLoader());
    }

    @Override
    public Resource previewTemplate() {
        return null;
    }

    @Override
    public Resource browseTemplate() {
        return new ClassPathResource("/template/wordBannerStyle1BrowseTemplate.html", getClass().getClassLoader());
    }
}
