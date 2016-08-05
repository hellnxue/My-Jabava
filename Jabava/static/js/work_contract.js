// work_contract.js
;(function(w, $){
    'use strict';
    var getState = function(oTarget, strStateType){
        return {
            stateType: strStateType,
            state: oTarget.prop(strStateType)
        };

    };
    var setState = function(oTarget, strStateType, bState){

    };
    var setVal = function(oTarget, bState, arrVal){
        arrVal = arrVal === undefined? [0,1] : arrVal;
        var $val = arrVal[bState*1];
        oTarget.val( $val );
        return {
            state: bState,
            value: $val
        };
    };
    var setDepsState = function(oTargets, bState){
        var getTargetsState = oTargets.map(function(index, ele){
            if( $(this).hasClass('hidden') && bState ){
                $(this).removeClass('hidden');
            }else{
                $(this).addClass('hidden');
            }
            return {
                index: index,
                ele: $(this),
                isHide: !bState
            };
        });
        return getTargetsState;
    };

    var bindActEvent = function(){

        $('[data-action="trial"]').on('click', 'input:checkbox', function(event) {
            var $getActionWrap = $(this).parents('[data-action]');
            var getDataAction = $getActionWrap.attr('data-action');
            var $getDeps = $getActionWrap.siblings('[data-action-for='+getDataAction+']');

            setVal( $(this), getState( $(this), 'checked' ).state);
            setDepsState( $getDeps, getState( $(this), 'checked' ).state );
        });

    };

    var addMoth = function(d, m){
        // d: Date
        // m: monthly
        var ds = d.split('-'), _d = ds[2]-0;
        m = m * 1;
        var nextM = new Date( ds[0],ds[1]-1 + m + 1, 0 );
        var max = nextM.getDate();
        d = new Date( ds[0],ds[1]-1+m,_d>max? max:_d );

        return d.toLocaleDateString().match(/\d+/g).join('-');
    };
    var setDate = function($datepicker, newDate){
        $datepicker.datepicker('update', newDate);
    };

    var bindDateChangeEvt = function(){
        $('[data-toggle="datepicker"]').on('changeDate', function(e){
            if($(e.target).find('input:text').attr('name') === 'contractStartDate'){
                var getContractStartDate = $(this).find('input:text').val();

                // set contract end date
                var $getContractEndDate = $(this).parents('[role="form"]').find('[name="contractEndDate"]');
                var $getContractEndDateDP = $getContractEndDate.parent('.date');
                var $getContractMonth = $(this).parents('[role="form"]').find('[name="contractMonth"]');
                var getMonthly = $getContractMonth.val();
                if(getMonthly!=='') setDate($getContractEndDateDP, addMoth( getContractStartDate, getMonthly ));

                // set plan positive date
                var $getPlanPositiveDate = $(this).parents('[role="form"]').find('[name="planPositiveDate"]');
                var $getPlanPositiveDateDP = $getPlanPositiveDate.parent('.date');
                var $getTrialMonth = $(this).parents('[role="form"]').find('[name="trialMonth"]');
                var getTrialMonth = $getTrialMonth.val();
                if(getTrialMonth!=='') setDate($getPlanPositiveDateDP, addMoth( getContractStartDate, getTrialMonth ));
            }
        });
        
    };
    var bindContractMonthInputEvt = function(){

        $('[name="contractMonth"]').on('keyup', function(event) {
            event.preventDefault();
            var $getContractStartDate = $(this).parents('[role="form"]').find('[name="contractStartDate"]');
            var getContractStartDate = $getContractStartDate.val();
            var $getContractEndDate = $(this).parents('[role="form"]').find('[name="contractEndDate"]');
            var $getDatepicker = $getContractEndDate.parent('.date');
            var getMonthly = $(this).val();

            if(getMonthly!=='') setDate($getDatepicker, addMoth( getContractStartDate, getMonthly ));
        });
    };
    var bindTrialMonthInputEvt = function(){

        $('[name="trialMonth"]').on('keyup', function(event) {
            event.preventDefault();
            var $getContractStartDate = $(this).parents('[role="form"]').find('[name="contractStartDate"]');
            var getContractStartDate = $getContractStartDate.val();
            var $getPlanPositiveDate = $(this).parents('[role="form"]').find('[name="planPositiveDate"]');
            var $getPlanPositiveDateDP = $getPlanPositiveDate.parent('.date');
            var getMonthly = $(this).val();

            if(getMonthly!=='') setDate($getPlanPositiveDateDP, addMoth( getContractStartDate, getMonthly ));
        });
    };
    var computeDate = function(){
        bindDateChangeEvt();
        bindContractMonthInputEvt();
        bindTrialMonthInputEvt();
    };

    var timer = setInterval(function(){
        if(w.renderReady){
            bindActEvent();
            computeDate();

            $('[data-toggle="datepicker"]')
            .not('[data-form-type="base"] [data-toggle="datepicker"]')
            .on('changeDate', function(e){
                var getEleName = $(e.target).find(':text').attr('name');
                var $fv = $(this).parents('.fv-form');
                $fv.formValidation('revalidateField', getEleName);
            });


            clearInterval(timer);

        }
    }, 1000);

})(window, jQuery);