$().ready(function(){
    getMenuLevel();
    getValue();
    if ($("#isShow").val() === "true"){
        $(":radio[name='isShow'][value='1']").prop("checked", "checked");
    } else {
        $(":radio[name='isShow'][value='0']").prop("checked", "checked");
    }
    if ($("#menuLevel").val() !== "1"){
        $("#menuHrefs").show();
    }
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        updateUser();
    }
});

function getMenuLevel(){
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'menu/getMenuLevel',
        error : function(request) {
            parent.layer.alert("Connection error");
        },
        success : function(data) {
            if (data.code === 200) {
                $("#menuLevel").html("");
                $("#menuLevel").append("<option value='0'>请选择菜单层级</option>");
                for (var i = 0; i < data.data.menuLevel.length; i++) {
                    var level = "";
                    level += "<option value='"+data.data.menuLevel[i]+"'>"+data.data.menuLevel[i]+"</option>";
                    $("#menuLevel").append(level);
                    level = "";
                }
            }
            document.getElementById("menuLevel")[ $("#menuLevels").val()].selected=true;
            pdMenuLevel($("#menuLevel").val());
        }
    });
}

function getValue() {
    $("#menuLevel").change(function(){
        var menuLevel =  $(this).children('option:selected').val();
        pdMenuLevel(menuLevel);
    });
}

function pdMenuLevel(menuLevel) {
    if (menuLevel !== "1" && menuLevel !== null) {
        $.ajax({
            cache: true,
            type: "GET",
            url: context + 'menu/getPreviousMenu?menuLevel=' + menuLevel,
            error: function (request) {
                parent.layer.alert("Connection error");
            },
            success: function (data) {
                if (data.code === 200) {
                    $("#menuNames").show();
                    $("#menuNames").html("");
                    $("#menuNames").append("<label class='col-sm-3 control-label'>一级菜单：</label>");
                    $("#menuNames").append("<div class='col-sm-8'>");
                    var level = "";
                    level += "<div class='col-sm-8'>";
                    for (var i = 0; i < data.data.menuNames.length; i++){
                        if (i % 3 === 0 && i !== 0) {
                            level += "</div>";
                            $("#menuNames").append(level);
                            level = "";
                            level += "<div class='col-sm-8' style='margin-left: 33%'>";
                        }
                        level += "<input type='radio' name='menuName' style='margin-left: 2%;margin-top: 1.3%' value='"+data.data.menuNames[i].id+"'>"+data.data.menuNames[i].menuName;
                    }
                    $("#menuNames").append(level);
                    $(":radio[name='menuName'][value='"+$("#parentId").val()+"']").prop("checked", "checked");
                    $("#menuHrefs").show();
                }
            }
        });
    } else {
        $("#menuNames").hide();
        $("#menuHrefs").hide();
    }
}

function updateUser(){
    var id=$("#id").val();
    var menuName=$("#menuName").val();
    var menuCode=$("#menuCode").val();
    var menuHref=$("#menuHref").val();
    var menuLevel=$("#menuLevel").val();
    var menuNames = "";
    if (menuLevel !== "1"){
        menuNames = $('#menuNames input:radio:checked').val();
    }
    var menuWeight=$("#menuWeight").val();
    var isShow=$('#show input:radio:checked').val()===undefined?"":$('#show input:radio:checked').val();
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'menu/updateMenu',
        data :{
            "id":id,
            "menuName":menuName,
            "menuCode":menuCode,
            "menuHref":menuHref,
            "menuLevel":menuLevel,
            "menuNames":menuNames,
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
