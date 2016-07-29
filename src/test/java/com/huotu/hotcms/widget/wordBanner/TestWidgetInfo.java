package com.huotu.hotcms.widget.wordBanner;

import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import com.huotu.widget.test.WidgetTest;
import com.huotu.widget.test.WidgetTestConfig;
import com.huotu.widget.test.bean.WidgetViewController;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 * Created by lhx on 2016/6/23.
 */

public class TestWidgetInfo extends WidgetTest {

    @Override
    protected boolean printPageSource() {
        return false;
    }

    @Autowired
    private WidgetViewController widgetViewController;

    @Override
    protected void editorWork(Widget widget, WebElement editor, Supplier<Map<String, Object>> currentWidgetProperties) {
        //保存自定义属性
        try{
            currentWidgetProperties.get();
            assert false;
        }catch (IllegalStateException ignored){
            assertThat(0).as("save没有属性值返回异常").isEqualTo(0);
        }

        try{
            currentWidgetProperties.get();
            assert false;
            Map<String,Object> ps = currentWidgetProperties.get();
            String bold = ps.get("textBold").toString();
            assertThat(bold).as("默认没有选中粗体").isEqualTo("false");

            //设置粗体
            WebElement elementBold = editor.findElement(By.className("textBold"));
            elementBold.click();
            ps = currentWidgetProperties.get();
            bold = ps.get("textBold").toString();
            assertThat(bold).as("单击选中粗体").isEqualTo("true");
        }catch (IllegalStateException ignored){
            assertThat(0).as("save没有属性值返回异常").isEqualTo(0);
        }

    }

    @Override
    protected void browseWork(Widget widget, WidgetStyle style, Function<ComponentProperties, WebElement> uiChanger) {
        uiChanger = (properties) -> {
            widgetViewController.setCurrentProperties(properties);
            String uri = "/browse/" + WidgetTestConfig.WidgetIdentity(widget) + "/" + style.id();
            if (printPageSource())
                try {
                    mockMvc.perform(get(uri))
                            .andDo(print());
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new IllegalStateException("no print html");
                }
            driver.get("http://localhost" + uri);
            WebElement webElement = driver.findElement(By.id("browse")).findElement(By.tagName("div"));
            return webElement;
        };
        ComponentProperties componentProperties = new ComponentProperties();
        ComponentProperties properties = new ComponentProperties();
        properties.put("textBold","true");
        properties.put("textContent","hello wordBanner");
        properties.put("textFontSize",  "20px");
        properties.put("textColor", "#111111");
        properties.put("textBgColor", "#111111");

        componentProperties.put("properties", properties);

        WebElement webElement = uiChanger.apply(componentProperties);

        List<WebElement> wordBanner = webElement.findElements(By.className("wordBanner"));
        assertThat(wordBanner.size()).isEqualTo(1);
        assertThat(wordBanner.get(0).getText()).isEqualToIgnoringCase("hello wordBanner");

    }
}
