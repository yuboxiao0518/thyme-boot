$.validator.setDefaults({
    submitHandler : function() {
        addMenu();
    }
});

var app = new Vue({
    el:"#app",
    data:{

    },
    methods:{
        getMenuLevel:function() {
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
                        for (var i = 0; i < data.data.menuLevel.length; i++) {
                            var level = "";
                            level += "<option value='"+data.data.menuLevel[i]+"'>"+data.data.menuLevel[i]+"</option>";
                            $("#menuLevel").append(level);
                            level = "";
                        }
                    }

                }
            });
        },
        getValue:function() {
            $("#menuLevel").change(function(){
                var menuLevel =  $(this).children('option:selected').val();
                if (menuLevel !== "1") {
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
                                var level = "";
                                level += "<div class='layui-input-inline'>";
                                level += "<select id='menuLevel' name='modules' lay-verify='required' lay-search='' style='width: 235px;height: 33.9px;border: 1px solid #ccc;border-radius: 4px;margin-left: 6%;'>";
                                for (var i = 0; i < data.data.menuNames.length; i++){
                                    level += "<option value='"+data.data.menuNames[i].id+"'>"+data.data.menuNames[i].menuName+"</option>";
                                }
                                level += "</select></div></div>";
                                $("#menuNames").append(level);
                                $("#menuHrefs").show();
                            }
                        }
                    });
                } else {
                    $("#menuNames").hide();
                    $("#menuHrefs").hide();
                }
            });
        },
        validateRule:function() {
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
    },
    mounted:function () {
        this.getMenuLevel();
        this.getValue();
        this.validateRule();
    }
});

function addMenu(){
    var menuName=$("#menuName").val();
    var menuCode=$("#menuCode").val();
    var menuHref=$("#menuHref").val();
    var menuLevel=$("#menuLevel").val();
    var menuNames = "";
    if (menuLevel !== "1"){
        menuNames = $('#menuNames option:selected').text();
    }
    var menuWeight=$("#menuWeight").val();
    var isShow=$('#isShow input:radio:checked').val();
    $.ajax({
        cache : true,
        type : "GET",
        url : context + 'menu/addMenu',
        data :{
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
                } else if(data.data.code === 501){
                    parent.layer.msg("该菜单已存在，操作失败");
                } else if(data.data.code === 500){
                    parent.layer.msg("操作失败");
                }
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            }
        }
    });
}