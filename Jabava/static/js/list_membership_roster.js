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
            var itemsName = $modal.data('itemsName');

            if( itemsId !== undefined ){
                var getAttrPrefix = $modal.data('modal');
                switch(getAttrPrefix){
                    case 'trial':
                        doTransfer.trial( {
                            modal:$modal,
                            attrPrefix : getAttrPrefix
                        }, itemsId ,itemsName);
                        break;
                    case 'jobtransfer':
                        doTransfer.jobtransfer( {
                            modal:$modal,
                            attrPrefix : getAttrPrefix
                        }, itemsId,itemsName );
                        break;
                    case 'dimission':
                        doTransfer.dimission( {
                            modal:$modal,
                            attrPrefix : getAttrPrefix
                        }, itemsId ,itemsName);
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
        trial: function($o, personID,personName){
            var personIDAry=personID;
            var personNameAry=personName;
            var $modal = $o.modal;
            var personJSON=JSON.stringify({personIdList: personIDAry});
            
            $.ajax({
                url:"employee/getContractInfoByPersonID",
                type:"post",
                contentType:"application/json",
                cache:false,
                dataType:"json",
                data: personJSON
            })
            .done(function(d){

                // 验证员工的试用状态
                var personState = {
                    trial: false,
                    message: '无试用期或已转正，请检查该员工的合同信息！'
                };
                
                var cLength=d.contractList.length;
               
                //无合同信息时，给出提示
                if(cLength==0){
                	swal({
                      title: personState.message,
                      type: 'info'
                  });
                	 
                }else{
                	 
                	var contractAry=d.contractList;
                	
                	//过滤已办理过或者没有试用期的人员，用于友情提示
                	
                	var personIdList=[];//准备办理的人员
                	
                	var employeeNameList=[];//准备办理的人员
                	
                	var contractIdList=[];//准备办理的合同id
                	
                 	var tipPerson=personNameAry.concat();//无试用的人员
                	 
                	$.each(contractAry,function(index,obj){
                	   
//                		if(obj.isTrial=="1"){
                		   personIdList.push(obj.personId);
                		   contractIdList.push(obj.contractID);
                		   employeeNameList.push(obj.employeeName);
                		   tipPerson.splice(tipPerson.indexOf(obj.employeeName),1);
//                		}else{
//                		   tipPerson.push(obj.employeeName);
//                		}
                	});
                	
                	//筛选出的无试用期的给出提示
                	if(personIDAry.length!=personIdList.length){
                		swal({
                            title:tipPerson.join(",")+" 无试用期或已转正，请检查该员工的合同信息！",
                            type: 'info'
                        });
                	}
                	//转正数据准备
                	if(personIdList.length!=0){
                		
                		var contract={
                				personIdList:personIdList.join(","),
                				contractIdList:contractIdList.join(","),
                				name:employeeNameList.join("、 ")
                		};
                		
                		 dataFiller( contract, $o.attrPrefix, d );
                		 
                		 $modal.modal('show');
                	}
                	
                }
                
            });

            // 办理转正
            $modal.find('form').formValidation(validOpts[$o.attrPrefix])
            .on('success.form.fv', function(e){
            	
                e.preventDefault();
                
                var postParams = $(e.target).serializeObject();
                
                 var personIdList= $.map(postParams.personIdList.split(","),function(item,key){
                	 
                     return Number(item);
                  });
                 
                 var contractIdList= $.map(postParams.contractIdList.split(","),function(item,key){
                	 
                     return Number(item);
                  });
                 
                postParams.personIdList=personIdList;
                
                postParams.contractIdList=contractIdList;
                
               // console.log(JSON.stringify(postParams));
                $.ajax({
                    url:"employee/setContractAndBasicInfo",
                    type:"post",
                    cache: false,
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
                        }, function(){
                            location.reload();
                        });
                    }else{
                    	swal({
                            title: d.msg,
                            type: 'false'
                        });
                    }
                });
            });

        },
        jobtransfer: function($o, personID,personName){
            var $modal = $o.modal;
//            $.ajax({
//                url: 'employee/jobpostInfo',
//                type: 'post',
//                dataType: 'json',
//                data:"personIdList="+personID
//            })
//            .done(function(d) {
//                if(d.ehrPositionList){
//                    var ehrPositionList = d.ehrPositionList || {};
//                        ehrPositionList.reportPerson ? ehrPositionList.reportPerson = ehrPositionList.reportPerson.employeeName : '';
//                        ehrPositionList.organizationName = ehrPositionList.department.organizationName;
//                    dataFiller( d.ehrPositionList, $o.attrPrefix, d );
//                    var $getDatePicker = $modal.find('[data-init-start-date]');
//                        $getDatePicker = {
//                            control : $getDatePicker,
//                            bSetStartDate: Boolean( $getDatePicker.attr('data-init-start-date')*1 )
//                        };
//                    if(d.recordStartDate && $getDatePicker.bSetStartDate){
//                        var getStartDate = d.recordStartDate.substr(0,10);
//                            getStartDate = moment(getStartDate).add(1, 'day').format('YYYY-MM-DD');
//                        $getDatePicker.control.datepicker('setStartDate', getStartDate);
//                    }
//
//                    $modal.modal('show');
//                }
//            });
            
            var jobtransfer={
        			personIdList:personID,
        			employeeName:personName.join("、 "),
        	};
            
            $('[data-modal="organization"]').data("itemsId",personID[0]);
        	
    		dataFiller( jobtransfer, $o.attrPrefix, null );
            
            $modal.modal('show');

            // 办理岗位调动
            $modal.find('form').formValidation(validOpts[$o.attrPrefix])
            .on('success.form.fv', function(e){
                e.preventDefault();
                var postParams = $(e.target).serializeObject();
                
                    delete postParams.addNewDepartment;
                    
                    postParams.newDepartment=Number(postParams.newDepartment);
                    
                    var personIdList= $.map(postParams.personIdList.split(","),function(item,key){
                      	 
                        return Number(item);
                     });
                    
                    postParams.personIdList=personIdList;
                    
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
	                    }else{
	                    	swal({
	                            title: d.msg,
	                            type: 'warning'
	                        });
	                    }
	                });
            });
            
        },
        dimission: function($o, personID,personName){
        	 var personIDAry=personID;
        	 var personNameAry=personName;
             var $modal = $o.modal;
             var personJSON=JSON.stringify({personIdList: personIDAry});
            // 获取员工基本信息
            $.ajax({
                url: 'employee/dimissionInfo',
                type: 'post',
                dataType: 'json',
                contentType:"application/json",
                data:personJSON
            })
            .done(function(d){
            	
//            	 $modal.modal('show');
//                // 验证员工的离职状态
                var personState = {
                    dimission: true,
                    message: '员工已办理离职，请检查该员工的在职状态！'
                };
                
                
                
                var cLength=d.isDimissionPersonList.length;
                
                //无合同信息时，给出提示
//                if(cLength==0){
//                	swal({
//                      title: personState.message,
//                      type: 'info'
//                  });
//                	 
//                }else{
                	 
                	var dimissionAry=d.isDimissionPersonList;
                	//{"personIdList":[1159,1676,1693]}
                	
                	var tipName=[];//已离职的人员
                	
                	var handlerPersonIDAry=personIDAry.concat();
                	
                	var handlerPersonNameAry=personNameAry.concat();
                	
                	$.each(dimissionAry,function(index,obj){
                		
                		handlerPersonIDAry.splice(personIDAry.indexOf(obj.personId),1);
                		
                		handlerPersonNameAry.splice(personNameAry.indexOf(obj.employeeNname),1);
                		
                		tipName.push(obj.employeeNname);
                	});
                	
                	if(tipName.length!=0){
                		swal({
                            title: tipName.join(", ")+' 已离职，不需要办理离职！',
                            type: 'info'
                        });
                	}
                	
                	if(personIDAry.length!=0){
                		var dimission={
                    			personIdList:handlerPersonIDAry,
                    			employeeName:handlerPersonNameAry.join("、 "),
                    	};
                    	
                		dataFiller( dimission, $o.attrPrefix, d );
                		 
                		$modal.modal('show');
                	}
                	
            });

            // 办理离职
            $modal.find('form').formValidation(validOpts[$o.attrPrefix])
            .on('success.form.fv', function(e){
                e.preventDefault();
                var postParams = $(e.target).serializeObject();
                
                var personIdList= $.map(postParams.personIdList.split(","),function(item,key){
               	 
                    return Number(item);
                 });
                
                postParams.personIdList=personIdList;
                postParams.dimissionCause=Number(postParams.dimissionCause);
                
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
                        }, function(){
                            location.reload();
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
            	positiveDate: {
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
                },
                newDate: {
                    validators: {
                        notEmpty: {
                            message: '请选择必填项'
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
    var key = $("#key")
    var setting = {
            view: {
                dblClickExpand: false,
                showLine:false,
                fontCss: getFontCss
            },
            check: {
                enable: false
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
    key.bind("focus", focusKey)
        .bind("blur", blurKey)
        .bind("propertychange", searchNode)
        .bind("input", searchNode);
    function focusKey(e) {
        if (key.hasClass("empty")) {
            key.removeClass("empty");
        }
    }
    function blurKey(e) {
        if (key.get(0).value === "") {
            key.addClass("empty");
        }
    }
    var lastValue = "", nodeList = [], fontCss = {};
    function searchNode(e) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree");
        var value = $.trim(key.get(0).value);
        var keyType = "";
        if ($("#name").attr("checked")) {
            keyType = "name";
        }
        if (key.hasClass("empty")) {
            value = "";
        }
        if (lastValue === value) return;
        lastValue = value;
        if (value === "") return;
        updateNodes(false);

        if ($("#getNodesByParamFuzzy").attr("checked")) {
            nodeList = zTree.getNodesByParamFuzzy(keyType, value);
        }
        updateNodes(true);

    }
    function updateNodes(highlight) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree");
        for( var i=0, l=nodeList.length; i<l; i++) {
            nodeList[i].highlight = highlight;
            zTree.updateNode(nodeList[i]);
        }
    }
    function getFontCss(treeId, treeNode) {
        return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
    }
    function filter(node) {
        return !node.isParent && node.isFirstNode;
    }
    $('[data-modal="organization"]').on('hide.bs.modal', function(e){
        key.val('').addClass('empty');
        lastValue = "", nodeList = [], fontCss = {};
    });
    var timer = setInterval(function(){
        if(w.renderReady){
            evtManuallyModal();
            

            clearInterval(timer);

        }
    }, 1000);
})(window, jQuery);