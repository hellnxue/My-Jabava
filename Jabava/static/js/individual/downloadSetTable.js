//downloadSetTable.js
;(function($){
	'use strict';
	
	var address="";
	 
	
	$('[data-address]').on("click",function(){
		//address="E:\\upload\\8\\100\\2016\\05\\19\\P60519-081503.jpg";
		var sessionAddress = $("#bpaddress"+individualType).val();
		if(sessionAddress){
			
			window.location.href="individual/downloadFile.do?filepath="+sessionAddress;
			
		}else{
			
			
			swal("请先上传人员名单!","",'error');
		}
	});
	
	
	
    var $uploadCompent = $('[data-toggle="upload:file"]'),
        $fileInput = $('[data-action="file"]');

    var fileUpload = function(){
        $fileInput.on('change.file:selected', function(e){
            var getFileName = $(this).val(),
                getTitle = '附件资料',
                $getMsgCover = $(this).next();
            $getMsgCover.css({
                height: $getMsgCover.parent().height(),
                lineHeight: $getMsgCover.parent().height()+'px',
                width: $getMsgCover.parent().width(),
                zIndex: 100
            }).text(getTitle+'上传中...').show();
            

            $uploadCompent.find(':text').val(getFileName);
        	var rosterbatchId=$("#rosterbatch").val();//总名单批次id
            var formData=new FormData();
        	formData.append("uploadFiles", $fileInput[0].files[0]);
        	formData.append("rosterbatchId",rosterbatchId);//测试数据1
        	formData.append("type",individualType);//批次类型
        	
    	   $.ajax({
				url:  "individual/uploadBPFile",
				type: 'POST',
				cache: false,
				dataType: 'json',
				data: formData,
				processData: false,
				contentType: false
				
			}).done(function(data){
				if(data.success){
					$getMsgCover.text(getTitle+'上传成功。');
    				setTimeout(function(){ $getMsgCover.hide(); }, 1500);
    				swal("上传成功!","",'success');
    				address=data.msg;
    				$("#bpaddress"+individualType).val(address);
    				
				}else{
					$getMsgCover.text(getTitle+'上传失败。');
    				setTimeout(function(){ $getMsgCover.hide(); }, 1500);
    				swal("上传失败!",data.msg,'error');
				}
				
			}).fail(function(data){
				
				$getMsgCover.text(getTitle+'上传失败。');
				setTimeout(function(){ $getMsgCover.hide(); }, 1500);
			});   
        });
    };

    var download = function(){
        $('[data-id="download"]').on('click', function(e) {
            var getSelectVal = $('[data-toggle="select2"]').select2().val(),
                getFileTextVal = $uploadCompent.find(':text').val();

            if(!getSelectVal || !getFileTextVal){
                console.log('false')
            }else{
                console.log('true')
            }
        });
    }()

    var initSelect = function(){
        $.ajax({
            url: 'individual/rosterBatchInfo',
            type: 'POST',
            dataType: 'json',
            data: {type: individualType}
        })
        .done(function(d) {
            var $select2 = $('[data-toggle="select2"]');
            $.each(d.rosterBatchList,function(index, el) {
                $select2.append('<option value="'+el.id+'">'+el.batchCode+'</option>')
            });
            $select2.select2();
        })
    }()


    $uploadCompent.on('click', function(e){
    	var rosterbatchId=$("#rosterbatch").val();
    	if(rosterbatchId=="" ){
    		swal("请先选择批次号!","",'error');
    	}else{
    		  $fileInput.trigger('click.file:selected');
    	        fileUpload();
    	}
      
    });

})(jQuery)