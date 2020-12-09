;(function($, undefined) {
    "use strict";
    let pluginName = 'baseAjax';
    $[pluginName] = function(url,type,data,success,error){
        $.ajax({
            type: type === '' ? 'POST' : type,
            url: url,
            data: data,
            success: success,
            error: error
        });
    };
    $.extend($[pluginName], {
        options: {
        }
    });
})(jQuery);
