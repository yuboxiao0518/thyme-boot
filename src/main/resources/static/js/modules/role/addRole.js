$().ready(function(){
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        addRole();
    }
});

function addRole(){
    var name=$("#name").val();
    var authority=$("#authority").val();
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'role/addRole',
        data :{
            "name":name,
            "authority":authority
        },
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code === 200) {
                if (data.data.code === 200){
                    parent.layer.msg("操作成功");
                } else if (data.data.code === 501){
                    parent.layer.msg("该角色已存在，操作失败");
                } else if (data.data.code === 500){
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
            name : {
                required : true
            }, authority : {
                required : true
            }
        },
        messages : {
            name : {
                required : icon + "请输入角色名称"
            }, authority : {
                required : icon + "请输入角色权限"
            }
        }
    })
}