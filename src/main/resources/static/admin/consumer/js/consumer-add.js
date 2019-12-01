/**
 * 用户页面js
 */

$("#form-add").validate({
    submitHandler:function(form){
        add();
    }
});


/**
 * 用户添加方法
 */
function add() {
    var dataFormJson=$("#form-add").serialize();
    $.ajax({
        cache : true,
        type : "POST",
        url : rootPath + "/ConsumerController/add",
        data : dataFormJson,
        dataType: "json",
        async : false,
        error : function(request) {
            $.modal.alertError("系统错误");
        },
        success : function(data) {
            $.operate.saveSuccess(data);
        }
    });
}


