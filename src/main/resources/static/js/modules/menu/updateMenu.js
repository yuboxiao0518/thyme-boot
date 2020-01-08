$().ready(function(){
    initradio("isShow",$("#isShow").val());
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        updateUser();
    }
});

function updateUser(){
    var id=$("#id").val();
    var parentId=$("#parentId").val();
    var menuName=$("#menuName").val();
    var menuCode=$("#menuCode").val();
    var menuHref=$("#menuHref").val();
    var menuLevel=$("#menuLevel").val();
    var menuWeight=$("#menuWeight").val();
    var isShow=$('input:radio:checked').val()===undefined?"":$('input:radio:checked').val();
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'menu/updateMenu',
        data :{
            "id":id,
            "parentId":parentId,
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
                } else if(data.data.code === 200){
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
            id : {
                required : true
            }, name : {
                required : true
            }, password : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入用户名"
            }, password : {
                required : icon + "请输入密码"
            }
        }
    })
}

function initradio(rName,rValue){
    if (rValue === "true"){
        rValue = 1;
    } else {
        rValue = 0;
    }
    var rObj = document.getElementsByName(rName);
    for(var i = 0;i < rObj.length;i++){
        if(rObj[i].value == rValue){
            rObj[i].checked =  'checked';
        }
    }

}
