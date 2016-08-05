// list_membership_roster.js
;(function(w, $){
    'use strict';
    // transfer modal
    var evtManuallyModal = function(){
        $('[data-modal-toggle="manually"]').on('click', function(event){
            event.preventDefault();
            var getModalTarget = $(this).attr('data-target');
            var $modal = $(getModalTarget);

            var itemsId = $modal.data('itemsId');

            if( itemsId !== undefined ){
                var getAttrPrefix = $modal.data('modal');
                switch(getAttrPrefix){
                    case 'trial':
                        doTransfer.trial( {
                            modal:$modal,
                            attrPrefix : getAttrPrefix
                        }, itemsId );
                        break;
                    case 'jobtransfer':
                        doTransfer.jobtransfer( {
                            modal:$modal,
                            attrPrefix : getAttrPrefix
                        }, itemsId );
                        break;
                    case 'dimission':
                        doTransfer.dimission( {
                            modal:$modal,
                            attrPrefix : getAttrPrefix
                        }, itemsId );
                        break;
                }
            }else{
                swal({
                    title: '请先选择需要办理人员变动的员工。',
                    type: 'info'
                });
            }
        });
    };

    var dataFiller = function(d, prefix, baseData){
        var $getItems = $('[data-'+prefix+']');
        $.each($getItems, function(index, item){
            var getItemVal =  d[$(this).data(prefix)];
            var getTagName = $(this)[0].tagName.toLowerCase();

            if( getTagName === 'input' ){
                switch( $(this).attr('type') ){
                    case 'date':
                        $(this).val( getItemVal.substr(0,10) );
                        break;
                    default:
                        $(this).val( getItemVal );
                        break;
                }
            }else if( getTagName === 'select' ){
                if( getItemVal === undefined && $(this).attr('data-base-data') !== undefined ){
                    renderBaseData($(this), getItemVal, baseData);
                }
            }else{

                if($(this).attr('data-base-data') !== undefined){
                    renderBaseData($(this), getItemVal, baseData);
                }else{
                    $(this).text( getItemVal );
                }

            }

        });

    };
    
    var renderBaseData = function($target, $val, baseData){
        var getDataKey = $target.attr('data-base-data');
        var getTagName = $target[0].tagName.toLowerCase();
        var haveBaseId=$target.attr("data-base-id");//是否用基础数据的id来匹配字段值标示
        if( getTagName === 'select' ){
            var options = [];
            
            if(haveBaseId){
                $.each(baseData[getDataKey], function(index, item) {
                    options.push('<option value="'+item.baseDataId+'">'+item.baseDataName+'</option>');
                });
            }else{
                $.each(baseData[getDataKey], function(index, item) {
                    options.push('<option value="'+item.baseDataCode+'">'+item.baseDataName+'</option>');
                });
            }
            
            $target.html( options.join(''));

            var _getThatToggle = $target.attr('data-toggle');
            if( _getThatToggle !== undefined && _getThatToggle === 'select2' ) $target.select2();
            
        }else{//根据取得的字段值匹配基础数据中的记录

            if(haveBaseId){
                $.each(baseData[getDataKey], function(index, item) {
                    if( $val == item.baseDataId ){
                        $target.text( item.baseDataName );
                        return false;
                    }
                });
            }else{
                $.each(baseData[getDataKey], function(index, item) {
                    if( $val == item.baseDataCode ){
                        $target.text( item.baseDataName );
                        return false;
                    }
                });
            }
            
        }
    };

    var doTransfer = {
        trial: function($o, personID){
            var $modal = $o.modal;
            $.ajax({
                url:"employee/getContractInfoByPersonID",
                type:"post",
                cache:false,
                dataType:"json",
                data:"personId="+personID
            })
            .done(function(d){

                // 验证员工的试用状态
                var personState = {
                    trial: false,
                    message: '无试用期或已转正，请检查该员工的合同信息！'
                };

                if(d.contract){
                    var isTrial = Boolean( d.contract.isTrial*1 );
                    var factPositiveDate = d.contract.factPositiveDate;

                    personState.trial = isTrial && !factPositiveDate;
                    personState.message = (!isTrial || !!factPositiveDate) ? '无试用期或已转正，请检查该员工的合同信息！' : '';
                }

                if( personState.trial ){
                    var contract = d.contract;
                        contract.personID = personID;
                    dataFiller( contract, $o.attrPrefix, d );

                    $modal.modal('show');
                }else{
                    swal({
                        title: personState.message,
                        type: 'info'
                    });
                }
            });

            // 办理转正
            $modal.find('form').formValidation(validOpts[$o.attrPrefix])
            .on('success.form.fv', function(e){
                e.preventDefault();
                var postParams = $(e.target).serializeObject();
                $.ajax({
                    url:"employee/setContractAndBasicInfo",
                    type:"post",
                    cache: false,
                    contentType:"application/json",
                    data: JSON.stringify(postParams),
                    dataType:"json"
                })
                .done(function(d){
                    if(d.result){
                        $modal.modal('hide');
                        swal({
                            title: '人员转正办理成功。',
                            type: 'success'
                        });
                    }
                });
            });

        },
        jobtransfer: function($o, personID){
            var $modal = $o.modal;
            $.ajax({
                url: 'employee/jobpostInfo',
                type: 'post',
                dataType: 'json',
                data:"personId="+personID
            })
            .done(function(d) {
                if(d.ehrPositionList){
                    var ehrPositionList = d.ehrPositionList || {};
                        ehrPositionList.reportPerson ? ehrPositionList.reportPerson = ehrPositionList.reportPerson.employeeName : '';
                        ehrPositionList.organizationName = ehrPositionList.department.organizationName;
                    dataFiller( d.ehrPositionList, $o.attrPrefix, d );
                    var $getDatePicker = $modal.find('[data-init-start-date]');
                        $getDatePicker = {
                            control : $getDatePicker,
                            bSetStartDate: Boolean( $getDatePicker.attr('data-init-start-date')*1 )
                        };
                    if(d.recordStartDate && $getDatePicker.bSetStartDate){
                        var getStartDate = d.recordStartDate.substr(0,10);
                            getStartDate = moment(getStartDate).add(1, 'day').format('YYYY-MM-DD');
                        $getDatePicker.control.datepicker('setStartDate', getStartDate);
                    }

                    $modal.modal('show');
                }
            });

            // 办理岗位调动
            $modal.find('form').formValidation(validOpts[$o.attrPrefix])
            .on('success.form.fv', function(e){
                e.preventDefault();
                var postParams = $(e.target).serializeObject();
                    delete postParams.addNewDepartment;
                    postParams = JSON.stringify(postParams);
                $.ajax({
                    url:"employee/addJobpost",
                    type:"post",
                    cache:false,
                    contentType:"application/json",
                    data: postParams,
                    dataType:"json"
                })
                .done(function(d){
                    if(d.success){
                        $modal.modal('hide');
                        swal({
                            title: d.msg,
                            type: 'success'
                        }, function(){
                            location.reload();
                        });
                    }
                });
            });
            
        },
        dimission: function($o, personID){
            var $modal = $o.modal;

            // 获取员工基本信息
            $.ajax({
                url: 'employee/dimissionInfo',
                type: 'post',
                dataType: 'json',
                data:"personId="+personID
            })
            .done(function(d){
                // 验证员工的离职状态
                var personState = {
                    dimission: true,
                    message: '员工已办理离职，请检查该员工的在职状态！'
                };

                if(d.dimission){
                    var isDimission = d.isDimission;

                    personState.dimission = isDimission;
                    personState.message = isDimission ? '员工已办理离职，请检查该员工的在职状态！' : '';
                }

                if( !personState.dimission ){
                    dataFiller( d.dimission, $o.attrPrefix, d );

                    var $getDatePicker = $modal.find('[data-init-start-date]');
                        $getDatePicker = {
                            control : $getDatePicker,
                            bSetStartDate: Boolean( $getDatePicker.attr('data-init-start-date')*1 )
                        };
                    if(d.recordStartDate && $getDatePicker.bSetStartDate){
                        var getStartDate = d.recordStartDate.substr(0,10);
                            getStartDate = moment(getStartDate).add(1, 'day').format('YYYY-MM-DD');
                        $getDatePicker.control.datepicker('setStartDate', getStartDate);
                    }
                    
                    $modal.modal('show');
                }else{
                    swal({
                        title: personState.message,
                        type: 'info'
                    });
                }

            });

            // 办理离职
            $modal.find('form').formValidation(validOpts[$o.attrPrefix])
            .on('success.form.fv', function(e){
                e.preventDefault();
                var postParams = $(e.target).serializeObject();
                $.ajax({
                    url:"employee/addDimission",
                    type:"post",
                    cache:false,
                    contentType:"application/json",
                    data: JSON.stringify(postParams),
                    dataType:"json"
                })
                .done(function(d){
                    if(d.success){
                        $modal.modal('hide');
                        swal({
                            title: d.msg,
                            type: 'success'
                        });
                    }else{
                        swal({
                            title: d.msg,
                            type: 'warning'
                        });
                    }
                });
            });
            
        }
    };

    // formValidation validation options
    var validOpts = {
        trial: {
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
                factPositiveDate: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        },
                        date: {
                            format: 'YYYY-MM-DD',
                            message: '该日期是无效的'
                        }
                    }
                }
            }
        },
        jobtransfer: {
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
                addNewDepartment: {
                    validators: {
                        notEmpty: {
                            message: '请选择新部门'
                        }
                    }
                }
            }
        },
        dimission: {
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
                dimissionDate: {
                    validators: {
                        notEmpty: {
                            message: '请选择必填项'
                        }
                    }
                },
                salarySettleDate: {
                    validators: {
                        notEmpty: {
                            message: '请选择必填项'
                        }
                    }
                },
                dimissionCause: {
                    validators: {
                        notEmpty: {
                            message: '请选择必填项'
                        }
                    }
                }
            }
        }
    };


    $('.input-group.date')
    .datepicker({
        format: "yyyy-mm-dd",
        autoclose: true
    })
    .on('changeDate', function(e){
        var getEleName = $(e.target).find(':text').attr('name');
        $(this).parents('.fv-form')
        .formValidation('revalidateField', getEleName);
    });


    // open zTree
    var setting = {
        view: {
            dblClickExpand: false,
            showLine:false
        },
        check: {
            enable: true
        },
        callback: {
            onClick: function(event, treeId, treeNode){
                if(!treeNode.authorized) return false;
                var $modal = $('[data-modal="organization"]'),
                $sourceDepartment = $(domTreeRelatedTarget).parent().siblings('.form-control'),
                $departmentId = $(domTreeRelatedTarget).parents('.input-group').siblings('[data-department-id]');
                $modal.modal('hide');
                $sourceDepartment.val( treeNode.name );
                $departmentId.val(treeNode.id);

                var getEleName = $sourceDepartment.attr('name');
                var $fv = $sourceDepartment.parents('.fv-form');
                $fv.formValidation('revalidateField', getEleName);
            }
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        treeNode:{
            nocheck:true
        }
    };

    var loadData = function(targetDom){
        //组织架构树引用json
        var personId = $('[data-modal="organization"]').data('itemsId');
        $.ajax({
            url:"system/loadPersonTree",
            type : "POST",
            dataType:'json',
            data:{personId:personId}
        })
        .done(function(d){
            var zNodes = $.map(d.data, function(item, index){
                return {
                    id: item.organizationId,
                    pId: item.parentId,
                    name: item.organizationName,
                    code: item.organizationCode,
                    memo: item.memo,
                    authorized: item.authorized? true:false,
                    open: true
                };
            });
            $.fn.zTree.init($(targetDom), setting, zNodes);

        });
    };
    var domTreeRelatedTarget = null;
    $('[data-modal="organization"]').on('show.bs.modal', function(e){
        // e.relatedTarget
        domTreeRelatedTarget = e.relatedTarget;
        var getTargetDom = $('#orgTree');
        loadData(getTargetDom);
    });


    var timer = setInterval(function(){
        if(w.renderReady){
            evtManuallyModal();
            

            clearInterval(timer);

        }
    }, 1000);
})(window, jQuery);