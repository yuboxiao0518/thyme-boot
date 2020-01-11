$().ready(function(){
    app.getData();
    validateRule();
});

$.validator.setDefaults({
    submitHandler : function() {
        addRole();
    }
});

var app = new Vue({
    el:"#app",
    data:{
        data: [],
        defaultProps: {
            children: 'children',
            label: 'menuName'
        },
        expandAll:true
    },
    methods:{
        getData: function() {
            $.ajax({
                cache : true,
                type : "GET",
                url : context + 'role/getData',
                error : function(request) {
                    parent.layer.alert("Connection error");
                },
                success : function(data) {
                    if (data.code === 200) {
                        app.data = data.data.menuList;
                    }
                }
            });
        },
        getCheckedKeys:function () {
            return this.$refs.tree.getCheckedKeys();
        },
        getHalfCheckedKeys:function (value) {
            return this.$refs.tree.getHalfCheckedKeys().concat(value);
        }
    }
});

function addRole(){
    var name=$("#name").val();
    var authority=$("#authority").val();
    var childrenId = app.getCheckedKeys();
    var ids = app.getHalfCheckedKeys(childrenId);
    $.ajax({
        cache : true,
        type : "POST",
        url : context + 'role/addRole',
        data :{
            "name":name,
            "authority":authority,
            "ids":ids
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