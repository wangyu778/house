$().ready(function () {
    navBinding();
});

/**
 * tab点击监听事件
 */
function navBinding() {
    $(".nav-item").on('click',function () {
        let dataId = $(this);
        console.log(dataId.attr("data-to"));
        function success(o) {
            $("#content").html(o);
        }
        function error() {}
        $.baseAjax("/overview/index","post","",success,error)
    })
}