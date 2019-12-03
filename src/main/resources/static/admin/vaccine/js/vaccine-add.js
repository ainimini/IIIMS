/**
 * 用户页面js
 */

$("#form-add").validate({
    rules: {
        vaccineName: {
            required: true
        },
        approvalNumber: {
            required: true
        },
        productionNumber: {
            required: true,
        },
        productionDate: {
            required: true,
            isBirth: true
        },
        effectiveDate: {
            required: true,
            isBirth: true
        },
        factoryName: {
            required: true
        },
        inventory: {
            required: true
        }
    },
    messages: {
        "vaccineName": {
            required: "请填写疫苗名称！"
        },
        "approvalNumber": {
            required: "请填写疫苗批准文号！"
        },
        "productionNumber": {
            required: "请填写疫苗产品批号！"
        },
        "productionDate": {
            required: "请填写疫苗生产日期！"
        },
        "effectiveDate": {
            required: "请填写疫苗有效日期！"
        },
        "factoryName": {
            required: "请填写疫苗生产厂名称！"
        },
        "inventory": {
            required: "请填写疫苗库存！"
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
        url: rootPath + "/VaccineInfoController/add",
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


