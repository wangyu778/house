$().ready(function () {
    navBinding();
});

/**
 * tab点击监听事件
 */
function navBinding() {
    $(".nav-item").on('click',function () {
        let dataId = $(this);
        function success(o) {
            $("#content").html(o);
        }
        function error() {}
        $.baseAjax(dataId.attr("data-to"),"post","",success,error)
    })
}

/**
 * 后端后返的结果信息展示
 */
function toastInfo() {
    let status = $("#respStatus").val();
    let msg =  $("#respMsg").val();
    if(status === '200'){
        $("#successMsg").html(msg);
        $('#successToast').toast('show');
    }else {
        $("#errorMsg").html(msg);
        $('#errorToast').toast('show');
    }
}