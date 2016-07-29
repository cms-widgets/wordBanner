/**
 * Created by admin on 2016/6/23.
 */
var wordBanner = {
    properties:null,
    saveComponent: function () {
        var me = this;
        $.each($(".textContent"),function(i,obj){
            me.properties.textContent = $(obj).val();
        });
        $.each($(".textBgColor"),function(i,obj){
            me.properties.textBgColor = $(obj).val();
        });
        $.each($(".textColor"),function(i,obj){
            me.properties.textColor = $(obj).val();
        });
        $.each($(".textFontSize"),function(i,obj){
            me.properties.textFontSize = $(obj).val()+'px';
        });
        $.each($(".textBold"),function(i,obj){
            me.properties.textBold = $(obj).is(":checked");
        });
        if(this.properties.textContent=="" || this.properties.textBold==true || this.properties.textFontSize==""
        || this.properties.textColor==""   || this.properties.textBgColor=="" ){
            layer.msg("组件数据缺少,未能保存,请完善。");
            return null;
        }
        return this.properties;
    },
    fontSizeChange:function(){
        $(".textFontSize").change(function(){
            $(this).siblings(".spanFontSize").text($(this).val()+"px")
        });
    },
    initProperties:function(){
        this.properties.textContent=""
        this.properties.textBgColor="";
        this.properties.textColor="";
        this.properties.textFontSize="";
        this.properties.textBold="";
    },
    init: function (globalId) {
        this.properties = widgetProperties(globalId);
        this.fontSizeChange();
    }
};

