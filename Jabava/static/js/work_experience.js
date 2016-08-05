// work_experience.js
;(function(w, $){
    'use strict';

    var timer = setInterval(function(){
        if(w.renderReady){
            $('[data-toggle="datepicker"]')
            .not('[data-form-target="content"] [data-toggle="datepicker"]')
            .datepicker({
                format: "yyyy-mm-dd",
                autoclose: true
            })
            .on('changeDate', function(e){
                var $fv = $(this).parents('.fv-form');
                if($(this).hasClass('input-daterange')){
                    $(e.target).each(function(index, el) {
                        $fv.formValidation('revalidateField', $(this).attr('name'));
                    });

                }else{
                    var getEleName = $(e.target).find(':text').attr('name');
                    $fv.formValidation('revalidateField', getEleName);
                }

            });

            $('[name="proveMobile"]').on('keyup', function(event) {
                var isEmpty = $(this).val() === '';
                var getEleName = $(this).attr('name');
                var $fv = $(this).parents('[data-form-validator="validator"]');

                $fv.formValidation('enableFieldValidators', getEleName, !isEmpty);

                if( !isEmpty ){
                    $fv.formValidation('validateField', getEleName);
                }
            });

            clearInterval(timer);

        }
    }, 1000);

})(window, jQuery);