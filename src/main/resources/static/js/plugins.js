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

;(function($, undefined) {
    "use strict";

    var pluginName = 'baseJsonAjax';

    $[pluginName] = function(url,type,data,success,error){
        $.ajax({
            type: type==''?'POST':type,
            url: url,
            contentType: "application/json",
            data: JSON.stringify(data),
            success: success,
            error: error
        });
    };
    $.extend($[pluginName], {
        options: {
        }
    });
})(jQuery);