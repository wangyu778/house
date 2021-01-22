$().ready(function () {
    getActiveInfo($("#rentingInfo"));
});

/**
 * 更改样式
 * @param selectId 选中的标签
 */
function changeActive(selectId) {
    let activeBut =  $("#"+selectId);
    activeBut.addClass("badge").addClass("badge-pill").addClass("badge-secondary").addClass("mine-active");
    activeBut.parents().siblings().children().removeClass("badge").removeClass("badge-pill").removeClass("badge-secondary").removeClass("mine-active");
}

/**
 * 获取点击信息，展示页面
 * @param selectItem $
 */
function getActiveInfo(selectItem) {
    let url = $(selectItem).attr("data-to");
    function success(o) {
        $("#mine #activePage").html(o);
    }
    function error() {}
    $.baseAjax(url,"get",{},success,error);
}