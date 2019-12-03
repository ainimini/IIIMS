/**
 * 用户页面js
 */

$("#form-add").validate({
    rules: {
        username: {
            required: true,
            isUserName: true,
            minlength: 2,
            maxlength: 20,
            remote: {
                url: rootPath + "/ConsumerController/checkLoginNameUnique",
                type: "post",
                dataType: "json",
                dataFilter: function (data, type) {
                    if (data == "0")
                        return true;
                    else
                        return false;
                }
            }
        },
        password: {
            required: true,
            minlength: 5,
            maxlength: 20
        },
        nickname: {
            required: true,
            isName: true
        },
        datetimepicker1: {
            required: true
        },
        detailedAddress: {
            required: true
        },
        birthdate: {
            required: true,
            isBirth: true
        },
        gender: {
            required: true
        },
        phone: {
            required: true,
            isPhone: true
            /*remote: {
                url: rootPath + "/system/user/checkPhoneUnique",
                type: "post",
                dataType: "json",
                data: {
                    name: function () {
                        return $.trim($("#phone").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }*/
        },
        idNumber: {
            required: true,
            isIdentity: true
        }
    },
    messages: {
        "username": {
            required: "请填写登录名称！",
            remote: "用户已经存在！"
        },
        "password": {
            required: "请填写密码！"
        },
        "nickname": {
            required: "请填写接种疫苗人的姓名！"
        },
        "gender": {
            required: "请填写接种疫苗人的性别！"
        },
        "birthdate": {
            required: "请填写接种疫苗人的生日！"
        },
        "phone": {
            required: "请填写监护人的手机号！",
            remote: "手机号码已经注册！"
        },
        "idNumber": {
            required: "请填写接种疫苗人的身份证号！"
        }
    },
    submitHandler: function (form) {
        add();
    }
});


/**
 * 用户添加方法
 */
function add() {
    var dataFormJson = $("#form-add").serialize();
    $.ajax({
        cache: true,
        type: "POST",
        url: rootPath + "/ConsumerController/add",
        data: dataFormJson,
        dataType: "json",
        async: false,
        error: function (request) {
            $.modal.alertError("系统错误");
        },
        success: function (data) {
            $.operate.saveSuccess(data);
        }
    });
}


