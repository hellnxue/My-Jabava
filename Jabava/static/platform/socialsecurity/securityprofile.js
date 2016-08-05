/**
 * 社保档案管理模块
 * @return {[type]} [description]
 */
(function(power){
	var table;

	const _MAIN_TABLE_DOM = "<'row'<'col-sm-6'l><'col-sm-4'f><'col-sm-2'<'toolbar text-right'>>>\
				<'row'<'col-sm-12 table-responsive'tr>>\
				<'row'<'col-sm-5'i><'col-sm-7'p>>";

	$('#wrapper_editor').hide();

	// 操作按钮
	const $confirmSaveProfile = $('#zy-ss-confirm-save-profile');
	const $confirmPayment = $('#zy-ss-confirm-payment');
	const $cancelPayment = $('#zy-ss-cancel-payment');
	const $addSecurity = $('#zy-ss-add-security');
	const $addAllSecurity = $('#zy-ss-add-all-security');

	const $securitySwitch = $('#zy-ss-security-switch');
	const $gongjijinSwitch = $('#zy-ss-gongjijin-switch');
	const $inputHelperSwitch = $('#zy-ss-input-helper-switch');

	const $securityOrgAccountSeletor = $('#zy-ss-security-org-account-selector');
	const $securityTypeSelector = $('#zy-ss-security_type_selector');
	const $gongjijinOrgAccountSeletor = $('#zy-ss-gongjijin-org-account-selector');
	const $gongjijinTypeSelector = $('#zy-ss-gongjijin_type_selector');

	const $securityRulesDetail = $('#zy-ss-security-rules-details');

	const $securityAccount = $('#zy-ss-security-account');
	const $securityBase = $('#zy-ss-security-base');
	const $enterpriseSecurityBase = $('#zy-ss-enterprise-base');

	const $inputHelperEditor = $('#zy-ss-inputhelper-pane');
	const $gongjijinEditor = $('#zy-ss-gongjijin-pane');

	const $securityCreateType = $('#zy-ss-security-create-type'); // 社保档案创建类型 新开 续交
	const $securityStartTime = $('#zy-ss-security-start-time'); // 起缴月
	const $securityActivateTime = $('#zy-ss-security-activate-time'); // 办理月
	const $securityEndTimeStatus = $('#zy-ss-security-end-time-status'); // 停办月状态 缴费 不缴费
	const $securityEndTime = $('#zy-ss-security-end-time'); // 停办月

	const $gongjijinAccount = $('#zy-ss-gongjijin-account');
	const $gongjijinBase = $('#zy-ss-gongjijin-base');
	const $orgGongjijinBase = $('#zy-ss-org-gongjijin-base');
	const $gongjijinCreateType = $('#zy-ss-gongjijin-create-type');
	const $gongjijinStartTime = $('#zy-ss-gongjijin-start-time');
	const $gongjijinActivateTime = $('#zy-ss-gongjijin-activate-time');
	const $gongjijinEndTimeStatus = $('#zy-ss-gongjijin-end-time-status');
	const $gongjijinEndTime = $('#zy-ss-gongjijin-end-time');

	const $paymentBody = $('#zy-ss-supplement-payment');
	const $paymentMonth = $('#zy-ss-payment-month');

	const $inputHelperPane = $('#zy-ss-input-helper-pane');
	const $gongjijnRulesContainer = $('#zy-ss-gongjijin-rules-details');
	
	//--------------------------myself----------------------------------------
	//批量导入部分
	var $bachImportModal=$("#bachImportModal");
	//社保账户 
	var $securityAccountName=$("#security-account-name");
	//社保参数类型
	var $securityAccountRefType=$("#security-account-ref-type");
	
	//公积金账户 
	var $gjjAccountName=$("#gjj-account-name");
	
	//公积金参数类型
	var $gjjAccountRefType=$("#gjj-account-ref-type");
	//--------------------------myself----------------------------------------end

	var dataBuff = {};

	// 产品类型
	const SECURITY_CATEGORY_SECURITY  = 1; // 社保
	const SECURITY_CATEGORY_GONGJIJIN = 2; // 公积金

	// 产品基数类型
	const PRODUCT_BASE_TYPE_INDIVIDUAL = 1; // 个人基数
	const PRODUCT_BASE_TYPE_COMPANY    = 2; // 公司基数

	// 当前使用的社保产品列表
	var currentSecurityConfig    = [];
	var currentGongjijinProducts = [];

	// 可用社保参保规则明细
	var validSocialSecurityRules = {
		securityRules: undefined,
		gongjijinRules:undefined,
		securityDetail:undefined,
		gongjijinDetail:undefined
	};

	var GLB_PRODUCTS = undefined;

	// 社保子项模板
	var SECURITY_RULES_ITEM_TEMPLATE = '<div class="col-xs-4">\
											<div class="form-group">\
												<label for="" class="control-label col-xs-4 zy-ss-rule-name"></label>\
												<div class="col-xs-8">\
													<input name="itemBase" type="text" class="form-control">\
												</div>\
											</div>\
										</div>';

	// 公积金子项模板
	const gongjijinItemTemplate = '<div class="col-xs-4">\
										<div class="form-group">\
											<label for="" class="control-label col-xs-4 gongjijinName">公积金基数：</label>\
											<div class="col-xs-8">\
												<input type="text" class="form-control">\
											</div>\
										</div>\
									</div>';

	// 补缴子项模板
	var ssss = 	'<tr>\
		<td class="col-md-2"><select class="form-control" name="securityItemCode"></select></td>\
		<td><div class="input-group date"><input name="startTime" class="form-control" type="text"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span></div></td>\
		<td><div class="input-group date"><input name="endTime" class="form-control" type="text"><span class="input-group-addon"><i class="glyphicon glyphicon-th"></i></span></div></td>\
		<td><input name="orgFee" class="form-control zy-digital" type="text"></td>\
		<td><input name="personalFee" class="form-control zy-digital" type="text"></td>\
		<td><input name="description" class="form-control" type="text"></td>\
		<td><input name="orgLateFee" class="form-control zy-digital" type="text"></td>\
		<td><input name="personalLateFee" class="form-control zy-digital" type="text"></td>\
		<td>\
		<button type="buttton" class="btn btn-danger" data-action="remove">删除</button>\
		</td>\
		</tr>';

	var HOSPITAL_INFORMATION_TEMPLATE = '<div class="col-xs-4"><div class="form-group">\
			<label for="" class="control-label col-xs-3">定点医院</label>\
			<div class="col-xs-8" style="display:inline">\
			<input type="text" class="form-control" name="hospital" placeholder="请输入定点医院名称">\
			</div>\
			<a class="control-label glyphicon glyphicon-remove col-xs-1 zy-ss-remove-hospital"  style="color:#f00" role="menuitem" tabindex="-1"></a>\
			</div></div>';

	// 社保档案校验字段设置
	function formValidators(){
		var validateOptions = {
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
		        	securityIndividualBase: {
		        		enabled: true,
		                validators: {
		                    notEmpty: {
		                        message: '请填写必填项目'
		                    }
		                }
		            },
		            securityOrgBase: {
		            	enabled: true,
		                validators: {
		                    notEmpty: {
		                        message: '请填写必填项目'
		                    }
		                }
		            },
		            gongjijinIndividualBase: {
		            	enabled: true,
		                validators: {
		                    notEmpty: {
		                        message: '请填写必填项目'
		                    }
		                }
		            },
		            gongjijinOrgBase: {
		            	enabled: true,
		                validators: {
		                    notEmpty: {
		                        message: '请填写必填项目'
		                    }
		                }
		            }
		        }
		};
		$('[data-form="staffdocs"]')
		.on('init.form.fv', function(e, data) {
			var getCheckbox = $('[data-checkbox-target]');
				getCheckbox.each(function(index, el){
					if(!$(this).prop('checked')){
						var getCheckboxTarget = $(this).data('checkboxTarget')
						$('[data-box='+getCheckboxTarget+']').hide();
					}
				});
		})
		.formValidation(validateOptions)
		.on('change','[data-checkbox-target]',function(e){
			var getCheckbox = $(this),
				getCheckboxTarget = getCheckbox.data('checkboxTarget'),
				getBox = $('[data-box='+getCheckboxTarget+']'),
				fv = $('[data-form="staffdocs"]').data('formValidation');

			if(getCheckbox.prop('checked')){
				$('[data-form="staffdocs"]').formValidation('resetForm');
				getBox.show();
				switch(getCheckboxTarget){
					case 'paySocialSecurity':
						fv
						.enableFieldValidators('securityIndividualBase',true)
						.revalidateField('securityIndividualBase')
						.enableFieldValidators('securityOrgBase',true)
						.revalidateField('securityOrgBase');
						break;
					case 'payProvidentFund':
						fv
						.enableFieldValidators('gongjijinIndividualBase',true)
						.revalidateField('gongjijinIndividualBase')
						.enableFieldValidators('gongjijinOrgBase',true)
						.revalidateField('gongjijinOrgBase');
						break;
				}
			} else{
				$('[data-form="staffdocs"]').formValidation('resetForm');
				switch(getCheckboxTarget){
					case 'paySocialSecurity':
						fv
						.enableFieldValidators('securityIndividualBase',false)
						.revalidateField('securityIndividualBase')
						.enableFieldValidators('securityOrgBase',false)
						.revalidateField('securityOrgBase');
						break;
					case 'payProvidentFund':
						fv
						.enableFieldValidators('gongjijinIndividualBase',false)
						.revalidateField('gongjijinIndividualBase')
						.enableFieldValidators('gongjijinOrgBase',false)
						.revalidateField('gongjijinOrgBase');
						break;
				}
				getBox.hide()
			};
		})
		.on('success.form.fv',function(e){
			e.preventDefault();
			_onSaveProfile();
		})
	}

	const $securityProfileForm = $('#zy-ss-security-profile-form');

	var securityProfile = {};

	$(function (){
		_init()
	});

	function _onRemoveSupplementPayment(){
		$(this).parent().parent().remove();
	}
	
	function validateDigitalField() {
		var digitalVal = $(this).val();
		var z= /^[0-9]*$/;
		if(!z.test(digitalVal)){
			$(this).val(0);
		}
	}

	/**
	 * 校验社保公积金基数
	 * @parem base 当前基数
	 * @param fee  产品类型     1 社保 2 公积金
	 * @param type 产品基数类型 1 个人 2 企业
	 * @private
	 */
	function _varifyBaseFee(base,fee,type){
		var products = (fee == SECURITY_CATEGORY_SECURITY) ?  currentSecurityConfig: currentGongjijinProducts;

		var result = {
			failed: false,
			product:{}
		};

		for(var idx = 0; idx < products.length; idx ++){
			var product = products[idx];

			console.log("================== 目标产品 =================");
			console.log(product);

			var field = (type == PRODUCT_BASE_TYPE_INDIVIDUAL) ? product.individual_base_scope : product.company_base_scope;

			// 同时校验基数的上限和下限
			if(field != undefined){
				var tuples = field.split('~');
				var sub = parseFloat(tuples[0]);
				var sup = parseFloat(tuples[1]);
				
				if(base < sub || base > sup){
					result.failed = true;
					result.product = product;
					return result;
				}
			}
		}

		return result;
	}

	function _init(){
		// $securityProfileForm.formValidation(validators);
		formValidators();

		$('[data-toggle=select2]').select2();

		$('#projectTable').on('click','.zy-ss-edit-button',_onEdit);
		// $confirmSaveProfile.on('click',_onSaveProfile);
		$confirmPayment.on('click',_onSavePayment);
		$cancelPayment.on('click',_toProfileList);
		$addSecurity.on('click',_onAddSecurity);
		$addAllSecurity.on('click',_onAddAllSecurity);

		$securitySwitch.on('click',_onSwicthSecurity);
		$gongjijinSwitch.on('click',_onSwitchGongjijin);
		$inputHelperSwitch.on('click',_onSwitchInputHelper);

		// 选中社保企业账号
		$securityOrgAccountSeletor.on('change',function(){
			var account = $(this).val();

			securityProfile.securityOrgAccount = account;
			// 刷新社保企业账号，并更新政策包,未设置政策包默认值
			_updateSecurityAccountList(account,undefined);
		});

		$securityTypeSelector.on('change:once',_onSelectSecurityType);
		$gongjijinOrgAccountSeletor.on('change',function(){
			var account = $(this).val();
			securityProfile.gongjijinOrgAccount = account;



			// 刷新公积金企业账号，并更新政策包,未设置政策包默认值
			_updateGongjijinAccountList(account,undefined);
		});

		$paymentBody.on('click','button',_onRemoveSupplementPayment);
		$paymentBody.on('keyup','.zy-digital',validateDigitalField);

		$securityAccount.on('keyup',function(){
			securityProfile.securityAccount = $(this).val();
		});

		$securityCreateType.on('change',function(){
			var createType = $(this).val();
			securityProfile.securityCreateType = createType;
		});

		$securityStartTime.on('change',function(){
			var startTime = $(this).val();


			var activateTime = $securityActivateTime.val();
			var endTime = $securityEndTime.val();

			if($.trim(activateTime) != "" && startTime > activateTime){
				swal("起缴月不可大于办理月!","","error");
				$(this).val("");
				return;
			}

			if($.trim(endTime) != "" && startTime > endTime){
				swal("起缴月不可大于停缴月!","","error");
				$(this).val("");
				return;
			}

			securityProfile.securityStarttime = startTime;
		});

		$('.input-group.date').on('changeDate', function(e) {
		// $securityActivateTime.on('change',function(){
			// var activateTime = $(this).val();
			var activateTime = $(e.target).find(':text').val()
			var startTime = $securityStartTime.val();
			var endTime = $securityEndTime.val();

			console.log(e,startTime, endTime, activateTime)


			if($.trim(startTime) != "" && activateTime < startTime){
				swal("办理月不可小于起缴月!","","error");
				$(this).val('');
				return;
			}

			if($.trim(endTime) != "" && activateTime > endTime){
				swal("办理月不可大于停缴月!","","error");
				$(this).val('');
				return;
			}

			securityProfile.securityActivatetime = activateTime;
		});

		$securityEndTimeStatus.on('change',function(){
			var endTime = $(this).val();
			securityProfile.securityEndtimeStatus = endTime;
		});

		$securityEndTime.on('change',function(){
			var endTime = $(this).val();
			var startTime = $securityStartTime.val();
			var activateTime = $securityActivateTime.val();


			console.log("============== security end time like this ===========");
			console.log(">>>>>>");

			if($.trim(endTime) == ""){
				securityProfile.securityEndtime = $.trim(endTime);
				return;
			}

			if($.trim(startTime) != "" && endTime < startTime){
				swal("停缴月不可小于起缴月!","","error");
				$(this).val('');
				return;
			}

			if($.trim(activateTime) != "" && endTime < activateTime){
				swal("停缴月不可小于办理月!","","error");
				$(this).val('');
				return;
			}

			securityProfile.securityEndtime = endTime;
		});

		$securityBase.on('blur',function(){
			var base = $(this).val();
			securityProfile.securityBase = base;

			// 校验个人社保基数范围
			var result = _varifyBaseFee(
				parseFloat(base),
				SECURITY_CATEGORY_SECURITY,
				PRODUCT_BASE_TYPE_INDIVIDUAL);

			if(result.failed){
				var product = result.product;
				var msg = product.policy_group_name+' '
					+ product.prod_name+' '
					+ '个人社保基数范围：￥'
					+ product.individual_base_scope ;

				swal("个人社保基数不在规定范围:",msg,"error");
			}
		});

		$enterpriseSecurityBase.on('blur',function(){
			var base = $(this).val();
			securityProfile.securityOrgBase = base;

			// 校验企业社保基数范围
			var result = _varifyBaseFee(
				parseFloat(base),
				SECURITY_CATEGORY_SECURITY,
				PRODUCT_BASE_TYPE_COMPANY);

			if(result.failed){
				var product = result.product;
				var msg = product.policy_group_name+' '
					+ product.prod_name+' '
					+ '企业社保基数范围：￥'
					+ product.company_base_scope ;

				swal("企业社保基数不在规定范围:",msg,"error");
			}
		});

		$gongjijinAccount.on('keyup',function(){
			var gongjijinAccount = $(this).val();
			securityProfile.gongjijinAccount = gongjijinAccount;
		});

		$gongjijinCreateType.on('change',function(){
			var gongjijinCreateType = $(this).val();
			securityProfile.gongjijinCreateType = gongjijinCreateType;
		});

		$gongjijinTypeSelector.on('change',function(){
			var gongjijinType = $(this).val();
			securityProfile.gongjijinType = gongjijinType;
			_getGongjijinRules(gongjijinType);
		});

		$gongjijinStartTime.on('change',function(){
			var startTime = $(this).val();

			var actvateTime = $gongjijinActivateTime.val();
			var endTime = $gongjijinEndTime.val();

			if($.trim(actvateTime) != "" && startTime > actvateTime){
				swal("起缴月不可大于办理月!","","error");
				$(this).val("");
				return;
			}

			if($.trim(endTime) != "" && startTime > endTime){
				swal("起缴月不可大于停办月!","","error");
				$(this).val("");
				return;
			}

			securityProfile.gongjijinStarttime = startTime;
		});

		$gongjijinActivateTime.on('change',function(){
			var activateTime = $(this).val();

			var startTime = $gongjijinStartTime.val();
			var endTime = $gongjijinEndTime.val();

			if($.trim(startTime) != "" && activateTime < startTime){
				swal("办理月不可小于起缴月!","","error");
				$(this).val("");
				return;
			}

			if($.trim(endTime) != "" && activateTime > endTime){
				swal("办理月不可大于停半月!","","error");
				$(this).val("");
				return;
			}

			securityProfile.gongjijinActivatetime = activateTime;
		});

		$gongjijinEndTime.on('change',function(){
			var endTime = $(this).val();

			var startTime = $gongjijinStartTime.val();
			var actvateTime = $gongjijinActivateTime.val();

			if($.trim(endTime) == ""){
				securityProfile.securityEndtime = $.trim(endTime);
				return;
			}

			if($.trim(startTime) != "" && endTime < startTime){
				swal("停办月不可小于起缴月！","","error");
				$(this).val("");
				return;
			}

			if($.trim(actvateTime) != "" && endTime < actvateTime){
				swal("停办月不可小于办理月！","","error");
				$(this).val("");
				return;
			}
			securityProfile.gongjijinEndtime = endTime;
		});

		$gongjijinEndTimeStatus.on('change',function(){
			var endTimeStatus = $(this).val();
			securityProfile.gongjijinEndtimeStatus = endTimeStatus;
		});

		$gongjijinBase.on('blur',function(){
			var base = $(this).val();
			securityProfile.gongjijinBase = base;


			// 校验企业公积金基数范围
			var result = _varifyBaseFee(
				parseFloat(base),
				SECURITY_CATEGORY_GONGJIJIN,
				PRODUCT_BASE_TYPE_INDIVIDUAL);

			if(result.failed){
				var product = result.product;
				var msg = product.policy_group_name+' '
					+ product.prod_name+' '
					+ '个人公积金基数范围：￥'
					+ product.individual_base_scope ;

				swal("个人公积金基数不在规定范围:",msg,"error");
			}
		});

		$orgGongjijinBase.on('blur',function(){
			var base = $(this).val();
			securityProfile.gongjijinOrgBase = base;

			// 校验企业公积金基数范围
			var result = _varifyBaseFee(
				parseFloat(base),
				SECURITY_CATEGORY_GONGJIJIN,
				PRODUCT_BASE_TYPE_COMPANY);

			if(result.failed){
				var product = result.product;
				var msg = product.policy_group_name+' '
					+ product.prod_name+' '
					+ '企业公积金基数范围：￥'
					+ product.company_base_scope ;

				swal("企业公积金基数不在规定范围:",msg,"error");
			}
		});

		// 编辑社项目子项
		$securityRulesDetail.on('keyup',"input[name=itemBase]",function(e){
			var baseInfo = $(this).val();
			var model = $(this).data("item_base_data");
			model.securityItemBase = baseInfo;
		});

		// 编辑公积金子项
		$gongjijnRulesContainer.on('keyup','input',function(){
			var model = $(this).data('item_base_data');
			model.securityItemBase = $(this).val();
		});

		var lastMonth = '';
		$paymentMonth.on('change',function(){
			var nowMonth = $(this).val();
			if(lastMonth !== nowMonth){
				// 加载已经保存的
				$.ajax({
					type : 'GET',
					url : "employee/supplementPayment/"+ securityProfile.personid+"/"+ nowMonth,
					async : false,
					dataType: "json",
					success : function(result) {
						$paymentBody.empty();

						cut:for(var idy = 0; idy < result.data.length; idy ++){
							var $lineTemplate = $(ssss);
							var tulp = result.data[idy];

							$lineTemplate.find('.input-group.date').datepicker({
								format: "yyyymm",
								language: "zh-CN",
								autoclose: true,
								weekStart: 1,
								startView: 1, maxViewMode: 1,minViewMode:1,
								forceParse: false
							});

							for(var idx = 0; idx < GLB_PRODUCTS.length; idx++){
								var item = GLB_PRODUCTS[idx];
								var $option = $('<option>'+ item.productName +'</option>');

								$option.attr('value',item.securityItemCode);
								$lineTemplate.find('select').append($option);

								$lineTemplate.find('select').data(PRODUCT_ITEM_MODEL_NAME,item);
							}

							$lineTemplate.find('[name=securityItemCode]').val(tulp.securityItemCode);
							$lineTemplate.find('[name=startTime]').val(tulp.startTime);
							$lineTemplate.find('[name=endTime]').val(tulp.endTime);
							$lineTemplate.find('[name=orgFee]').val(tulp.orgFee);
							$lineTemplate.find('[name=personalFee]').val(tulp.personalFee);
							$lineTemplate.find('[name=description]').val(tulp.description);
							$lineTemplate.find('[name=orgLateFee]').val(tulp.orgLateFee);
							$lineTemplate.find('[name=personalLateFee]').val(tulp.personalLateFee);

							$paymentBody.append($lineTemplate);
						}
					}
				});
			}

			lastMonth = $(this).val();
		});

		// 初始化小秘书的显示隐藏
		$inputHelperPane.hide();

		// 初始化日期组件
		$('.input-group.date').datepicker({
			format: "yyyymm",
			language: "zh-CN",
			autoclose: true,
			weekStart: 1, 
			startView: 1,
			maxViewMode: 1,
			minViewMode:1,
			forceParse: false
		});

		// init
		_initSecurityAccount();

		// 加载档案数据
		loadTable();
	}

	/**
	 * 更新社保和公积金企业账号
	 * @return {[type]} [description]
	 */
	function _initSecurityAccount(){
		$.ajax({
			type : 'GET',
			url : "employee/getSecurityAccount",
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					var data = result.data;

					$securityOrgAccountSeletor.empty();
					$securityOrgAccountSeletor.append("<option  value='-1'>请选择</option>");
					$.each(data.security,function(idx,item){
						var $item = $('<option>'+item.socialSecurityAccountName+'</option>');

						$item.attr("value",item.socialSecurityAccountId);
						$securityOrgAccountSeletor.append($item);
					});

					$gongjijinOrgAccountSeletor.empty();
					$gongjijinOrgAccountSeletor.append("<option  value='-1'>请选择</option>");
					$.each(data.gongjijin,function(idx,item){
						var $item = $('<option>'+item.accumulationFundAccountName+'</option>');
						$item.attr("value",item.accumulationFundAccountId);
						$gongjijinOrgAccountSeletor.append($item);
					});
					
					//--------------------------myself----------------------------------------
					//批量导入部分-社保账户初始化
					$securityAccountName.empty();
					$securityAccountName.append("<option  value='-1'>请选择</option>");
					$.each(data.security,function(idx,item){
						var $item = $('<option>'+item.socialSecurityAccountName+'</option>');

						$item.attr("value",item.socialSecurityAccountId);
						$securityAccountName.append($item);
					});
					
					//批量导入部分-公积金账户初始化
					$gjjAccountName.empty();
					$gjjAccountName.append("<option  value='-1'>请选择</option>");
					$.each(data.gongjijin,function(idx,item){
						var $item = $('<option>'+item.accumulationFundAccountName+'</option>');
						$item.attr("value",item.accumulationFundAccountId);
						$gjjAccountName.append($item);
					});
					//--------------------------myself----------------------------------------end
				}else{
					throw new Error("查询公司社保账号失败");
				}
			}
		});
	}

	/**
	 * 展示社保账号下的参保规则信息
	 * @param  {[type]} accountId 社保账号代码
	 */
	function _updateSecurityAccountList(accountId,securityType){
		if(accountId<0){
			return false;
		}
		$.ajax({
			type : 'GET',
			url : "employee/getSecurityRulesByAccount/"+accountId,
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					$securityTypeSelector.empty();
					$securityTypeSelector.append("<option  value='-1'>请选择</option>");
					var items = result.data;
					for(var idx = 0; idx < items.length; idx ++){
						var item = items[idx];
						var $item = $('<option>'+item.sbGroupName+'</option>');
						$item.attr("value",item.id);
						$securityTypeSelector.append($item);
					}

					// 未设置社保方案的情况下，使用第一项
					if(securityType === undefined){
						$securityTypeSelector.val(items[0].id);
						$securityTypeSelector.trigger('change:once');
					}
				}
			}
		});
	}

	function _getGongjijinRules(ruleId){
		$.ajax({
			type : 'GET',
			url : "employee/getSecurityGroupDetail/"+ruleId+'/'+SECURITY_CATEGORY_GONGJIJIN,
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					currentGongjijinProducts = result.data;

					$gongjijnRulesContainer.empty();
					for(var idx = 0; idx < currentGongjijinProducts.length; idx++){
						var item = currentGongjijinProducts[idx];

						var $item = $(gongjijinItemTemplate);
						$item.find('.gongjijinName').text(item.prod_name);
						$item.find('input').val(securityProfile.gongjijinBase);
						$item.find('input').attr("prod_id",item.prod_id);

						var model = {
							securityProfileId:'', // 未设置
							personId:securityProfile.personid,
							securityTypeId: item.sb_group_id, //
							securityTypeCategory: 2,  // 1 社保 2 公积金
							securityItemCode: item.prod_id,
							securityItemBase: securityProfile.gongjijinBase,
							securityOrgBase:5000
						};

						$item.find('input').data('item_base_data',model);

						$gongjijnRulesContainer.append($item);
					}
				}
			}
		});
	}

	/**
	 * 取得参保规则明细
	 * @param  {[type]} ruleId 参保规则代码
	 */
	function _getSecutityRules(ruleId){
		$.ajax({
			type : 'GET',
			url : "employee/getSecurityGroupDetail/"+ruleId+'/'+SECURITY_CATEGORY_SECURITY,
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					$securityRulesDetail.empty();
					currentSecurityConfig = result.data;
					var socialSecurityRules = result.data;

					for(var idx = 0; idx < socialSecurityRules.length; idx++){
						var item = socialSecurityRules[idx];
						var $ruleItem = $(SECURITY_RULES_ITEM_TEMPLATE);
						$ruleItem.find('.zy-ss-rule-name').text(item.prod_name);
						$ruleItem.find('input').val(securityProfile.securityBase);
						$ruleItem.find('input').attr("prod_id",item.prod_id);

						var model = {
							securityProfileId:'', // 未设置
							personId:securityProfile.personid,
							securityTypeId: item.sb_group_id, //
							securityTypeCategory: 1,  // 1 社保 2 公积金
							securityItemCode: item.prod_id,
							securityItemBase: securityProfile.securityBase,
							securityOrgBase:5000
						};

						$ruleItem.find('input').data('item_base_data',model);
						$securityRulesDetail.append($ruleItem);
					}

				}else{
					swal("取得社保明细信息失败","","error");
				}
			}
		});
	}

	/**
	 * 填充实际的社保和公积金明细信息
	 * @private
     */
	function _fillDetailedInfo(){
		// 更新线上的社保明细信息
		var personId = securityProfile.personid;
		$.ajax({
			type : 'GET',
			url : "employee/saveSecurityItems/"+personId,
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				var products = result.data;
				if(result.status == 0){
					for(var idx = 0 ; idx < products.length; idx++){
						var item = products[idx];
						var productID   = item.securityItemCode;
						var productBase = item.securityItemBase;
						var productCategory = item.securityTypeCategory;

						if(productCategory == 1){
							var $input = $securityRulesDetail.find('input[prod_id='+productID+']');
							if($input.length > 0){
								$input.val(productBase);
								var model = $input.data('item_base_data');
								model.securityItemBase = productBase;
							}
						}else if(productCategory == 2){
							var $input = $gongjijnRulesContainer.find('input[prod_id='+productID+']');
							if($input.length > 0){
								$input.val(productBase);
								var model = $input.data('item_base_data');
								model.securityItemBase = productBase;
							}
						}
					}
				}
			}
		});
	}

	function _updateGongjijinAccountList(accountId,gongjijinType){
		if(accountId<0){
			return false;
		}
		$.ajax({
			type : 'GET',
			url : "employee/getFundAccountRulesByAccount/"+accountId,
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					var solutions = result.data;
					if(solutions.length <= 0){
						swal("该企业账号下未设置公积金参保方案，请重新选择！", undefined, "error");
					}else{
						// 复制到公共领域
						if(validSocialSecurityRules.gongjijinDetail === undefined){
							validSocialSecurityRules.gongjijinDetail = solutions;
						}

						$gongjijinTypeSelector.empty();
						$gongjijinTypeSelector.append("<option  value='-1'>请选择</option>");
						for(var idx = 0; idx < solutions.length; idx ++){
							var item = solutions[idx];
							var $item = $('<option>'+item.sbGroupName+'</option>');
							$item.attr('value',item.id);

							$gongjijinTypeSelector.append($item);
						}
						
						if(gongjijinType === undefined){
							$gongjijinTypeSelector.val(solutions[0].id);
							$gongjijinTypeSelector.trigger('change');
						}else{
							$gongjijinTypeSelector.val(gongjijinType);
						}
					}
				}else{
					swal("取得社保规则明细失败!","","error");
				}
			}
		});
	}

	/**
	 * 选择社保类型
	 */
	function _onSelectSecurityType(){
		var securityType = $(this).val();
		securityProfile.securityType = securityType;

		var securityType = $(this).val();
		securityProfile.securityType = securityType;

		// 处理北京地区社保的呈现
		$inputHelperPane.find('select').select2('val',-1);
		if( $(this).text().indexOf('北京') != -1 ){
			$inputHelperPane.show();
		}else{
			$inputHelperPane.hide();
		}

		_getSecutityRules(securityType);
	}

	const $securityEditor = $('#zy-ss-security-pane');

	/**
	 * 设置|关闭社保缴纳
	 * @return {[type]} [description]
	 */
	function _onSwicthSecurity(){
		var checked = $(this).is(':checked');
		if(checked){
			$securityEditor.show();
			securityProfile.securitySwitch = 0;
		}else{
			$securityEditor.hide();
			securityProfile.securitySwitch = 1;
		}
	}

	/**
	 * 设置|关闭公积金缴纳
	 * @return {[type]} [description]
	 */
	function _onSwitchGongjijin(){
		var checked = $(this).is(':checked');
		if(checked){
			$gongjijinEditor.show();
			securityProfile.gongjijinSwitch = 0;
		}else{
			$gongjijinEditor.hide();
			securityProfile.gongjijinSwitch = 1;
		}
	}

	/**
	 * 设置|关闭 是否启用录单小秘书
	 * @return {[type]} [description]
	 */
	function _onSwitchInputHelper(){
		var checked = $(this).is(':checked');
		if(checked){
			$inputHelperEditor.show();
			securityProfile.inputHelpSwitch = 0;
		}else{
			$inputHelperEditor.hide();
			securityProfile.inputHelpSwitch = 1;
		}
	}

	const PRODUCT_ITEM_MODEL_NAME = 'PRODUCT_ITEM_MODEL_NAME';

	/**
	 * 添加社保类型
	 * @return {[type]} [description]
	 */
	function _onAddSecurity(){
		var $lineTemplate = $(ssss);
		$lineTemplate.find('.input-group.date').datepicker({
			format: "yyyymm",
			language: "zh-CN",
			autoclose: true,
			weekStart: 1,
			startView: 1, maxViewMode: 1,minViewMode:1,
			forceParse: false
		});

		for(var idx = 0; idx < GLB_PRODUCTS.length; idx++){
			var item = GLB_PRODUCTS[idx];
			var $option = $('<option>'+ item.productName +'</option>');
			$option.attr('value',item.securityItemCode);
			$lineTemplate.find('select').append($option);

			$lineTemplate.find('select').data(PRODUCT_ITEM_MODEL_NAME,item);
		}

		$paymentBody.append($lineTemplate);
	}

	/**
	 * 添加全部社保类型
	 * @return {[type]} [description]
	 */
	function _onAddAllSecurity(){
		// TODO　添加全部社保类型

		for(var idx = 0; idx < GLB_PRODUCTS.length; idx++){
			var currentItem = GLB_PRODUCTS[idx];
			var $lineTemplate = $(ssss);
			$lineTemplate.find('.input-group.date').datepicker({
				format: "yyyymm",
				language: "zh-CN",
				autoclose: true,
				weekStart: 1,
				startView: 1, maxViewMode: 1,minViewMode:1,
				forceParse: false
			});

			var $select = $lineTemplate.find('select');

			for(var idy = 0; idy < GLB_PRODUCTS.length; idy++){
				var item = GLB_PRODUCTS[idy];
				var $option = $('<option>'+ item.productName +'</option>');
				$option.attr('value',item.securityItemCode);
				$select.append($option);
			}

			$select.data(PRODUCT_ITEM_MODEL_NAME,currentItem);
			$select.val(currentItem.securityItemCode);

			$paymentBody.append($lineTemplate);
		}
	}

	function buildSecurityTypeCombo(){
		$securityRulesDetail.empty();
		for(var idx = 0; idx < currentSecurityConfig.length; idx++){
			var item = currentSecurityConfig[idx];

			var $ruleItem = $(SECURITY_RULES_ITEM_TEMPLATE);
			$ruleItem.find('.zy-ss-rule-name').text(item.prod_name);
			$ruleItem.find('input').val(securityProfile.securityBase);
			$ruleItem.find('input').attr("prod_id",item.prod_id);

			var model = {
				securityProfileId:'', // 未设置
				personId:securityProfile.personid,
				securityTypeId: item.sb_group_id, //
				securityTypeCategory: 1,  // 1 社保 2 公积金
				securityItemCode: item.prod_id,
				securityItemBase: securityProfile.securityBase,
				securityOrgBase: 5000
			};

			$ruleItem.find('input').data('item_base_data',model);
			$securityRulesDetail.append($ruleItem);
		}
	}

	/**
	 * 编辑用户社保档案
	 * @return {[type]} [description]
	 */
	function _onEdit(){
		$('.nav-tabs').children().removeClass('active');
		$('.nav-tabs').find('li:first').addClass('active');
		$('.tab-pane').removeClass('active');
		$('#profileEdit').addClass('active');

		$('#zy-ss-profile-list').hide();
		$('#zy-ss-profile-editor').show();

		var personId = $(this).data('personid');
		securityProfile.personid = personId;
		$('#personIdForPaymentList').val(personId);

		var obj = dataBuff[personId];
	//	$('#zy-ss-base-job-number').text(obj.jobNumber);
		$('#zy-ss-base-name').text(obj.employeeName);
	//	$('#zy-ss-base-organization').text(obj.organizationName);
		$('#zy-ss-base-cert-id').text(obj.certId);
		$('#zy-ss-base-register-type').text(obj.registerType);
		$('#zy-ss-base-entry-date').text(obj.entryDate);

		// 设置补缴信息上的参数数据
		$('#zy-ss-security-rule-name').text(obj.securityOrgAccount);
		$('#zy-ss-gongjijin-rule-name').text(obj.gongjijinOrgAccount);
		$('#zy-ss-user-name').text(obj.employeeName);


		$paymentBody.empty();
		$paymentMonth.val("");

		// 加载个人配置的所有产品,和医院的信息,个人的社保档案信息
		$.ajax({
			type : 'GET',
			url : "employee/getAllProductsByPersonId/" + securityProfile.personid,
			async : false,
			dataType: "json",
			contentType:'application/json',
			success : function(result) {
				GLB_PRODUCTS = result.data;

				var hospital = result.hospital;
				for(var idx = 0; idx < hospital.length; idx ++){
					var item = hospital[idx];
					var $item = $('<option value="'+item.baseDataCode+'">'+item.baseDataName+'</option>');
					$('[name=socialSecurityLocation]').append($item);
				}

				securityProfile = result.profile;

				/////////////////////////////////////////////
				$('#zy-ss-base-job-number').html(securityProfile.employeeNumber);
				$('#zy-ss-base-organization').text(securityProfile.department);

				// 更新社保账号下参保曾策包
				if(securityProfile.securityOrgAccount !== null){
					$securityOrgAccountSeletor.val(securityProfile.securityOrgAccount);
					_updateSecurityAccountList(securityProfile.securityOrgAccount,securityProfile.securityType);
				}

				if(securityProfile.gongjijinOrgAccount !== null){
					$gongjijinOrgAccountSeletor.val(securityProfile.gongjijinOrgAccount);
					_updateGongjijinAccountList(securityProfile.gongjijinOrgAccount,securityProfile.gongjijinType);
				}

				// 更新社保规则,并获取当前可用的社保规则明细
				if(securityProfile.securityType !== null){
					$.ajax({
						type : 'GET',
						url : "employee/getSecurityGroupDetail/"+securityProfile.securityType+'/'+SECURITY_CATEGORY_SECURITY,
						async : false,
						dataType: "json",
						success : function(result, textStatus, jqXHR) {
							if(result.status == 0){
								// 渲染社保下拉框
								currentSecurityConfig = result.data;
								buildSecurityTypeCombo();

								// 设置社保状态
								$securityTypeSelector.val(securityProfile.securityType);

								// 如果社保是在北京需要显示定点医院信息
								$inputHelperPane.find('select').val('');
								if( $securityTypeSelector.text().indexOf('北京') != -1 ){
									//$inputHelperPane.find('select').val('-1');

									$inputHelperPane.find('select').select2('val',-1);

									// 呈现定点医院数据
									$.ajax({
										type : 'GET',
										url : "employee/designatedHospital/"+securityProfile.personid,
										async : true,
										dataType: "json",
										success : function(result, textStatus, jqXHR) {
											var hospitals = result.data;

											var $hlist = $inputHelperPane.find('select');
											$.each($hlist,function(idx,item){
												if(hospitals[idx] != undefined){
													var val = parseInt(hospitals[idx].hospitalName);
													$(item).select2("val", val);
												}else{
													$(item).select2("val", -1);
												}
											});
										}
									});
									$inputHelperPane.show();

								}else{
									$inputHelperPane.hide();
								}
							}else{
								swal("取得社保明细信息失败","","error");
							}
						}
					});
				}

				// 更新公积金规则，并获取当前可用公积金明细
				if(securityProfile.gongjijinType !== null){
					$gongjijinTypeSelector.val(securityProfile.gongjijinType);
					_getGongjijinRules(securityProfile.gongjijinType)
				}

				// 填写实际的业务数据
				_fillDetailedInfo();

				$securityAccount.val(securityProfile.securityAccount);
				$securityBase.val(securityProfile.securityBase);
				$enterpriseSecurityBase.val(securityProfile.securityOrgBase);
				$gongjijinAccount.val(securityProfile.gongjijinAccount);
				$gongjijinBase.val(securityProfile.gongjijinBase);
				$orgGongjijinBase.val(securityProfile.gongjijinOrgBase);

				// 设置录单小秘书编辑状态
				//if(securityProfile.inputHelpSwitch == 0){
				//	$inputHelperPane.show();
				//	$inputHelperSwitch.prop("checked",true);
				//}else{
				//	$inputHelperPane.hide();
				//	$inputHelperSwitch.prop("checked",false);
				//}

				// 设置公积金编辑状态
				if(securityProfile.gongjijinSwitch == 0){
					$gongjijinEditor.show();
					$gongjijinSwitch.prop("checked",true);
				}else{
					$gongjijinEditor.hide();
					$gongjijinSwitch.prop("checked",false);
				}

				// 设置社保编辑状态
				if(securityProfile.securitySwitch == 0){
					$securityEditor.show();
					$securitySwitch.prop("checked",true);
				}else{
					$securityEditor.hide();
					$securitySwitch.prop("checked",false);
				}

				// 设置社保编辑字段
				$securityCreateType.val(securityProfile.securityCreateType);
				$securityStartTime.val(securityProfile.securityStarttime);
				$securityActivateTime.val(securityProfile.securityActivatetime);
				$securityEndTimeStatus.val(securityProfile.securityEndtimeStatus);
				$securityEndTime.val(securityProfile.securityEndtime);
				// 设置公积金编辑字段
				$gongjijinCreateType.val(securityProfile.gongjijinCreateType);
				$gongjijinStartTime.val(securityProfile.gongjijinStarttime);
				$gongjijinActivateTime.val(securityProfile.gongjijinActivatetime);
				$gongjijinEndTimeStatus.val(securityProfile.gongjijinEndtimeStatus);
				$gongjijinEndTime.val(securityProfile.gongjijinEndtime);
				//////////////////////////////////////////////
			}
		});
	}

	/**
	 * 返回社保档案列表页面
	 */
	function _toProfileList(){
		$('#zy-ss-profile-list').show();
		$('#zy-ss-profile-editor').hide();

		loadTable();
	}

	/**
	 * 保存补缴信息
	 */
	function _onSavePayment(){
		// TODO　补缴信息需要添加字段
		var $forms = $paymentBody.find('tr');
		var month = $paymentMonth.val();

		if($.trim(month) == ''){
			swal("请输入补缴办理月信息！","","error");
			return ;
		}

		var arr = [];
		for(var i = 0; i < $forms.length; i++){
			var $form = $($forms[i]);

			var startTime = $form.find('[name=startTime]').val();
			var endTime = $form.find('[name=endTime]').val();

			if(endTime < startTime){
				swal("第"+ (i+1) + '行，补缴起始月大于补缴结束月',"","error");
				return;
			}

			if(endTime > month){
				swal("第"+ (i+1) + '行，补缴结束月大于办理月',"","error");
				return;
			}

			var productModel = $form.find('select').data(PRODUCT_ITEM_MODEL_NAME);
			var obj = {};
			obj['securityTypeId'] = productModel.securityTypeId;
			obj['securityTypeCategory'] = productModel.productCategory;
			obj['securityItemCode'] = $form.find('[name=securityItemCode]').val();
			obj['startTime'] = $form.find('[name=startTime]').val();
			obj['endTime'] = $form.find('[name=endTime]').val();
			obj['orgFee'] = $form.find('[name=orgFee]').val();
			obj['personalFee'] = $form.find('[name=personalFee]').val();
			obj['description'] = $form.find('[name=description]').val();
			obj['orgLateFee'] = $form.find('[name=orgLateFee]').val();
			obj['personalLateFee'] = $form.find('[name=personalLateFee]').val();
			obj['personId'] = securityProfile.personid;
			obj['operateTime'] = month;

			arr.push(obj);
		}

		if(arr.length <= 0){
			swal("请添加补缴信息!!!","","error");
			return;
		}

		$.ajax({
			type : 'POST',
			url : "employee/supplementPayment",
			async : false,
			dataType: "json",
			contentType:'application/json',
			data: JSON.stringify(arr),
			success : function(result, textStatus, jqXHR) {
				swal("保存补缴信息成功！","","success");
			}
		});

		_toProfileList();
	}

	/**
	 * 保存用户参保信息并返回社保档案列表页面
	 */
	// TODO　保存社保档案
	function _onSaveProfile(){
		// $securityProfileForm.data('formValidation').validate();
		// var valid = $securityProfileForm.data('formValidation').isValid();
		// if(!valid){
		// 	console.log("表单字段校验失败");
		// 	return;
		// }

		var obj = {
			profileId: securityProfile.profileId,
			hospitals: []
		};

		var securityEnabled = $securitySwitch.is(":checked");//是否缴纳社保
		if(securityEnabled){
			var $inputs = $inputHelperPane.find('select');
			for(var idx = 0; idx < $inputs.length; idx++){
				var $item = $($inputs[idx]);
				if(!$item.val()) continue;
				
				var model ={
					'hospitalName': $item.val(),
					'personId': securityProfile.personid,
					'profileId':securityProfile.profileId
				};
	
				obj.hospitals.push(model);
			};
		}
		
		if(obj.hospitals.length == 0){	//不交社保或非北京，用于删除定点医院
			var model ={
				'personId': securityProfile.personid,
				'profileId': -1
			};

			obj.hospitals.push(model);
		}
		
		$.ajax({
			type : 'POST',
			url : "employee/designatedHospital",
			async : false,
			dataType: "json",
			contentType:'application/json',
			data: JSON.stringify(obj.hospitals),
			success : function(result, textStatus, jqXHR) {
			}
		});
		
		var itemArray = [];
		
		if(securityEnabled){
			var $container = $('#zy-ss-security-rules-details');
			var $inputs = $container.find('input');
			for(var idx = 0; idx < $inputs.length; idx ++){
				var model = $($inputs[idx]).data('item_base_data');
				itemArray.push(model);
			}
		}

		var gongjijinEnabled = $gongjijinSwitch.is(":checked");
		if(gongjijinEnabled){
			var $gongjijinContainer = $('#zy-ss-gongjijin-rules-details');
			var $gongjijinInputs = $gongjijinContainer.find('input');
			for(var idx = 0; idx < $gongjijinInputs.length; idx ++){
				var model = $($gongjijinInputs[idx]).data('item_base_data');
				itemArray.push(model);
			}
		}

		for(var idx = 0; idx < itemArray.length; idx ++){
			var item = itemArray[idx];
			if(item.securityTypeCategory == 1){
				item.securityOrgBase = parseFloat($('#zy-ss-enterprise-base').val());
			}else{
				item.securityOrgBase = parseFloat($('#zy-ss-org-gongjijin-base').val());
			}
		}

		// 多余的项目的不能留着，否则会有反序列化的问题的
		delete securityProfile['ehrSecurityItems'];
		$.ajax({
			type : 'POST',
			url : "employee/securityProfile",
			async : false,
			data: JSON.stringify(securityProfile),
			dataType: "json",
			contentType:"application/json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					var newProfile = result.data;
					var profileId = newProfile.profileId;

					for(var idx = 0; idx < itemArray.length; idx ++){
						itemArray[idx].securityProfileId = profileId;
					}

					// 保存社保基数信息
					$.ajax({
						type : 'POST',
						url : "employee/saveSecurityItems",
						async : false,
						dataType: "json",
						contentType:'application/json',
						data: JSON.stringify(itemArray),
						success : function(result, textStatus, jqXHR) {
						}
					});

					_toProfileList();
				}else{
					swal("员工社保档案数据保存失败！", undefined, "error");
				}
			}
		});
	}

	function loadTable(params){
		if(table){
			table.destroy();
			dataBuff = {};
		}
		table = $('#projectTable').DataTable({
			"dom": _MAIN_TABLE_DOM,
			"processing": true,
			"serverSide": true,
			"bDestroy": true,
			"ordering": false,
			"sServerMethod": "POST",
			"ajax": {
				"url":"employee/searchSecurityProfile",
				"data": params ? params : {}
			},
			"columns": [
				{ "data": "jobNumber" },
				{ "data": "employeeName" },
				{ "data": "organizationName" },
				{ "data": "securityOrgAccount" },
				{ "data": "gongjijinOrgAccount" },
				{ "data": "certId" },
				{ "data": "registerType" },
				{ "data": "entryDate" },
				//{ "data": "baseSalary"},
				// { "data": "securityStatus" },
				{"render":function(data, type, row, meta){
					var map = {
						'0':'草稿',
						'1':'在缴',
						'2':'停缴'
					}

					return map[row.securityStatus];
				}},
				{ "data": "securityAccount" },
				{ "data": "securityType" },
				{ "data": "securityCity" },
				{"render":function(data, type, row, meta){
					var map = {
						'0':'草稿',
						'1':'在缴',
						'2':'停缴'
					};
					return map[row.gongjijinStatus];
				}},
				{ "data": "gongjijinAccount" },
				{ "data": "gongjijinType" },
				{ "data": "gongjinCity" },
				{ "render": function(data, type, row, meta){
					var _OPERATION_BUTTON = '';
					if(power.securityprofile_mp){
						_OPERATION_BUTTON = '<a class="btn btn-success btn-xs zy-ss-edit-button" \ ' +
						'type="button" data-personid="'+row.person_id+'" href="javascript:void(0)">修改</a>&nbsp;';
					}

					dataBuff[row.person_id] = row;
					return _OPERATION_BUTTON;
				}}
			],
			"columnDefs": [
				{defaultContent: '', targets: '_all'}
			],
			"language": {
				"search": "过滤:",
				"lengthMenu": "每页显示 _MENU_ 条记录",
				"zeroRecords": "暂无数据 - 报歉啦?",
				"info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
				"infoEmpty": "暂无数据",
				"infoFiltered": "(筛选自 _MAX_ 条记录)",
				"paginate":{
					"first":"首页",
					"previous":"前一页",
					"next":"后一页",
					"last":"尾页"
				}
			}
		});
		//--------------------------myself----------------------------------------
		var strHtml = ''
		if(power.securityprofile_im){
			strHtml += '<button id="bachImportBtn" class="btn btn-success btn-sm ">批量导入</button>';
		}
		if(power.securityprofile_ex){
			strHtml += ' <button id="zy-ss-export-sheet" class="btn btn-warning btn-sm">导 出</button>';
		}
		$(".toolbar").html(strHtml);
		//--------------------------myself----------------------------------------end
		$(".toolbar").on('click','#zy-ss-export-sheet',function(){
			window.open('employee/exportSecurityProfile');
		});
		//--------------------------myself----------------------------------------
		//批量导入
		$("#bachImportBtn").on("click",function(){
			 
			$bachImportModal.modal();
			
		});
		//--------------------------myself----------------------------------------end
	}
// 上传
	$('#addForm')[0].reset();

	$('[data-toggle="upload:file"] :file').on('change.file:selected', function(event){
	    var oEventTarget = $(this);
	    var oFile = $(this).val();
	    var fileExt = oFile.substr(oFile.lastIndexOf(".")).toLowerCase();
	
	    if(fileExt!='.xlsx'){
	        alert("请上传后缀名为xlsx的文件!");
	        return;
	    }
	
	    oEventTarget.parents('[data-toggle="upload:file"]').find(':text').val( oFile );
	})
	
	$('[data-toggle="upload:file"]').on('click', function(event){
	    $(this).find(':file').trigger('click.file:selected');
	});
	//--------------------------myself----------------------------------------
	//批量导入部分
	// 选中社保企业账号
	 $securityAccountName.on('change',function(){
		var account = $(this).val();

		securityProfile.securityOrgAccount = account;
		// 刷新社保企业账号，并更新政策包,未设置政策包默认值
		_updateSecurityAccountListBach(account,undefined);
	}); 
	 
	 /**
	  * 展示社保账号下的参保规则信息--批量导入部分
	  * @param  {[type]} accountId 社保账号代码
	  */
	 function _updateSecurityAccountListBach(accountId,securityType){
	 	if(accountId<0){
	 		return false;
	 	}
	 	$.ajax({
	 		type : 'GET',
	 		url : "employee/getSecurityRulesByAccount/"+accountId,
	 		async : false,
	 		dataType: "json",
	 		success : function(result, textStatus, jqXHR) {
	 			if(result.status == 0){
	 				$securityAccountRefType.empty();
	 				$securityAccountRefType.append("<option  value='-1'>请选择</option>");
	 				var items = result.data;
	 				for(var idx = 0; idx < items.length; idx ++){
	 					var item = items[idx];
	 					var $item = $('<option>'+item.sbGroupName+'</option>');
	 					$item.attr("value",item.id);
	 					$securityAccountRefType.append($item);
	 				}

	 				// 未设置社保方案的情况下，使用第一项
	 				if(securityType === undefined){
	 					$securityAccountRefType.val(items[0].id);
	 					$securityAccountRefType.trigger('change:once');
	 				}
	 			}
	 		}
	 	});
	 }
	 
	 
	 //选择公积金账户-批量导入
	 $gjjAccountName.on('change',function(){
			var account = $(this).val();
			securityProfile.gongjijinOrgAccount = account;

			// 刷新公积金企业账号，并更新政策包,未设置政策包默认值
			_updateGongjijinAccountListBach(account);
		});
	 /**
	  * 展示公积金账号下的参保规则信息--批量导入部分
	  * @param  {[type]} accountId 公积金账号代码
	  */
	function _updateGongjijinAccountListBach(accountId,gongjijinType){
		if(accountId<0){
			return false;
		}
		$.ajax({
			type : 'GET',
			url : "employee/getFundAccountRulesByAccount/"+accountId,
			async : false,
			dataType: "json",
			success : function(result, textStatus, jqXHR) {
				if(result.status == 0){
					var solutions = result.data;
					if(solutions.length <= 0){
						swal("该企业账号下未设置公积金参保方案，请重新选择！", undefined, "error");
					}else{
						/*// 复制到公共领域
						if(validSocialSecurityRules.gongjijinDetail === undefined){
							validSocialSecurityRules.gongjijinDetail = solutions;
						}*/

						$gjjAccountRefType.empty();
						$gjjAccountRefType.append("<option  value='-1'>请选择</option>");
						for(var idx = 0; idx < solutions.length; idx ++){
							var item = solutions[idx];
							var $item = $('<option>'+item.sbGroupName+'</option>');
							$item.attr('value',item.id);

							$gjjAccountRefType.append($item);
						}
						
						if(gongjijinType === undefined){
							$gjjAccountRefType.val(solutions[0].id);
							$gjjAccountRefType.trigger('change');
						}else{
							$gjjAccountRefType.val(gongjijinType);
						}
					}
				}else{
					swal("取得社保规则明细失败!","","error");
				}
			}
		});
	}
	
	$("#securityFileUpload").on("click",function(){

		 $.ajax({
				url:  "employee/personSecurityProfileImport",
				type: 'POST',
				cache: false,
				dataType: 'json',
				data: new FormData($('#importFileForm')[0]),
				processData: false,
				contentType: false
			}).done(function(data) {
				 
				var flag=data.error.indexOf("导入失败");
				if(data.result&&flag==-1){
					swal({
                        title: data.error,
                        text: "",
                        type: "success",
                        confirmButtonText: "确定"
                    },function(){
                    	window.location.reload();
                    });
				}else{
					swal("员工社保公积金档案导入失败!",data.error,'error');
				}

				// 不管成功失败，都要把文件输入框内容清空，不然在不刷新页面的情况下，下次就不能触发上传了
				//$('#importFile').val("");
			}).fail(function(data) {
				swal("员工社保公积金档案导入异常!","",'error');
				//$('#importFile').val("");
			}); 
		
	});
	
	//--------------------------myself----------------------------------------end

})(window.power);


