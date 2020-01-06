$().ready(function(){
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        addMenu();
    }
});

function addMenu(){
    var menuName=$("#menuName").val();
    var menuCode=$("#menuCode").val();
    var menuHref=$("#menuHref").val();
    var menuLevel=$("#menuLevel").val();
    var menuWeight=$("#menuWeight").val();
    var isShow=$('input:radio:checked').val();
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'menu/addMenu',
        data :{
            "menuName":menuName,
            "menuCode":menuCode,
            "menuHref":menuHref,
            "menuLevel":menuLevel,
            "menuWeight":menuWeight,
            "isShow":isShow
        },
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code === 200) {
                if (data.data.code === 200){
                    parent.layer.msg("操作成功");
                    parent.location.reload();
                } else if(data.data.code === 501){
                    parent.layer.msg("该菜单已存在，操作失败");
                } else if(data.data.code === 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            }
            // window.location.reload();
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules : {
            menuName : {
                required : true
            }, menuCode : {
                required : true
            }, menuHref : {
                required : true
            }, menuLevel : {
                required : true
            }, menuWeight : {
                required : true
            }, isShow : {
                required : true
            }
        },
        messages : {
            menuName : {
                required : icon + "请输入菜单名称"
            }, menuCode : {
                required : icon + "请输入菜单别名"
            }, menuHref : {
                required : icon + "请输入菜单链接"
            }, menuLevel : {
                required : icon + "请输入菜单层级"
            }, menuWeight : {
                required : icon + "请输入排序"
            }, menuCode : {
                isShow : icon + "请输入状态"
            }
        }
    })
}