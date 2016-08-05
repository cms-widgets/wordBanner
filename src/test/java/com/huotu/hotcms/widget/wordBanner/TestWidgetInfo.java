package com.huotu.hotcms.widget.wordBanner;

import com.huotu.hotcms.widget.ComponentProperties;
import com.huotu.hotcms.widget.Widget;
import com.huotu.hotcms.widget.WidgetStyle;
import com.huotu.widget.test.WidgetTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
/**
 * Created by lhx on 2016/6/23.
 */
public class TestWidgetInfo extends WidgetTest {

    @Override
    protected boolean printPageSource() {
        return true;
    }

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
        ComponentProperties properties = new ComponentProperties();
        properties.put(WidgetInfo.VALID_TEXT_COLOR,"#000000");
        properties.put(WidgetInfo.VALID_BG_COLOR,"#ffffff");
        properties.put(WidgetInfo.VALID_FONT_SIZE,"16px");
        properties.put(WidgetInfo.VALID_TITLE,"hello wordBanner");
        properties.put(WidgetInfo.VALID_SUB_TITLE,"#hello wordBanner");
        properties.put(WidgetInfo.VALID_BOLD,"true");
        properties.put(WidgetInfo.VALID_LINK_URL,"http://www.baidu.com");
        properties.put(WidgetInfo.VALID_BG_IMG,"http://www.baidu.com");
        WebElement webElement = uiChanger.apply(properties);
        List<WebElement> desc = webElement.findElements(By.className("desc"));
        assertThat(desc.size()).isEqualTo(1);
        assertThat(desc.get(0).getText()).isEqualToIgnoringCase("#hello wordBanner");

    }
}
