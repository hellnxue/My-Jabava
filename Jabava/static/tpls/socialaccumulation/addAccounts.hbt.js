<!-- // addAccounts.hbt.js -->
<div role="tabpanel" class="tab-pane">
    <form class="form-horizontal" data-form="rules">
        <div class="hidden params-area">
            <input type="hidden" name="accountId">
            <input type="hidden" name="accountType">
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
            <div class="form-group">
                <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">参保地：</label>
                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 no-gutter form-required">
                    <div class="col-xs-6">
                        <select class="form-control" name="provinceId"></select>
                    </div>
                    <div class="col-xs-6">
                        <select class="form-control" name="cityId"></select>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
            <div class="form-group">
                <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">参保类型：</label>
                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 checkbox-inline-group">
                    <label class="checkbox-inline">
                        <input type="checkbox" name="accountTypeChb" value="1" checked="checked" data-rel-name="securityRule"> 社保
                    </label>
                    <label class="checkbox-inline">
                        <input type="checkbox" name="accountTypeChb" value="2" checked="checked" data-rel-name="accumulationRule"> 公积金
                    </label>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3" data-account-type="1">
            <div class="form-group">
                <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">社保规则 ：</label>
                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 form-required">
                    <div class="form-control" role="textarea" data-policy-type="securityRule">
                        <ul class="list-group">
                        </ul>
                    </div>
                    <input type="hidden" name="securityRule">
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3" data-account-type="2">
            <div class="form-group">
                <label class="control-label col-xs-4 col-sm-4 col-md-4 col-lg-4">公积金规则 ：</label>
                <div class="col-xs-8 col-sm-8 col-md-8 col-lg-8 form-required">
                    <div class="form-control" role="textarea" data-policy-type="accumulationRule">
                        <ul class="list-group">
                        </ul>
                    </div>
                    <input type="hidden" name="accumulationRule">
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-md-offset-3">
            <div class="form-submit text-center">
                <button type="submit" class="btn btn-primary">保存规则</button>
            </div>
        </div>
    </form>
</div>