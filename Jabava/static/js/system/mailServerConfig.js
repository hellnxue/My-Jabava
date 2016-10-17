//mailServerConfig.js
(function($){
	'use strict'
    var setMailConfig = function(){
        $.ajax({
            url : "system/loadMailConfig",
            dataType:'json',
            type : 'post'
        })
        .done(function(d){
            if(d.success){
                $.each(d.result, function(key, val) {
                    var getInput = $('[name='+key+']');
                    if( Boolean( getInput.length ) ){
                        if(getInput.attr('type').toLowerCase() === 'radio'){
                            getInput.eq(Math.abs(val-1)).prop('checked', true);
                        }else{
                            getInput.val(val);
                        }
                    }
                });
            }
        });
    }();

    $('[data-id="validate"]')
    .formValidation({
        err: {
                container: 'tooltip'
            },
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        locale: 'zh_CN',

        fields: {
            sendTo: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    },
                    regexp: {
                        message: '请输入有效的邮箱',
                        regexp: /^([a-zA-Z0-9_\-\.])+@(\w)+((\.\w+)+)$/
                    }
                }
            },
            mailPassword: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            },
            mailServer: {
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            },
            mailPort:{
                validators: {
                    notEmpty: {
                        message: '请填写必填项目'
                    }
                }
            }
        }
    })
    .on('success.form.fv', function(event) {
        event.preventDefault();
        var $form = $(event.target);
        var fv = $form.data('formValidation');
        fv.disableSubmitButtons(true);
        $.ajax({
            url : "system/saveMailConfig",
            data : $("#mailConfigId").serialize(),
            dataType:'json',
            type : 'post'
        })
        .done(function(d){
            swal({
                title: '',
                text: d.msg,
                type: d.success?'success':'error'
            },function(d){
                $form.formValidation('resetForm');
            })
        })
    });

    $('[data-id="validate"]').on('click', 'button:reset', function(e) {
        var $form = $('[data-id="validate"]');
        $form.formValidation('resetForm');
    });
})(jQuery);