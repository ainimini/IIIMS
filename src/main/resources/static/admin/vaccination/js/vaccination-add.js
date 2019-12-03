/**
 * 用户页面js
 */

$("#form-add").validate({
    rules: {
        vaccinationUserIdNumber: {
            required: true,
            isIdentity: true
        },
        vaccinationName: {
            required: true
        },
        vaccinationDate: {
            required: true,
            isBirth: true
        },
        vaccinationAmount: {
            required: true
        },
        vaccinationPlace: {
            required: true
        },
        vaccinationHospital: {
            required: true
        }
    },
    messages: {
        "vaccinationUserIdNumber": {
            required: "请填写接种疫苗用户身份证号！"
        },
        "vaccinationName": {
            required: "请填写接种疫苗名称！"
        },
        "vaccinationDate": {
            required: "请填写接种疫苗时间！"
        },
        "vaccinationAmount": {
            required: "请填写接种疫苗次数！"
        },
        "vaccinationPlace": {
            required: "请填写接种疫苗部位！"
        },
        "vaccinationHospital": {
            required: "请填写接种疫苗医院！"
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
        url: rootPath + "/UserVaccinationController/add",
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
