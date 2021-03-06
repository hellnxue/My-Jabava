// addAccounts.js
;(function(w, $){
	'use strict';
	window.jabava = window.jabava || {};
	window.jabava['socialAccumulationAccount'] = {};

    var getQueryString = function(name){
        var reg = new RegExp('(^|&)'+ name +'=([^&]*)(&|$)');
        var queryString = window.location.search.substr(1).match(reg);
        if( queryString !== null ) return unescape(queryString[2]);
        return null;
    };
    var accountId = getQueryString('accountId');
	var validOpts = {
		addAccounts: {
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
				accountName: {
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						}
					}
				},
				accountType: {
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						},
						callback: {
							callback: function(value, validator, $field){
								// 账户类型0:企业;1:外包
								var hasPactInfos = true;
								if(value*1 === 1){
									$.post({
										url : 'socialAccumulationAccount/hasPactInfos',
										async : false,
										dataType: 'json'
									})
									.done(function(d){
										if(!d.resultData){
											hasPactInfos = false;
											swal({
												title: '您没有外包服务商，将为您跳转到服务开通页面',
												text: '',
												type: 'warning',
												showCancelButton: true,
												confirmButtonColor: '#dd6b55',
												confirmButtonText: '确定跳转！',
												cancelButtonText: '放弃操作！'
											}, function(isConfirm){
												if(isConfirm){
													location.assign('http://news.163.com');
												}else{
													return {
														valid: false,
														message: '您没有外包服务商'
													};
												}
											});

										}
									});

								}

								return hasPactInfos;
							}
						}
					}
				},
				socialSecurityCode: {
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						},
						integer: {
							message: '请输入有效的社保账号'
						}
					}
				},
				accumulationFundCode: {
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						},
						integer: {
							message: '请输入有效的公积金账号'
						}
					}
				}
			}
		},
		rules: {
			err: {
				container: 'tooltip'
			},
			icon: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			locale: 'zh_CN',
			excluded: [':disabled'],
			fields: {
				cityId: {
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						},
						callback: {
							callback: function(value, validator, $field){
								if( value*1 !== 0 ) return true;
								return false;
							}
						}
					}
				},
				accountTypeChb: {
					validators: {
						callback: {
							callback: function(value, validator, $field){
								if(value==1){
									validator
									.enableFieldValidators('securityRule', $field.prop('checked'))
									.revalidateField('securityRule');
								}
								if(value==2){
									validator
									.enableFieldValidators('accumulationRule', $field.prop('checked'))
									.revalidateField('accumulationRule');
								}
								return true;
							}
						}
					}
				},
				securityRule: {
					enabled: false,
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						}
					}
				},
				accumulationRule: {
					enabled: false,
					validators: {
						notEmpty: {
							message: '请填写必填项目'
						}
					}
				}
			}
		}
	};

	// init account base info
	$.when($.ajax({
		url: 'socialAccumulationAccount/findAccountById',
		type: 'GET',
		dataType: 'json',
		data: {accountId: accountId}
	}))
	.then(function(d){
		var $form = $('[data-form=addAccounts]');
		var $el = {};
		if( !Boolean( d.resultCode*1 ) ){
			$.each(d.resultData.dataList, function(index, item){
				$.each(item, function(key, val){
					$el = $form.find('[data-name='+key+']');

					if( $el.length > 0 ){
						switch($el.attr('data-type')){
							case 'select':
								$el.find('[aria-value='+val+']').removeClass('hidden');
								break;
							default:
								$el.text(val);
								break;
						}
					}
					
				});
			});
		}
	});

	// init policy
	$.when($.ajax({
		url: 'socialAccumulationAccount/findAccountCities',
		type: 'GET',
		dataType: 'json',
		data: {accountId: accountId}
	}))
	.then(function(d){
		var getParent = $('[data-toggle=addTab]').parent('li');
		var policysParams = [];
		if( !Boolean(d.resultCode*1) ){
			$.each(d.resultData.dataList, function(index, item){
				$.each(item, function(key, val){
					addTab( getParent );
					policysParams.push({cityId: key, accountId: accountId});
				});
			});


			var getTabPane = $('.tab-pane');
			var $this = {};
			var $el = {};
			var renderList = function(list){
				var listGroup = [];
				$.each(list, function(index, item){
					listGroup.push( rulesTpl(item) );
				});
				return listGroup;
			};
			var rulesTpl = function(item){
				var getSummary = item.groupName+'：'+item.summary;
				var tpl = '<li class="list-group-item" data-policy-id="'+ item.id +'">';
					tpl += getSummary+'</li>';
				return tpl;
			};
			getTabPane.each(function(index, el) {
				$.get({
					url: 'socialAccumulationAccount/findAccountRuleDetail',
					dataType: 'json',
					async: false,
					data: policysParams[index]
				})
				.done(function(d){
					if( !Boolean(d.resultCode*1) ){
						$.each(d.resultData.dataList[0], function(key, val) {
							$el = $(el).find('[data-name='+key+']');

							if( $el.length > 0 ){
								switch($el.attr('data-type')){
									case 'list':
										$el.empty().append( renderList(val) );
										break;
									default:
										$el.text(val);
										break;
								}
							}
						});
					}
				});
			});

		}
	});
	
	
	var unique = function(timestamp){

		if( Boolean(timestamp) ) return (new Date()).getTime();

		return (Math.random().toString().slice(2)) * 1;

	};
	var serializeParam = function(d){
		var params = {};
		$.each(d, function(index, val) {
			params[val.name] = val.value;
		});
		// return JSON.stringify( params );
		return params;
	};


	var hasDataAttr = function($ele, attrName){
		return Boolean( $ele.data(attrName) );
	};
	var getTabNum = function($storage, $eles){
		var getNum = $eles.length;
		$storage.attr('data-num', getNum);
		return getNum;
	};
	var setTabNum = function($storage, num){
		$storage.attr('data-num', num);
		return num;
	};
	var scrollAction = {
		setFirst: function($scroller){
			var $tabArrow   = {
				prev: $scroller.siblings('.tab-prev'),
				next: $scroller.siblings('.tab-next')
			};

			var $allNextTabs   = $scroller.find('[aria-next]');
			var $allPrevTabs   = $scroller.find('[aria-prev]');
			var $allNormalTabs = $scroller.find('[data-tab-sn]')
								.not('[aria-next], [aria-prev]');

			if( $allNextTabs.length > 0 ){
				$allNextTabs.eq(0).removeAttr('aria-next')
								.removeClass('hidden');
				$allNormalTabs.eq(0).attr('aria-prev', 'true')
								.addClass('hidden');
			}
			

			var nNextItem = $tabArrow['next'].attr('data-next-item');
				nNextItem = (nNextItem - 1)>=0?nNextItem - 1:0;

			$tabArrow['next'].attr('data-next-item', nNextItem);
			if( nNextItem === 0 ) $tabArrow['next'].addClass('disabled');

			var nPrevItem = hasDataAttr( $tabArrow['prev'], 'prevItem' )?
							$tabArrow['prev'].attr('data-prev-item'):0;

			if( $tabArrow['prev'].hasClass('disabled') ){
				$tabArrow['prev'].removeClass('disabled');
			}
			$tabArrow['prev'].attr('data-prev-item', nPrevItem*1+1);
		},
		getFirst: function($scroller){},
		setLast: function($scroller){
			var $tabArrow   = {
				prev: $scroller.siblings('.tab-prev'),
				next: $scroller.siblings('.tab-next')
			};
			var $allNextTabs   = $scroller.find('[aria-next]');
			var $allPrevTabs   = $scroller.find('[aria-prev]');
			var $allNormalTabs = $scroller.find('[data-tab-sn]')
								.not('[aria-next], [aria-prev]');

			if( $allPrevTabs.length > 0 ){
				$allPrevTabs.eq(-1).removeAttr('aria-prev')
								.removeClass('hidden');
				$allNormalTabs.eq(-1).attr('aria-next', 'true')
								.addClass('hidden');
			}


			var nPrevItem = $tabArrow['prev'].attr('data-prev-item');
				nPrevItem = (nPrevItem - 1)>=0?nPrevItem - 1:0;

			$tabArrow['prev'].attr('data-prev-item', nPrevItem);
			if( nPrevItem === 0 ) $tabArrow['prev'].addClass('disabled');

			var nNextItem = hasDataAttr( $tabArrow['next'], 'nextItem' )?
							$tabArrow['next'].attr('data-next-item'):0;

			if( $tabArrow['next'].hasClass('disabled') ){
				$tabArrow['next'].removeClass('disabled');
			}
			$tabArrow['next'].attr('data-next-item', nNextItem*1+1);


		},
		getLast: function(){}
	};
	var scrollSpy = function($ele, SN){
		var isDeleted   = hasDataAttr($ele, 'tabSn');
		var getScroller = $ele.parent('.nav-tabs');
		var getItems    = $ele.siblings('li');
		var $tabArrow   = {
			prev: getScroller.siblings('.tab-prev'),
			next: getScroller.siblings('.tab-next')
		};
		var _getWidth = {
				lastItem : $('[data-tab-sn='+SN+']'),
				last : $('[data-tab-sn='+SN+']').width()
			},
			getWidth = {
				last : _getWidth.last,
				length : getItems.length
			};
		if( hasDataAttr( getScroller, 'width' ) ){
			_getWidth.scroller = getScroller.data('width')*1;
			_getWidth.items    = getScroller.attr('data-items-width')*1;
		}else{
			_getWidth.scroller = getScroller.outerWidth();
			_getWidth.items    = $ele.width();
		}
		getWidth.scroller = _getWidth.scroller;
		getWidth.items    = _getWidth.items + _getWidth.last;

		if( getWidth.scroller < getWidth.items ){
			getScroller.addClass('scroll-mode')
						.css('width', getWidth.items - $ele.outerWidth());
		}

		if( getWidth.scroller < getWidth.items - $ele.outerWidth() ){
			_getWidth.lastItem.attr('aria-next', 'true').addClass('hidden');

			if( $tabArrow['next'].hasClass('disabled') ){
				$tabArrow['next'].removeClass('disabled');
			}
			var nNextItem = hasDataAttr( $tabArrow['next'], 'nextItem' )?
							$tabArrow['next'].attr('data-next-item'):0;
			$tabArrow['next'].attr('data-next-item', nNextItem*1+1);

		}
		getScroller.attr('data-width', getWidth.scroller)
					.attr('data-items-width', getWidth.items)
					.attr('data-last-width', getWidth.last);

	};
	var rulesFormAction = {
		getAccountId : function(){
			if( window.jabava.socialAccumulationAccount.accountId ) return window.jabava.socialAccumulationAccount.accountId;
			return null;
		},
		setAccountId : function($form, id){
			$form.find('[name=accountId]').val(id);
		},
		emptyRuleArea : function($form){
			var _this = this;
			$form.find('[data-policy-type]').each(function(index, el) {
				$(this).find('.list-group').empty();
				$(this).siblings('input:hidden').val('');
			});
		},
		setProvince : function($form){
			var _this = this;
			var $province = $form.find('[name=provinceId]');
			var getProvinces = ['<option value="0">-- 省 --</option>'];
			$.get('area/getProvinces', function(d) {
				if( !Boolean(d.resultCode*1) ){
					$.each(d.resultData.dataList, function(index, item){
						getProvinces.push('<option value="'+item.provinceId+'">'+item.provinceName+'</option>');
					});
					$province.empty().append( getProvinces.join('') );
				}
			}, 'json');

			$province.on('change', function(event) {
				event.preventDefault();
				_this.setCity($form, $(this).val());
				_this.emptyRuleArea($form);
			});

		},
		setCity : function($form, provinceId){
			var _this = this;
			var $city = $form.find('[name=cityId]');
			var getCity = ['<option value="0">-- 市 --</option>'];
			$.get('area/getCitiesByProvinceId', {'provinceId': provinceId}, function(d) {
				if( !Boolean(d.resultCode*1) ){
					$.each(d.resultData.dataList, function(index, item){
						getCity.push('<option value="'+item.cityId+'">'+item.cityName+'</option>');
					});
					$city.empty().append( getCity.join('') );
				}
			}, 'json');
		},
		setAccountType : function($form){
			var $accountTypeChb = $form.find('[name=accountTypeChb]');
			var $accountType    = $form.find('[name=accountType]');
			// 政策包组合类型：1社保;2公积金
			// 00:均不选;10 选择社保；01选择公积金；11全都选中
			var accountType    = ['00', '01', '10', '11'];
			var getCheckedLen  = 0;
			var getAccountType = accountType[0];
			var _val = 0;
			var setAccountTypeHandler = function(event){
				getCheckedLen = $accountTypeChb.filter(':checked').length;
				switch(getCheckedLen){
					case 0:
						getAccountType = accountType[0];
						$form.find('[data-account-type]').addClass('hidden');
						break;
					case 1:
						_val = $accountTypeChb.filter(':checked').val();
						if( _val*1 === 1) getAccountType = accountType[2];
						if( _val*1 === 2 ) getAccountType = accountType[1];
						getAccountType = accountType[_val];
						$form.find('[data-account-type]').addClass('hidden');
						$form.find('[data-account-type='+_val+']').removeClass('hidden');
						break;
					case 2:
						getAccountType = accountType[3];
						$form.find('[data-account-type]').removeClass('hidden');
						break;
				}
				$accountType.val(getAccountType);
			};
			$accountType.val(getAccountType);
			// 检查社保和公积金选项初始化时是否已被选中
			setAccountTypeHandler();
			// bind event
			$accountTypeChb.on('click', setAccountTypeHandler);
		},
		getRules : function(event){
			var _this              = event.data.$this;
			var $form              = event.data.$theForm;
			var $modal             = $('[data-modal=rules]');
			var getCityId          = $form.find('[name=cityId]').val();
			var getPolicyGroupType = $(this).parents('[data-account-type]').attr('data-account-type');
			var policyType = {
				1: 'securityRule',
				2: 'accumulationRule'
			};
			
			var arrNormalRulesId = _this.getNormalRulesId($(this));

			// get rules
			var policyTpl = {
				platform: [],
				vendor: []
			};
			var listTpl = function(key, item){
				var tpl = '<li><label>';
					tpl += '<input type="checkbox" name="'+key+'" value="'+item.groupId+'" aria-id="'+ item.groupId +'"> ';
					tpl += item.groupName + '：' + item.summary;
					tpl += '</label></li>';
				return tpl;
			};
			var $policy = {
				platform: $('[data-list=platform]'),
				vendor: $('[data-list=vendor]')
			};
			var policys = {
				platform : [],
				vendor   : []
			};

			if( !getCityId || getCityId*1 === 0 ){
				swal({
					title: '请选择参保地',
					text: '',
					type: 'warning'
				});
				return;
			}
			$.get({
				url: 'socialAccumulationAccount/getAccountRules',
				data: {cityId: getCityId, policyGroupType: getPolicyGroupType},
				dataType: 'json'
			})
			.done(function(d){
				if( !Boolean( d.resultCode*1 )){
					policys.platform      = d.resultData.dataList.platform;
					policys.vendor        = d.resultData.dataList.vendor;
					$.each(policys.platform, function(index, item) {
						policyTpl['platform'].push( listTpl('platform', item) );
					});
					$.each(policys.vendor, function(index, item) {
						policyTpl['vendor'].push( listTpl('vendor', item) );
					});

					// merge rules template
					$policy['platform'].empty().append( policyTpl['platform'].join('') );
					$policy['vendor'].empty().append( policyTpl['vendor'].join('') );


					_this.checkedNormalRule( $modal, arrNormalRulesId );

					// show rules
					$modal
					.attr('data-policy-type', policyType[getPolicyGroupType])
					.modal('show');

					// get policy id
					$modal.on('click', '[data-modal-action=confirm]', function(event){
						$modal.modal('hide');
						var getPolicyType = $modal.attr('data-policy-type');
						var $policyInput  = $form.find('[name='+getPolicyType+']');
						var $rulesList    = $form.find('[data-policy-type='+getPolicyType+'] > .list-group');
						var policys       = [];
						var rules         = [];
						var rulesTpl = function($checkbox, policyType){
							var getSummary = $checkbox.parent('label').text();
							var tpl = '<li class="list-group-item" data-policy-id="'+ $checkbox.val() +'">';
								tpl += getSummary+'</li>';
							return tpl;
						};
						$modal.find(':checkbox:checked').each(function(index, el){
							policys.push( $(this).val() );
							rules.push( rulesTpl( $(this), getPolicyType) );
						});
						$policyInput.val(policys.join(','));
						$rulesList.empty().append( rules.join('') );
						$form.formValidation('revalidateField', getPolicyType);
					});

				}
			});
		},
		getNormalRulesId : function($el){
			var getAllRules = $el.find('[data-policy-id]');
			var rulesId = [];
			getAllRules.each(function(index, el){
				rulesId.push( $(this).attr('data-policy-id')*1 );
			});
			return rulesId;
		},
		checkedNormalRule : function($target, arrIds){
			var getAllRules = $target.find('[aria-id]');
			$.each(arrIds, function(index, val){
				$target.find('[aria-id='+val+']').prop('checked', true);
			});
		}
	};
	var rulesFormHandler = function($form){
		// init form
		rulesFormAction.setProvince($form);
		rulesFormAction.setAccountType($form);
		if( window.jabava.socialAccumulationAccount.accountId ){
			rulesFormAction.setAccountId($form, window.jabava.socialAccumulationAccount.accountId);
		}
		$form.on('click.getRule', '[role=textarea]', {$theForm: $form, $this:rulesFormAction}, rulesFormAction.getRules);


		// submit form
		$form.formValidation(validOpts.rules)
		.on('success.form.fv', function(e){
			e.preventDefault();
			if( !rulesFormAction.getAccountId() ){
				swal({
					title: '请先保存账户。',
					text: '',
					type: 'warning'
				});
				return false;
			}

			var requestData              = serializeParam( $(this).serializeArray() );
			requestData.securityCode     = window.jabava.socialAccumulationAccount.securityCode;
			requestData.accumulationCode = window.jabava.socialAccumulationAccount.accumulationCode;
			$.ajax({
				type: 'post',
				url: 'socialAccumulationAccount/addUpdateAccountRule',
				data: requestData,
				dataType: 'json',
			})
			.done(function(d){
				swal({
					title: d.resultMsg,
					text: '',
					type: Boolean(d.resultCode*1)?'warning':'success'
				});
			});
		});

	};
	var loadRulesForm = function($container, SN){

		var tplURI = 'static/tpls/socialaccumulation/detailAccounts.hbt.js?_t='+unique();
		$.get({
			url : tplURI,
			async : false,
			dataType : 'html'
		})
		.done(function(hbtpl){
			hbtpl = $(hbtpl);
			hbtpl.attr('data-tabpanel-sn', SN);
			var $form = hbtpl.find('[data-form=rules]');

			// check active state
			if( $('[data-tab-sn='+SN+']').hasClass('active') ) hbtpl.addClass('active');

			$container.append( hbtpl );
			// 查看详情的时候不需要查询所有的政策包
			// rulesFormHandler($form);
		});
	};

	var addTab    = function($ele){
		var getSN = unique();
		var theTabNum = getTabNum( $ele, $ele.siblings() ) + 1;
		var tabTpl = [];
			tabTpl.push('<li role="presentation" data-tab-sn="'+getSN+'">');
			tabTpl.push('<a href="javascript://" role="tab">');
			tabTpl.push('参保地 <span class="tab-order">'+theTabNum+'</span> ');
			// tabTpl.push('<span class="fa fa-times" data-toggle="removeTab"></span>');
			tabTpl.push('</a></li>');

		var $tab = $(tabTpl.join(''));

		$ele.before(function(){
			return $tab
					.on('click', '[role=tab]', switchTabHandler)
					.on('click', '[data-toggle=removeTab]', removeTabHandler);
		});
		
		setTabNum( $ele, theTabNum );
		if( theTabNum === 1) setCurrent($ele.prev());

		loadRulesForm($('.tab-content'), getSN);
		scrollSpy($ele, getSN);

	};
	var addTabHandler    = function(event){
	};
	var switchTabHandler = function(event){
		event.preventDefault();
		var getSN = $(this).parent('li').data('tabSn');
		setCurrent( $('[data-tab-sn='+getSN+'], [data-tabpanel-sn='+getSN+']') );
	};
	var setCurrent       = function($ele){
		$ele.siblings().removeClass('active');
		$ele.addClass('active');
	};
	var getCurrent       = function($ele){};
	var removeTabHandler = function(event){
		event.preventDefault();
		event.stopImmediatePropagation();
		var getParents        = $(this).parents('[role=presentation]');
		var getSN             = getParents.data('tabSn');
		var getRelSN          = '';
		var getIndex          = $('[role=presentation]').index(getParents);
		var getSiblings       = getParents.siblings().not(':last');
		var getSiblingsLength = getSiblings.length;
		var isCurrent         = getParents.hasClass('active');
		if( isCurrent && getSiblingsLength > 0 ){
			if( getIndex !== 0 ){
				getRelSN = getSiblings.eq(0).data('tabSn');
				setCurrent( getSiblings.eq(0).add('[data-tabpanel-sn='+getRelSN+']') );
			}
			if( getIndex ===0 ){
				getRelSN = getParents.next().data('tabSn');
				setCurrent( getParents.next().add('[data-tabpanel-sn='+getRelSN+']') );
			}
		}
		getParents.add('[data-tabpanel-sn='+getSN+']').remove();
		scrollSpy(getParents, getSN);

		// reorder
		var getTabOrder = $('.tab-order');
		getTabOrder.each(function(index, el) {
			$(this).text(index+1);
		});

	};
	// bind event
	$('[role=presentation]')
	.on('click', '[data-toggle=addTab]', function(event){
		event.preventDefault();
		if($(this).attr('disabled') === 'disabled' ) return;
		var getParent = $(this).parent('li');
		addTab( getParent );
	})
	.on('click', '[role=tab]:not([data-toggle=addTab])', switchTabHandler)
	// remove tab
	.on('click', '[data-toggle=removeTab]', removeTabHandler);
	$('[data-container=tabnv]')
	.on('click', '.tab-prev', function(event) {
		event.preventDefault();
		if($(this).hasClass('disabled')) return false;
		var $scroller = $(this).siblings('.nav-tabs');
		scrollAction.setLast($scroller);

	})
	.on('click', '.tab-next', function(event) {
		event.preventDefault();
		if($(this).hasClass('disabled')) return false;
		var $scroller = $(this).siblings('.nav-tabs');
		scrollAction.setFirst($scroller);
	});


})(window, jQuery);