var lockReconnect=true;//避免ws重复连接

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
function toastInfo(status, msg) {
    if(status === undefined && msg === undefined){
        status = $("#respStatus").val();
        msg =  $("#respMsg").val();
    }
    if(status === '200' || status === 200){
        $("#successMsg").html(msg);
        $('#successToast').toast('show');
    }else {
        $("#errorMsg").html(msg);
        $('#errorToast').toast('show');
    }
}

/**
 * 注册用户
 */
function userRegister() {
    function success(o) {
        $("#content").html(o);
    }
    function error() {}
    $.baseAjax("/mine/userRegister","get","",success,error)
}

function manageHouseIndex() {
    function success(o) {
        $("#content").html(o);
    }
    function error() {}
    $.baseAjax("/manage/houseManageIndex","post","",success,error)
}

function manageFoodIndex() {
    function success(o) {
        $("#content").html(o);
    }
    function error() {}
    $.baseAjax("/manage/foodManageIndex","post","",success,error)
}

function repairIndex() {
    function success(o) {
        $("#content").html(o);
    }
    function error() {}
    $.baseAjax("/mine/index","post","",success,error)
}

function rentingIndex() {
    function success(o) {
        $("#content").html(o);
    }
    function error() {}
    $.baseAjax("/renting/index","post","",success,error)
}