// yuangongluru.js
;(function(w, $){
    'use strict';

    var getQueryString = function(name){
        var reg = new RegExp('(^|&)'+ name +'=([^&]*)(&|$)');
        var queryString = window.location.search.substr(1).match(reg);
        if( queryString !== null ) return unescape(queryString[2]);
        return null;
    };

    var requestParams = {
        companyId: getQueryString('companyId'),
        mobile: getQueryString('mobile')
    };

    var validOpts = {
        person: {
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
                employeeName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                nationality: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                sex: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: '请输入中国区域的手机或电话号码'
                        },
                        phone: {
                            country: 'CN',
                            message: '请输入中国区域的手机或电话号码'
                        }
                    }
                },
                email: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                education: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                degree: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                certType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                certId: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                registerType: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                registerLocation: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                salaryCard: {
                    validators: {
                        notEmpty: {
                            message: '请输入有效的银行卡号'
                        },
                        integer: {
                            message: '请输入有效的银行卡号'
                        }
                    }
                },
                bankName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                subbank: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                }
            }
        },
        experience: {
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
                startDate: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        },
                        date: {
                            format: 'YYYY-MM-DD',
                            message: '该日期是无效的'
                        }
                    }
                },
                endDate: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        },
                        date: {
                            format: 'YYYY-MM-DD',
                            message: '该日期是无效的'
                        }
                    }
                },
                company: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                workPost: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                }
            }
        },
        education: {
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
              entranceDate: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      },
                      date: {
                        format: 'YYYY-MM-DD',
                        message: '该日期是无效的'
                      }
                  }
              },
              graduateDate: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      },
                      date: {
                        format: 'YYYY-MM-DD',
                        message: '该日期是无效的'
                      }
                  }
              },
              graduateSchool: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      }
                  }
              },
              learnType: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      }
                  }
              },
              schoolTime: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      }
                  }
              },
              major: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      }
                  }
              },
              degree: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      }
                  }
              },
              education: {
                  validators: {
                      notEmpty: {
                          message: '请填写必填项目'
                      }
                  }
              }
          }
        },
        family: {
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
                userName: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                relation: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                workUnit: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        }
                    }
                },
                phone: {
                    validators: {
                        notEmpty: {
                            message: '请填写必填项目'
                        },
                        phone: {
                            country: 'CN',
                            message: '请输入有效的手机或电话号码'
                        }
                    }
                }
            }
        }
    };


    /**
     * [getVisibleState 获取对象的显示状态，检查 class 中是否包含 hidden]
     * @param  {jQuery Object} $ele [jQuery DOM 对象]
     * @return {Object}      [获取到的结果]
     */
    var getVisibleState = function($ele){
        var bState = [
            {strStateType : 'visible', state: false},
            {strStateType : 'visible', state: true}
        ];
        return bState[ $ele.hasClass('hidden') * 1 ];
    };
    /**
     * [setVisibleState 设置对象的显示状态]
     * @param {jQuery Object} $ele   [jQuery DOM 对象]
     * @param {Boolean} bState [显示状态]
     */
    var setVisibleState = function($ele, bState){
        if(bState){
            $ele.removeClass('hidden');
        }else{
            $ele.addClass('hidden');
        }
        return {strStateType : 'visible', state: bState};
    };
    var hasDataAttr = function($ele, attrName){
        return Boolean( $ele.data(attrName) );
    };
    var serializeParam = function(d){
        var r = {};
        var params = [];
        var isPersonInfo = false;
        var personId = 0;
        $.each(d, function(index, val){
            if( val.name === 'approvedStatus') isPersonInfo = true;
            if( val.name === 'personId' && !Boolean( personId * 1 ) ) personId = val.value;
            if( r.hasOwnProperty( val.name )){
                params.push(r);
                r = {};
            }
            r[val.name] = val.value;
            if( val.name === 'personId') delete r.personId;
        });
        params.push(r);
        if(isPersonInfo){
            params[0].personId = personId;
            return params[0];
        }
        r = {};
        return {
            personId: personId,
            dataList: params
        };
    };
    var validator = function($ele, containerName){
        var $theForm     = $('[data-form="'+ containerName +'"]');
        var getUpdateUrl = $theForm.data('formAction');

        $theForm.formValidation(validOpts[containerName])
        .on('success.form.fv', function(e){
            e.preventDefault();
            var $form        = $(e.target);
            var $button      = $form.data('formValidation').getSubmitButton();
            var getTarget    = $button.attr('data-target'),
                getRelatedTarget = $button.attr('data-relatedTarget');
            var isLastOne = getTarget.indexOf('index') !== -1? true : false ;
            getTarget        = $(getTarget);
            getRelatedTarget = $(getRelatedTarget);

            var postData = serializeParam( $form.serializeArray() );
           // postData.personId=1202;
            // do save
           $.ajax({
                url: getUpdateUrl,
                type: 'POST',
                dataType: 'json',
                contentType:"application/json",
                data: JSON.stringify(postData)
            })
            .done(function(d){
                if( !Boolean( d.resultCode * 1 ) ){
                    swal({
                        title: '数据保存成功',
                        text: '',
                        type: 'success',
                        showCancelButton: !isLastOne,
                        cancelButtonText: '返回首页',
                        confirmButtonText: isLastOne? '确定' :'继续填写',
                        animation: 'slide-from-bottom'
                    }, function(isConfirm){
                        if(isConfirm){
                            // $button.trigger('click.switch');
                            setVisibleState( getTarget, true );
                            setVisibleState( getRelatedTarget, false );
                            fillData( getTarget );
                        }else{
                            setVisibleState( $('[data-container=index]'), true );
                            setVisibleState( getRelatedTarget, false );

                        }
                        
                    });
                }

            });
            
        });
    };
    var fillBaseData = function($ele, baseData){
        var $theForm          = $('[data-form="'+ $ele.data('container') +'"]');
        var getBaseDataFields = $theForm.find('[data-base-data]');
        getBaseDataFields.each(function(index, el){
            var getTagName = el.tagName.toLowerCase();
            if(getTagName === 'select'){
                var options = [];
                $.each(baseData[el.name], function(key, val) {
                    options.push('<option value="'+key+'">'+val+'</option>');
                });
                $(el).html( options.join('') );
            }
        });
    };
    var fillOriginalDate = function($ele, originalData){
        var getDataLength = originalData.length;
        var getEditorArea = $ele.find('[data-inser-id]');
        var containerName = $ele.data('container');
        var fieldKey = containerName+'Id';
        var dataList = originalData.dataList;
        if(originalData.baseData.hasOwnProperty('personId') ){
            if( originalData.dataList.length === 0 ){
                dataList[0]= {
                    personId : originalData.baseData.personId
                };
            }else{
                dataList = $.map(dataList, function(item, index){
                    item['personId'] = originalData.baseData.personId;
                    return item;
                });
            }
        }
        
        $.map(dataList, function(item, index){
        	
            getEditorArea.eq(index).attr('data-field-val', item[fieldKey]);
            $.each(item, function(key, val){
                getEditorArea.eq(index).find('[name='+key+']').val(val);
                if(key=="personId"){
                	  $('[data-container="tpl-'+containerName+'"] :input[name="personId"]').val(val);
                }
                
            });
        });
        
        
    };
    var hasMultipleRecord = function($ele, containerName, dataLength){
    	
        var $theForm    = $('[data-form="'+ containerName +'"]');
        var hasMultiple = $theForm.find('.panel-header').hasClass('add-more');
        var originalDOM = $theForm.find('.panel-body');
            originalDOM.attr('data-field', containerName)
                        .attr('data-field-val', '');
        var slideTitle  = originalDOM.find('.slide-title');
        var getContainerTpl = $('[data-container=tpl-'+containerName+']');
       
        if( getContainerTpl.length < 1 ){
            var getDOM  = originalDOM.clone()
                        .addClass('hidden')
                        .attr('data-container', 'tpl-'+ containerName)
                        .attr('data-list-num', 1);
            originalDOM.attr('data-inser-id', (Math.random().toString().slice(2)) * 1 );
           
            if( hasMultiple ){
            	
                // insert template
                $ele.find('.board').after(getDOM);
                slideTitle.text( slideTitle.text().replace('{num}', 1) );

                for (var i = 0; i < dataLength-1; i++){
                    $theForm.find('.add-more .btn').trigger('click.addmore');
                }
                
            }
        }
        return hasMultiple;
    };
    var fillData = function($ele){
        if( hasDataAttr($ele, 'requestUrl') ){
            var getContainer  = $ele.data('container');
            var getRequestUrl = $ele.data('requestUrl');
            var $theForm = $('[data-form="'+ getContainer +'"]');
            $theForm.formValidation('destroy');
            $.ajax({
                url: getRequestUrl,
                type: 'GET',
                dataType: 'json',
                contentType:'application/json',
                // data: JSON.stringify(requestParams),
                data: requestParams
            })
            .done(function(d){
            	var approvedMsg = ['您当前无需完善信息','已邀请','您提交的信息正在审核中','您的信息已完善']
            	if( d.resultData.dataList && d.resultData.dataList.length!=0&& d.resultData.dataList[0].approvedStatus*1!==1 ){
            		swal({
                        title: approvedMsg[d.resultData.dataList[0].approvedStatus*1],
                        text: '',
                        type: 'warning'
                    }, function(){

                            setVisibleState( $('[data-container=index]'), true );
                            setVisibleState( $('[data-container='+getContainer+']'), false );
                        
                    });
            		return false;
            		
            	}
                var dataList = d.resultData.dataList;
                var baseData = d.resultData;
                delete baseData.dataList;
                var originalData = {
                    baseData: baseData,
                    dataList: dataList !== undefined ? dataList : []
                };

                // step 0. 绑定表单验证事件
                validator($ele, getContainer);
                // step 1. 填充基础数据
                fillBaseData($ele, originalData.baseData);
                // step 2. 检查是否可以编辑多条数据，如果是，保存 DOM 便于后续的新增操作
                hasMultipleRecord($ele, getContainer, originalData.dataList.length);
                // step 3. 回填后端数据
                fillOriginalDate($ele, originalData);
            });


        }
    };


    $('[data-toggle="container-switch"]').on('click.switch', function(event) {
        event.preventDefault();
        var getTarget = $(this).attr('data-target'),
            getRelatedTarget = $(this).attr('data-relatedTarget');

        getTarget        = $(getTarget);
        getRelatedTarget = $(getRelatedTarget);

        setVisibleState( getTarget, true );
        setVisibleState( getRelatedTarget, false );
        fillData( getTarget );
    });

    $('.add-more').on('click.addmore', '.btn', function(event){
        event.preventDefault();
        var $theForm = $(this).parents('form');
        var getContainer = $theForm.data('form');
        var insertPoint = $(this).parent('.add-more');
        var getTemplate = $('[data-container=tpl-'+getContainer+']');
       
        var getListNum = getDataListNum( getContainer );
        var getDOM = getTemplate.clone()
                    .removeClass('hidden')
                    .removeAttr('data-container')
                    .removeAttr('data-list-num')
                    .attr('data-inser-id', (Math.random().toString().slice(2)) * 1 );
        
        var slideTitle  = getDOM.find('.slide-title');
        var getSlideMsgTpl = slideTitle.data('msgTpl');
        slideTitle.text( getSlideMsgTpl.replace('{num}', getListNum+1) );
        
       
        getDOM.insertBefore(insertPoint);
        setDataListNum( getContainer, getListNum+1);

        $('.input-group.date').datepicker({
            format : "yyyy-mm-dd",
            language : "zh-CN",
            autoclose : true
        });

        $theForm.data('formValidation').resetForm();
//        $theForm.formValidation(validOpts[getContainer]);
        getDOM.find('input[name]').not("input:hidden").each(function(index, el){
        		 
        		$theForm.formValidation('addField', $(el));
        });
         
       
        
    });
    $('[data-form]').on('click', '[data-action="delete"]', function(event){
        event.preventDefault();
        var $theForm = $(this).parents('form');
        var getContainer = $theForm.data('form');
        var getListNum = getDataListNum( getContainer );
        setDataListNum( getContainer, getListNum-1);

        var $this = $(this).parents('[data-inser-id]');
        var $siblings = $this.siblings('[data-inser-id]');
        var getSlideMsgTpl = '';

        var fieldKey = $this.attr('data-field') + 'Id';
        var fieldVal = $this.attr('data-field-val');
        var getPersonId = $theForm.find('[name=personId]').val();
        var param = {};
            param['personId'] = getPersonId;
            param[fieldKey] = fieldVal;
        var getDelUrl = $('[data-container='+getContainer+']').data('deleteUrl');

        var doDelete = function(){
            $siblings.find('.slide-title').each(function(index, el){
                getSlideMsgTpl = $(this).data('msgTpl');
                $(this).text( getSlideMsgTpl.replace('{num}', index+1) );
            });
            $theForm.data('formValidation').resetForm();
            $this.remove();
            if( getDataListNum( getContainer ) < 1 ){
                $theForm.data('formValidation').disableSubmitButtons(true);
            }
        };

        if(fieldVal!==''){
            $.ajax({
                url: getDelUrl,
                type: 'POST',
                dataType: 'json',
                contentType:"application/json",
                data: JSON.stringify( param )
            })
            .done(function(d){
                if( !Boolean( d.resultCode * 1 ) ) doDelete();
            });
        }else{
            doDelete();
        }
        

    });

    var getDataListNum = function(containerName){
        var getTemplate = $('[data-container=tpl-'+containerName+']');
        return getTemplate.attr('data-list-num')*1;
    };
    var setDataListNum = function(containerName, num){
        var getTemplate = $('[data-container=tpl-'+containerName+']');
        getTemplate.attr('data-list-num', num);
        return num;
    };

})(window, jQuery);