<%@page import="com.jabava.utils.RequestUtil"%>
<%@page import="com.jabava.pojo.manage.EhrUser"%>
<%@page import="com.jabava.pojo.manage.EhrCompany"%>
<%@ page contentType="text/html; charset=utf-8" %>

<%
	EhrUser user = RequestUtil.getLoginUser(request);
	EhrCompany company = user.getCompany();
%>
<!DOCTYPE html>
<html>
<head>
	<base href="${pageContext.request.contextPath}/">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <!-- Page title -->
    <title>申请开通</title>
    <jsp:include flush="true" page="../common/styles.jsp"></jsp:include>
</head>
<body >
<!--splash screen-->
<jsp:include flush="true" page="../common/splashscreen.div.jsp"></jsp:include>
<!--引入头文件 开始--> 
<jsp:include flush="true" page="../common/header.div.jsp"></jsp:include>
<!--引入头文件 结束--> 
<!--引入菜单文件 开始--> 
<jsp:include flush="true" page="../common/menu.div.jsp"></jsp:include>
<!--引入菜单文件 结束--> 
  
<!-- Main Wrapper -->
<div id="wrapper">
    <div class="small-header transition animated fadeIn">
        <div class="hpanel">
            <div class="panel-body text-center">
                申请开通
            </div>
        </div>
    </div>
    <!-- 放主要内容 -->
    <div class="content animate-panel">
        <div class="row">
            <div class="col-lg-12">
                <div class="hpanel">
                    <div class="panel-body">
                        <form class="form-horizontal" data-id="validator" method="post">
                        	<div class="col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-sm-6 col-md-6 col-lg-6">
                            	<input type="hidden" name="productName" value="${requestScope.hroPactInfo.productName }">
                            	<input type="hidden" name="providerName" value="${requestScope.hroPactInfo.providerName }">
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">服务城市</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                        <input type="text" name="serviceCity" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">服务人数</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                        <input type="text" name="servicePersonNum" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">服务起始日期</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                        <div class="input-group date">
                                            <input type="text" value="" name="serviceStartDate" class="form-control">
                                            <span class="input-group-addon" style=""><i class="glyphicon glyphicon-th"></i></span>
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">公司名称</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                        <input readonly="readonly" type="text" name="companyName" class="form-control" value="<%=company.getCompanyName()%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">地址</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9">
                                        <input type="text" name="address" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">联系人</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                        <input type="text" name="contactEmp" class="form-control" value="<%=company.getContactor() == null ? "" : company.getContactor()%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">联系方式</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9 form-required">
                                        <input type="text" name="telephoneNumber" class="form-control" value="<%=company.getContactorTelephone() == null ? "" : company.getContactorTelephone()%>">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-4 col-md-3 col-lg-3">email</label>
                                    <div class="col-sm-8 col-md-9 col-lg-9">
                                        <input type="text" name="email" class="form-control">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-4 col-md-offset-3 col-lg-offset-3 col-sm-8 col-md-9 col-lg-9">
                                        <label class="checkbox-inline">
                                            <input type="checkbox" value="0" id="" name="checkbox" data-id="checkbox">
                                            我已经阅读并同意<a href="javascript://" data-toggle="modal" data-target="[data-id=protocol]">《代发工资协议》</a>
                                        </label>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <div class="col-sm-offset-4 col-md-offset-3 col-lg-offset-3 col-sm-8 col-md-9 col-lg-9">
                                        <button class="btn btn-success btn-block" type="submit" data-motive="submit" id="">提交</button>
                                    </div>
                                </div>
	                        </div>
	                        <div class="col-sm-12 col-md-12 col-lg-12 text-right">
	                            <button class="btn btn-info m-r-md" type="button" id="previousStep">上一步</button>
	                            <button class="btn btn-info" type="submit" data-motive="save" id="">保存</button>
	                        </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div> 
    <div class="modal fade hmodal-success form-row in" data-id="protocol">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="color-line"></div>
                <div class="modal-header">
                    <div class="hpanel">
                        <div class="panel-heading m-b-md">
                            <h5>服务开通-电子协议</h5>
                        </div>
                        <div class="panel-body pre-scrollable">
                            <div class="col-lg-12 text-center m-b-lg">
                                <h6>安心社保产品技术平台服务协议</h6>
                                <small>协议编号：ZY20160013</small>
                            </div>
                            <div class="col-sm-offset-1 col-md-offset-1 col-lg-offset-1 col-sm-10 col-md-10 col-lg-10 m-b-lg">
                                <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">甲方：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">乙方：</label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">地址：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">地址：</label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">电话：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">电话：</label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">联系人：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">联系人：</label>
                                </div>
                                <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">电子邮件：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">电子邮件：</label>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <p>甲乙双方在平等互利的基础上，依照法律法规的相关规定，经友好协商就甲方使用乙方提供的安心社保技术平台服务达成以下协议，并共同遵守。在签订本协议时，甲乙双方对协议的所有条款均无疑义并对各自有关权利、义务和责任条款的法律含义有准确无误的理解。</p>
                                <p>第一条 合作内容</p>
                                <p>甲方是一家依据中国法律成立并有效存续的公司，属于劳动合同法规定的用人单位。乙方为安心社保及其无线客户端平台（包括APP和微信平台）的技术提供商，通过系统管理后台，将相关社保代理缴纳、住房公积金代理缴纳、补充商业保险等相关人力资源和保险服务供应商的产品展示在【网址】及其无线客户端，并提供技术服务和技术支持。乙方仅为IT系统技术提供商，不参与线下实际服务供应商与甲方之间的实际委托服务行为。</p>
                                <p>第二条 合同期限</p>
                                <p>本合同期限自_    年     月    日起至 _    年_    月_    日止，为期一年，本协议期满前双方未书面提出异议的，自动顺延。</p>
                                <p>第三条 甲方权利义务</p>
                                <p>3.1甲方保证为依法成立并有效存续的公司，具有劳动合同法规定的用人单位主体资质。</p>
                                <p>3.2甲方需妥善保管乙方提供的系统用户名、密码等身份认证信息。通过乙方系统登录的甲方身份认证信息所操作的行为，将视为甲方行为或甲方的授权行为，由甲方承担相应的后果。</p>
                                <p>3.3因甲方输入信息错误、遗漏或不准确、不真实所导致的与甲方员工纠纷，由甲方负责自行解决。</p>
                                <p>3.4甲方有权要求乙方按照本合同约定提供相应的委托服务，同时按照本合同规定按时向乙方支付总费用。</p>
                                <p>3.5 甲方对乙方违反本合同条款的行为有权提出书面意见，要求乙方按照合同履行义务。乙方应于收到此意见后十个工作日内，以书面形式回复甲方。</p>
                                <p>3.6甲方应将本合同的服务项目及乙方的相关程序告知员工，并征得员工的书面同意，该程序包括但不限于工资发放（包含“工资宝”服务），医保理赔程序，社保和住房公积金代缴服务。否则因此导致的法律责任，由甲方承担。</p>
                                <p>第四条 乙方权利义务</p>
                                <p>4.1 乙方有权向甲方收取系统的技术服务费和代收代付的费用（详见附件一）。</p>
                                <p>4.2 乙方应和甲方与相关人力资源和保险服务供应商保持沟通流畅，及时优化服务流程，协调甲方与线下服务供应商之间的沟通问题。</p>
                                <p>4.3 乙方利用自身技术优势对系统进行维护、支持和优化，保证系统平台的正常运行，保证及时准确地向甲方及满足条件的甲方雇员提供委托服务。</p>
                                <p>4.4 乙方和线下服务供应商可以与甲方协商制定提供委托服务的相关程序，甲方及员工应当配合执行。</p>
                                <p>第五条 代办社保特殊约定</p>
                                <p>乙方安排的线下实际服务供应商接受甲方的委托，根据甲方的要求为甲方员工在其社保账户内缴纳社会保险及住房公积金，但甲方员工不因此与乙方或线下实际服务供应商之间建立劳动关系，其仍与甲方存在唯一的劳动关系。甲方对此应向员工披露本合同或做出情况说明。</p>
                                <p>第六条  收款及结算</p>
                                <p>6.1 服务费从合同开始日开始计算，按月收取费用。</p>
                                <p>6.2 根据附件一的约定，乙方的技术服务费为【  】元/人/月（详见附件一）。服务费根据合同约定，以乙方《付款通知单》为当月服务费的结算依据。乙方《付款通知单》每月【  】日前发出。甲方收到《付款通知单》后在当月【  】日前付款至乙方帐中。</p>
                                <p>6.3 《付款通知单》中包括技术服务费和其他收费项目，开具营业性发票；而社保和公积金代付费用属于代收代付费用，开具代收代付发票。 本条款中发票是指增值税专用发票或增值税普通发票，具体执行时以国家和本市税务机关的有关规定为准。</p>
                                <p>6.4 本合同期限内每逢一个新的社会保险、住房公积金年度，甲方向乙方支付的总费用中有关社会保险费和住房公积金的付费标准，应按照当地政府颁布的社会保险费用、住房公积金调整比例做相应的调整。乙方应在当地政府公布新标准后，以书面形式及时通知甲方，甲方据此调整总费用的数额。本合同期内，如遇地方政策和操作流程变更，甲乙双方可以协商变更结算、付款流程和方式。乙方不承担为甲方垫款的义务。</p>
                                <p>6.5 如甲方以银行汇款方式支付费用，应汇入以下乙方指定的账号：</p>
                                <p>开户名：</p>
                                <p>开户行：</p>
                                <p>帐　号：</p>
                                <p>第七条 保密义务</p>
                                <p>双方在洽谈、签署和履行本协议中所接触的对方商业秘密信息或其他任何信息、资料均负有保密义务，未经对方事先书面许可任何一方不得泄露给任何第三方或在本协议之外使用。</p>
                                <p>第八条 违约责任</p>
                                <p>8.1  除双方特别约定，乙方没有为甲方垫款的义务，如甲方迟延支付费用，乙方（包括实际服务供应商）可相应暂停全部或部分服务不承担任何责任，且有权通知甲方解除本协议。</p>
                                <p>8.2任何一方未按本合同约定履行义务而导致对方蒙受经济损失的，应承担赔偿责任。</p>
                                <p>8.3 因违约方违反本协议下的义务、保证或承诺所导致的各种纠纷，违约方应积极妥善解决并承担责任。导致守约方及其关联企业的损失，由违约方承担赔偿责任，守约方有权提前解除本协议而不承担任何责任。</p>
                                <p>8.4 任何一方不得随意终止本合同。否则违约方应赔偿守约方受到的损失。但以下情形除外：</p>
                                <p>1）因本合同任何一方违反本合同相关义务，经守约方书面通知后仍不履行合同的，守约方可书面通知违约方解除本合同。</p>
                                <p>2）在合同已经履行一年以上的，任何一方可以提前三个月书面通知对方，并取得对方书面同意后方可解除。否则，甲方应支付乙方违约金，违约金标准为3个月的服务费，该服务费以履行合同期间的月最高服务费为标准。</p>
                                <p>3）依据不可抗力而终止。</p>
                                <p>8.5如因甲方原因致乙方迟于约定日期收到甲方付款的，乙方有权按应付款的2‰ /天收取滞纳金。由于甲方延期付款而造成社保、公积金或工资等各类费用缴纳延误或停缴而引起索赔（包括对员工医疗、工伤、居住证办理等造成的不利及赔偿）等责任的，由甲方承担因此产生的全部责任。</p>
                                <p>第九条 其他事项</p>
                                <p>9.1  本协议之订立、生效、解释、修订、补充、终止、执行与争议解决均适用中华人民共和国大陆地区法律；如法律无相关规定的，参照商业惯例或行业惯例。</p>
                                <p>9.2本协议任一条款被视为废止、无效或不可执行，该条应视为可分的且并不影响本协议其余条款的有效性及可执行性。</p>
                                <p>9.3  本合同在履行中，如国家和当地政府新颁布了有关法律、法规和规定，应以新颁布的规定为准。新颁布的有关法律、法规和规定与本合同条款发生矛盾时，则须就合同条款进行协商达成补充协议，任何一方不得拒绝变更合同条款。</p>
                                <p>9.4  因本协议而产生的纠纷，由甲乙双方协商解决。协商不成，双方同意提交原告方所在地人民法院解决。</p>
                                <p>9.5本协议自双方盖章或签字之日起生效，并取代双方之前任何口头和书面约定(除非本协议有特殊说明)。本协议一式两份，甲乙双方各执一份，具有同等效力。本协议到期后，甲乙双方一致同意合同自动顺延持续有效，除非一方提前15日书面通知另一方终止合作。</p>
                                <p>9.6 协议内容包括协议正文、附件。所有上述内容为本协议不可分割的一部分，与协议正文具有同等法律效力。乙方有权根据业务发展的需要对本协议正文、附件及公告内容等进行变更，并通过技术系统平台及时告知甲方，甲方在线确认或继续使用乙方服务，即视为甲方签署了变更、补充协议，与协议原件具有同等效力；如甲方不同意相关变更，可以终止本协议。</p>
                                <p>9.7 双方应提供具有合法经营许可权的证明文件复印件，并保证其有关证件在协议期间一直有效。</p>
                                <p>本协议附件包括：</p>
                                <p>附件一：《安心社保产品服务内容和报价单》 ；</p>
                            </div>
                            <div class="col-sm-offset-3 col-md-offset-3 col-lg-offset-3 col-sm-6 col-md-6 col-lg-6 m-b-lg">
                               <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">甲方：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">乙方：智阳</label>
                                </div> 
                                <div class="form-group">
                                    <label class="control-label col-sm-6 col-md-6 col-lg-6">日期：</label>
                                    <label class="control-label text-right col-sm-6 col-md-6 col-lg-6">日期：</label>
                                </div> 
                            </div>
                        </div>
                        <div class="panel-footer text-center no-borders">
                            <button type="button" class="btn btn-info"  data-target="checkbox">同意</button>
                            <button type="button" class="btn btn-info sr-only">联系客服</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 放页脚  开始-->
    <jsp:include flush="true" page="../common/foot.div.jsp"></jsp:include>
    <!-- 放页脚  结束-->
</div>

<!-- Vendor scripts -->
<script src="static/bootstrap/vendor/jquery/dist/jquery.min.js"></script>
<script src="static/bootstrap/vendor/jquery-ui/jquery-ui.min.js"></script>
<script src="static/bootstrap/vendor/slimScroll/jquery.slimscroll.min.js"></script>
<script src="static/bootstrap/vendor/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="static/bootstrap/vendor/metisMenu/dist/metisMenu.min.js"></script>
<script src="static/bootstrap/vendor/iCheck/icheck.min.js"></script>
<script src="static/bootstrap/vendor/sparkline/index.js"></script>
<script src="static/bootstrap/vendor/moment/moment.js"></script>
<script src="static/bootstrap/scripts/homer.js"></script>
<script src="static/bootstrap/vendor/bootstrap-datepicker-master/dist/js/bootstrap-datepicker.js"></script>
<!-- 表单验证 -->
<script src="static/js/plugins/form.validation/js/formValidation.js"></script>
<script src="static/js/plugins/form.validation/js/framework/bootstrap.js"></script>
<script src="static/js/plugins/form.validation/js/language/zh_CN.js"></script>
<!-- alert -->
<script src="static/bootstrap/vendor/sweetalert/lib/sweet-alert.min.js"></script>

<script>
	jQuery.prototype.serializeObject=function(){  
	    var obj=new Object();  
	    $.each(this.serializeArray(),function(index,param){  
	      if(!(param.name in obj)){  
	        obj[param.name]=param.value;  
	      }  
	    });  
	    return obj;
	};

	(function($){
        function dateInit(){
            $('.input-group.date').datepicker({
              format: "yyyy-mm-dd",
              autoclose: true
            });
        }
        function protocolChecked(){
            $('[data-id="protocol"]')
            .find('[data-target="checkbox"]')
            .on('click', function(e){
                var getTarget = $(this).attr('data-target'),
                    getCheckBox = $('[data-id="'+getTarget+'"]');

                if(!getCheckBox.prop('checked')){
                    getCheckBox.prop('checked',true);
                }
                $("[data-id=protocol]").modal('hide');
            });
        }
        function validateForm(){
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
                    serviceStartDate: {
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
                    serviceCity: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项目'
                            }
                        }
                    },
                    servicePersonNum: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项目'
                            },
                            between: {
                                message: '输入的值必须大于0',
                                max: 10000000,
                                min: 1
                            }
                        }
                    },
                    companyName: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项目'
                            }
                        }
                    },
                    contactEmp: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项目'
                            }
                        }
                    },
                    telephoneNumber: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项目'
                            },
                            phone: {
                                message: '请输入有效的电话号码',
                                country: 'CN'
                            }
                        }
                    },
                    email: {
                        enabled: false,
                        validators: {
                            notEmpty: {
                                message: '请填写必填项'
                            },
                            regexp: {
                                message: '请输入有效的邮箱',
                                regexp: /^([a-zA-Z0-9_\-\.])+@(\w)+((\.\w+)+)$/
                            }
                        }
                    },
                    checkbox: {
                        validators: {
                            notEmpty: {
                                message: '请填写必填项目'
                            }
                        }
                    }
                }
            }

            $('[data-id="validator"]')
            .formValidation(validateOptions)
            .on('keyup', '[name="email"]', function(e) {
                e.preventDefault();
                var isEmailEmpty = $(this).val() == '';

                $('[data-id="validator"]')
                .formValidation('enableFieldValidators', 'email', !isEmailEmpty);

                if($(this).val().length == 1){
                    $('[data-id="validator"]').formValidation('validateField', 'email');
                }
            })
            .on('success.form.fv', function(e){
                e.preventDefault();
                var getMotive = $(e.target).data('formValidation').getSubmitButton().attr('data-motive');
                console.log(getMotive);
                switch(getMotive){
                    case 'submit':
                        operate('outsourcing/addAndSubmitOutsourcing');
                        break;
                    case 'save':
                        operate('outsourcing/addOutsourcing');
                        break;
                    default:
                }
            });

            $('.input-group.date')
            .on('changeDate', function(e){
                var getEleName = $(e.target).find(':text').attr('name');
                $('[data-id="validator"]').formValidation('revalidateField', getEleName);
            });

            $('[data-id="protocol"]')
            .find('[data-target="checkbox"]')
            .on('click', function(e){
                var getTarget = $(this).attr('data-target'),
                    getCheckBox = $('[data-id="'+getTarget+'"]');

                if(!getCheckBox.prop('checked')){
                    getCheckBox.prop('checked',true);
                }
                $('[data-id="validator"]').formValidation('revalidateField', 'checkbox');
                $("[data-id=protocol]").modal('hide');
            });
        }
        
        $('#previousStep').click(function(){
            $('[data-id="validator"]').formValidation('destroy');
        	$('[data-id="validator"]').attr('action', 'outsourcing/toServiceOpen');
        	$('[data-id="validator"]').submit();
        });
        
        function init(){
            dateInit();//日期初始
            // protocolChecked();//协议勾选
            validateForm();//校验
        }
        init();
    })(jQuery)
    
    function operate(url){
		$.ajax({
			type : 'post',
			url : url,
			data : $('[data-id=validator]').serialize(),
			dataType: "json",
			async : false,
			success : function(data) {
                if(data.success){
                	window.location.href = 'outsourcing/toListProtocol';
                }else{
                    $('[data-id="validator"]').formValidation('resetForm');
                	swal({
                        title: data.msg,
                        type: "error"
                    });
                }
			}
		});
	}
</script>

</body>
</html>
