$().ready(function(){
    initradio("sex",$("#sex").val());
    var app = new Vue({
        el: '#app',
        data:{
            value1: ''
        }
    });
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        updateUser();
    }
});

function updateUser(){
    var id=$("#id").val();
    var password=CryptoJS.SHA256($("#password").val()).toString();
    var name=$("#name").val();
    var nickName=$("#nickName").val();
    var sex=$('input:radio:checked').val()===undefined?"":$('input:radio:checked').val();
    var mobile=$("#mobile").val();
    var email=$("#email").val();
    var birthday=$("#birthday").val();
    var hobby=$("#hobby").val();
    var liveAddress=$("#liveAddress").val();
    if (isMobileEmailDate(mobile, email, birthday)){
        $.ajax({
            cache : true,
            type : "GET",
            url : context + 'user/updateUser',
            data :{
                "id":id,
                "name":name,
                "password":password,
                "nickName":nickName,
                "sex":sex,
                "mobile":mobile,
                "email":email,
                "birthday":birthday,
                "hobby":hobby,
                "liveAddress":liveAddress
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

function isMobileEmailDate(mobile,email,birthday) {
    var flag = true;
    if (mobile !== ""){
        var phoneReg = /^[1][3,4,5,7,8][0-9]{9}$/;
        if (!phoneReg.test(mobile)) {
            layer.msg("手机号格式不正确，请重新输入！");
            document.getElementById("mobile").value = "";
            flag =  false;
        }
    }
    if(email !== "") {
        var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
        //调用正则验证test()函数
        isok= reg.test(email);
        if(!isok) {
            layer.msg("邮箱格式不正确，请重新输入！");
            document.getElementById("email").value = "";
            flag = false;
        }
    }
    if (birthday !== ""){
        var dataReg = /^(\d{4})-(\d{2})-(\d{2})$/
        if (!dataReg.test(birthday)) {
            layer.msg("邮箱格式不正确，请重新输入！(日期格式:yyyy-MM-dd)");
            document.getElementById("birthday").value = "";
            flag = false;
        }
    }
    return flag;
}

function initradio(rName,rValue){
    var rObj = document.getElementsByName(rName);

    for(var i = 0;i < rObj.length;i++){
        if(rObj[i].value == rValue){
            rObj[i].checked =  'checked';
        }
    }
}
