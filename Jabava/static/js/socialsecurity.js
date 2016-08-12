// socialsecurity.js
;(function(w, $){
    'use strict';

    var uploadCompent = function(){
        $('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
            var oEventTarget = $(this),
            oFile = $(this).val(),
            getFileType = $(this).attr('data-file-type'),
            fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase(),
            $getThumbZone = $(this).parents('[data-toggle="upload:file"]').prev('a'),
            getTitle = $getThumbZone.attr('title'),
            $getMsgCover = $(this).parents('[data-toggle="upload:file"]').next();

            oEventTarget.parents('[data-toggle="upload:file"]')
            .find(':text').val( oFile );

            $getMsgCover.css({
                height: $getMsgCover.parent().height(),
                lineHeight: $getMsgCover.parent().height()+'px',
                width: $getMsgCover.parent().width(),
                zIndex: 100
            }).text(getTitle+'上传中...').show();
            // upload file
            var formData=new FormData();
                formData.append('uploadFiles', $(this)[0].files[0]);
                formData.append('personId', w.personId);
                //上传文件类型  需要将值设置在指定的上传文件表单中
                formData.append("fileType", getFileType);
            $.ajax({
                url:  "employee/uploadSecurityFiles",
                type: 'POST',
                cache: false,
                dataType: 'json',
                data: formData,
                processData: false,
                contentType: false

            })
            .done(function(d){
                if(d.success){
                    $getThumbZone.attr('href', d.imgUrl)
                    .children('img').attr('src', d.imgUrl);
                    $getMsgCover.text(getTitle+'上传成功。');
                    setTimeout(function(){ $getMsgCover.hide(); }, 1500);
                }else{
                    $getMsgCover.text(getTitle+'上传失败。');
                    setTimeout(function(){ $getMsgCover.hide(); }, 1500);
                }

            });

        });

        $('[data-toggle="upload:file"]').on('click', function(event){
            $(this).find(':file').trigger('click.file:selected');
        });
    };

    var putSocialsecurity = function(postParams, hospitals){
        $.ajax({
            url: 'employee/updateSecurityProfileInfo',
            type: 'POST',
            dataType: 'json',
            contentType:"application/json",
            data: JSON.stringify(postParams)
        })
        .done(function(d) {
            if(d.success){
                putHospitals(hospitals, d);
            }else{
                swal({
                    title: d.msg,
                    type: 'wraning'
                });
            }
        });
        
    };
    var putHospitals = function(hospitals, msg){
        if( hospitals.length > 0 ){
            $.ajax({
                type : 'POST',
                url : "employee/designatedHospital",
                async : false,
                dataType: "json",
                contentType:'application/json',
                data: JSON.stringify( hospitals )
            })
            .done(function(d){
                if( !Boolean(d.status*1) ){
                    swal({
                        title: msg.msg,
                        type: 'success'
                    }, function(){
                        location.reload();
                    });
                }
            });
        }else{
            swal({
                title: msg.msg,
                type: 'success'
            }, function(){
                location.reload();
            });
        }
    };
    var postSocialsecurity = function(postParams, hospitals){
        $.ajax({
            url: 'employee/addSecurityProfileInfo',
            type: 'POST',
            dataType: 'json',
            contentType:"application/json",
            data: JSON.stringify(postParams)
        })
        .done(function(d) {
            if(d.success){
                postHospitals(hospitals, d);
            }else{
                swal({
                    title: d.msg,
                    type: 'wraning'
                });
            }
        });
    };
    var postHospitals = function(hospitals, msg){
        if( hospitals.length > 0 ){
            var getProfileId = msg.profileId;
            hospitals = $.map(hospitals, function(item, index) {
                item.profileId = getProfileId;
                return item;
            });
            $.ajax({
                type : 'POST',
                url : "employee/designatedHospital",
                async : false,
                dataType: "json",
                contentType:'application/json',
                data: JSON.stringify( hospitals )
            })
            .done(function(d){
                if( !Boolean(d.status*1) ){
                    swal({
                        title: msg.msg,
                        type: 'success'
                    }, function(){
                        location.reload();
                    });
                }
            });
        }else{
            swal({
                title: msg.msg,
                type: 'success'
            }, function(){
                location.reload();
            });
        }
    };

    var bindValidate = function(d){
        var validOpts = {
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
                securityCity: {
                    excluded: false,
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                securityCreateType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                securityAccount: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                securityStarttime: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        regexp: {
                            message: '日期格式不正确',
                            regexp:/^\d{4}\d{1,2}$/
                        }
                    }
                },
                securityActivatetime: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        regexp: {
                            message: '日期格式不正确',
                            regexp:/^\d{4}\d{1,2}$/
                        }
                    }
                },
                gongjijinCity: {
                    excluded: false,
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                gongjijinCreateType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                gongjijinAccount: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        }
                    }
                },
                gongjijinStarttime: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        regexp: {
                            message: '日期格式不正确',
                            regexp:/^\d{4}\d{1,2}$/
                        }
                    }
                },
                gongjijinActivatetime: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项'
                        },
                        regexp: {
                            message: '日期格式不正确',
                            regexp:/^\d{4}\d{1,2}$/
                        }
                    }
                }
            }
        };
        $('[data-form="validator"]').formValidation(validOpts)
        .on('success.form.fv', function(e){
            e.preventDefault();
            var postParams = $(e.target).serializeObject();

            var hospitalState = postParams.hospitalState;
            var hospitals = [];
            var getProfileId = postParams.profileId;
            if( Boolean( hospitalState*1 ) ){
                $('[name="hospital[]"]').each(function(index, el) {
                    hospitals.push({
                        //定点医院的id
                        'hospitalName': $(this).val(),
                        'personId': w.personId,
                        'profileId':getProfileId
                    });
                });
            }

            delete postParams['hospital[]'];
            delete postParams['hospitalState'];
            // console.log(postParams);
            if( !Boolean( d.status*1 ) ){
                postSocialsecurity(postParams, hospitals);
            }else{
                putSocialsecurity(postParams, hospitals);
            }
            return false;
        });
    };

    var render = function(d){
        d = {
            status: d.status,
            socialsecurity: d.data || {},
            cityList: d.cityList,
            hospital: d.hospital,
            photos: {
                '1': d.shenfenPhoto,
                '8': d.bankPhoto,
                '9': d.personalPhoto,
                '2': d.hukouIndexPhoto,
                '10': d.hukouSelfPhoto
            }
        };
        d.socialsecurity.personid = w.personId;

        // console.dir(d);
        // render template
        var strHtml = template('socialsecurity', d);
            strHtml = $(strHtml);
        $('[data-container="socialsecurity"]').prepend(strHtml);

        // socialsecurity.securityCity
        $.each(d.cityList, function(index, item) {
            if(item.baseDataCode === d.socialsecurity.securityCity && item.baseDataName === '北京'){
                var $getHospital = $('[data-container="hospital"]');
                getHospitals($getHospital);
                return false;
            }
        });
        // render template done

        // bind event
        uploadCompent();
        $('[data-toggle="select2"]').select2();
        $('[data-toggle="datepicker"]').datepicker({
            format: "yyyymm",
            language: "zh-CN",
            autoclose: true,
            weekStart: 1,
            startView: 1,
            maxViewMode: 1,
            minViewMode:1,
            forceParse: false
        }).on('changeDate', function(e){
            var getEleName = $(e.target).find(':text').attr('name');
            var $fv = $('[data-form="validator"]');
            $fv.formValidation('revalidateField', getEleName);
        });

        // 初始化定点医院
        showHospital();
        // bind form validate
        bindValidate(d);
    
    };

    var showHospital = function(){
        $('[data-toggle="select2"]').on('change', function(event) {
            if($(event.target).attr('name') === 'securityCity'){
                var getCity = event.added.text;
                var $getHospital = $('[data-container="hospital"]');
                if(getCity==='北京'){
                    getHospitals($getHospital);
                }else{
                    $('[name=hospitalState]').val(0);
                    if(!$getHospital.hasClass('hidden')){
                        $getHospital.addClass('hidden');
                    }
                }
            }
        });
        
    };

    var getHospitals = function($getHospital){
        var $getAllHospitalSelect = $getHospital.find('[data-toggle="select2"]');
        $.ajax({
            url: 'employee/designatedHospital/'+w.personId,
            type: 'GET',
            dataType: 'json'
        })
        .done(function(d) {
            $('[name=hospitalState]').val(1);
            if( !Boolean(d.status*1) && d.data.length > 0 ){
                var arrHospitalForEmployee = d.data;
                var getHospitalId = '';

                $.each($getAllHospitalSelect, function(index, ele) {
                    getHospitalId = arrHospitalForEmployee[index]['hospitalName'];
                    $(this).select2('val', getHospitalId);
                });
            }
            $getHospital.removeClass('hidden');
        });
    };

    var timer = setInterval(function(){
        if(w.personId!==undefined){
            clearInterval(timer);
            $.ajax({
                url: 'employee/getSecurityProfileInfoById',
                type: 'POST',
                dataType: 'json',
                data: {"personId":w.personId}
            })
            .done(function(d){
                render(d);
            });

           

        }
    }, 100);

})(window, jQuery);