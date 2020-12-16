$().ready(function () {
    // getUserInfo();
    getTagInfo();
});

/**
 * 获取用户信息
 */
function getUserInfo() {
    function success(o) {
        $("#mine #userInfo").html(o);
    }
    function error() {

    }
    $.baseAjax("/mine/getUserInfo","get",{},success(),error());
}

/**
 * 获取选中标签的内容
 */
function getTagInfo() {

}

/**
 * 更改样式
 * @param selectId 选中的标签
 */
function changeActive(selectId) {
    let activeBut =  $("#"+selectId);
    activeBut.addClass("badge").addClass("badge-pill").addClass("badge-secondary").addClass("mine-active");
    activeBut.parents().siblings().children().removeClass("badge").removeClass("badge-pill").removeClass("badge-secondary").removeClass("mine-active");
}