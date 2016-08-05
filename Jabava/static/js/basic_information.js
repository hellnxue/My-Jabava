// basic_information.js
;(function(w, $){
    'use strict';


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
             clearInterval(timer);

             $('[name="organizationName"]').on('change', function(event) {
                 event.preventDefault();
                 console.log(event);
             });

        }
    }, 1000);

})(window, jQuery);