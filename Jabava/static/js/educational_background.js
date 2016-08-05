// educational_background.js
;(function(w, $){
    'use strict';

    var timer = setInterval(function(){
        if(w.renderReady){


            $('[data-toggle="datepicker"]').not('[data-form-target="content"] [data-toggle="datepicker"]')
            .datepicker({
                language: "zh-CN",
                autoclose: true
            })
            .on('changeDate', function(e){
                var getEleName = $(e.target).find(':text').attr('name');
                var $fv = $(this).parents('.fv-form');
                $fv.formValidation('revalidateField', getEleName);
            });


            clearInterval(timer);

        }
    }, 1000);

})(window, jQuery);