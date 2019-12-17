$().ready(function() {
    var app = new Vue({
        el: '#app',
        data: {
            dynamicTags: [],
            inputVisible: false,
            inputValue: ''
        },
        methods: {
            handleClose:function(tag) {
                this.dynamicTags.splice(this.dynamicTags.indexOf(tag), 1);
            },
            showInput:function() {
                this.inputVisible = true;
                this.$nextTick(_ => {
                    this.$refs.saveTagInput.$refs.input.focus();
            });
            },
            handleInputConfirm:function() {
                let inputValue = this.inputValue;
                if (inputValue) {
                    this.dynamicTags.push(inputValue);
                }
                this.inputVisible = false;
                this.inputValue = '';
            },
            getValue:function (obj) {
                for (var i = 0; i < app.dynamicTags.length; i++){
                    if (app.dynamicTags[i].trim() === obj.target.innerText.trim()) {
                        return app.dynamicTags;
                    }
                }
                app.dynamicTags.push(obj.target.innerText)
            },
            qiehuan:function (obj) {
                if (obj.target.innerText.trim() === "用户管理"){
                    document.getElementById("yhgl").click();
                } else if(obj.target.innerText.trim() === "岗位管理"){
                    document.getElementById("gwgl").click();
                } else if(obj.target.innerText.trim() === "角色管理"){
                    document.getElementById("jsgl").click();
                }else if(obj.target.innerText.trim() === "菜单管理"){
                    document.getElementById("cdgl").click();
                }

                
            }

        }
    });
});