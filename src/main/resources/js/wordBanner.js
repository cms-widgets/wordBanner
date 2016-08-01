/**
 * Created by lhx on 2016/6/23.
 */
CMSWidgets.initWidget({
    editor: {
        properties: null,
        saveComponent: function (onSuccess, onFailed) {
            var me = this;
            $.each($(".textTitle"), function (i, obj) {
                me.properties.textTitle = $(obj).val();
            });
            $.each($(".textSubTitle"), function (i, obj) {
                me.properties.textSubTitle = $(obj).val();
            });
            $.each($(".textBgColor"), function (i, obj) {
                me.properties.textBgColor = $(obj).val();
            });
            $.each($(".textColor"), function (i, obj) {
                me.properties.textColor = $(obj).val();
            });
            $.each($(".textFontSize"), function (i, obj) {
                me.properties.textFontSize = $(obj).val() + 'px';
            });
            $.each($(".textBold"), function (i, obj) {
                me.properties.textBold = $(obj).is(":checked");
            });
            $.each($(".linkUrl"), function (i, obj) {
                me.properties.linkUrl = $(obj).val();
            });
            if (this.properties.textContent == "" || this.properties.textBold == true || this.properties.textFontSize == ""
                || this.properties.textColor == "" || this.properties.textBgColor == "") {
                onFailed("组件数据缺少,未能保存,请完善。");
                return;
            }
            onSuccess(this.properties);
            return this.properties;
        },
        fontSizeChange: function () {
            $(".form-horizontal").on("click", ".textFontSize", function () {
                $(this).siblings(".spanFontSize").text($(this).val() + "px")
            });
        },
        initProperties: function () {
            this.properties.textSubTitle = ""
            this.properties.textBgColor = "";
            this.properties.textColor = "";
            this.properties.textFontSize = "";
            this.properties.textBold = "";
            this.properties.linkUrl = "";
            this.properties.textTitle = "";
        },
        open: function (globalId) {
            this.properties = widgetProperties(globalId);
            this.fontSizeChange();
        },
        close: function (globalId) {
            $(".form-horizontal").off("click", ".textFontSize")
        }
    }
})

