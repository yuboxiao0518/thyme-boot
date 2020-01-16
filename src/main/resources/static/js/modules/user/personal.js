var app =  new Vue({
    el: '#app',
    data:{
        ruleForm: {
            id:'',
            name: '',
            nickName: '',
            sex: '',
            userRole: '',
            mobile: '',
            email: '',
            birthday: '',
            hobby: '',
            liveAddress: ''
        },
        rules: {
            name: [
                { required: true, message: '请输入用户名', trigger: 'blur' }
            ]
        }
    },
    methods: {
        submitForm:function(formName) {
            this.$refs[formName].validate((valid) => {
                if (valid) {
                    $.ajax({
                        url: context + 'user/editUser',
                        type: 'POST',
                        data: JSON.stringify(app.ruleForm),
                        dataType : 'json',
                        contentType:'application/json',
                        success: function (res) {
                            if (res.code === 200) {
                                app.$message.success(res.message);
                            } else {
                                app.$message.error(res.message);
                            }
                        }
                    })
                } else {
                    console.log('error submit!!');
                    return false;
                }
             });
        }
    },
    mounted:function() {
        var sysUser = JSON.parse($("#sysUser").val());
        this.ruleForm.id = sysUser.id;
        this.ruleForm.name = sysUser.name;
        this.ruleForm.nickName = sysUser.nickName;
        this.ruleForm.sex = sysUser.sex;
        this.ruleForm.userRole = $("#roleName").val();
        this.ruleForm.mobile = sysUser.mobile;
        this.ruleForm.email = sysUser.email;
        this.ruleForm.birthday = sysUser.birthday;
        this.ruleForm.hobby = sysUser.hobby;
        this.ruleForm.liveAddress = sysUser.liveAddress;
    }
});