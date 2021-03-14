$().ready(function () {
    getHouseList();
});

function getHouseList(roomLocation, roomPrice, roomType, roomDirection) {
    let data = {};
    data.roomLocation = roomLocation;
    data.roomPrice = roomPrice;
    data.roomType = roomType;
    data.roomDirection = roomDirection;
    function success(o) {
        $("#renting #houseList").html(o);
    }
    function error() {}
    $.baseJsonAjax("/renting/getHouseList","POST",data,success,error);
}

$("#renting #navigation .row div").on("click", function () {
    let dataType = this.getAttribute("data-type");
    let dataTarget = this.getAttribute("data-target");
    $("#"+dataType).val(dataTarget);
    $(this).css("color","#00ae66").siblings().css("color","black");
    getHouseList($("#roomLocation").val(),$("#roomPrice").val(),$("#roomType").val(),$("#roomDirection").val())
});

function collectionHouse(roomId) {
    function success(o) {
        toastInfo(o.status,o.msg);
        if(o.status === 200){
            $("#renting #collectionSpan_"+roomId).text('已收藏');
            $("#renting #collectionButton_"+roomId).removeClass('btn-warning').addClass('btn-secondary').attr('disabled','disabled');
        }
    }
    function error() {}
    $.baseAjax("/renting/collectionHouse","POST",{roomId: roomId},success,error);
}