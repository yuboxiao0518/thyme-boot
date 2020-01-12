$().ready(function(){
    getAllRoleName();
    validateRule();
});

var app = new Vue({
    el: '#app',
    data:{
        value1: ''
    }
});

$.validator.setDefaults({
    submitHandler : function() {
        addUser();
    }
});

function getAllRoleName() {
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'user/getAllRoleName',
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code === 200) {
                $("#userRole").html("");
                var level = "";
                level += "<div style='position: relative; min-height: 1px;  padding-right: 15px;'>";
                for (var i = 0; i < data.data.allRoleName.length; i++){
                    if (i % 3 === 0 && i !== 0) {
                        level += "</div>";
                        $("#userRole").append(level);
                        level = "";
                        level += "<div style='position: relative; min-height: 1px;  padding-right: 15px;'>";
                    }
                    level += "<input type='radio' name='userRole' style='margin-left: 1%;margin-top: 1.3%' value='"+data.data.allRoleName[i]+"'>"+data.data.allRoleName[i];
                }
                $("#userRole").append(level);
            }
        }
    });
}

function addUser(){
    var name=$("#name").val();
    var password=CryptoJS.SHA256($("#password").val()).toString();
    var nickName=$("#nickName").val();
    var sex=$('#sex input:radio:checked').val();
    var userRole = $('#userRole input:radio:checked').val();
    var mobile=$("#mobile").val();
    var email=$("#email").val();
    var birthday=$("#birthday").val();
    var hobby=$("#hobby").val();
    var liveAddress=$("#liveAddress").val();
    if (isMobileEmailDate(mobile, email, birthday)){
        $.ajax({
            cache : true,
            type : "GET",
            url : context + 'user/addUser',
            data :{
                "name":name,
                "password":password,
                "nickName":nickName,
                "sex":sex,
                "userRole":userRole,
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
                    } else if(data.data.code === 501){
                        parent.layer.msg("该用户已存在，操作失败");
                    } else if(data.data.code === 500){
                        parent.layer.msg("操作失败");
                    }
                    var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                    parent.layer.close(index);

                }
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
            },
            name : {
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
