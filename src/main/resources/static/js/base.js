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