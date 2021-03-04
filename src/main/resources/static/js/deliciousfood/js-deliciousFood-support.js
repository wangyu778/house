$().ready(function () {
    getFoodList();
});

/*获取美食列表*/
function getFoodList(sortType,sortValue,businessType,businessTypeValue) {
    let data = {};
    let $sortValue = $("#sortValue");
    let $businessTypeValue =  $("#businessTypeValue");
    if(sortType !== undefined){ $sortValue.attr('data-value',sortType);}
    if(businessType !== undefined){$businessTypeValue.attr('data-value',businessType);}
    data.sortType = $sortValue.attr("data-value");
    data.businessType = $businessTypeValue.attr("data-value");
    function success(o) {
        if(sortValue !== undefined){$sortValue.text(sortValue);}
        if(businessTypeValue !== undefined){$businessTypeValue.text(businessTypeValue);}
        $("#deliciousFood #foodList").html(o);
    }
    function error() {}
    $.baseJsonAjax("/deliciousFood/getFoodList","POST",data,success,error);
}

/*收藏美食*/
function collectionFood(foodId) {
    function success(o) {
        toastInfo(o.status,o.msg);
        if(o.status === 200){
            $("#deliciousFood #collectionSpan_"+foodId).text('已收藏');
            $("#deliciousFood #collectionButton_"+foodId).removeClass('btn-warning').addClass('btn-secondary').attr('disabled','disabled');
        }
    }
    function error() {}
    $.baseAjax("/deliciousFood/collectionFood","POST",{foodId: foodId},success,error);
}