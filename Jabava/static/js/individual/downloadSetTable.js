//downloadSetTable.js
;(function($){
	'use strict';
    var $uploadCompent = $('[data-toggle="upload:file"]'),
        $fileInput = $('[data-action="file"]');

    var fileUpload = function(){
        $fileInput.on('change.file:selected', function(e){
            var getFileName = $(this).val();

            $uploadCompent.find(':text').val(getFileName);
            
            var formData=new FormData();
        	formData.append("uploadFiles", $fileInput[0].files[0]);
        	formData.append("rosterbatchId",1);
        	formData.append("type",2);
        	 
        	console.log(formData);
        	console.log(formData.get("rosterbatchId"));
        	  $.ajax({
    				url:  "individual/uploadBPFile",
    				type: 'POST',
    				cache: false,
    				dataType: 'json',
    				data: formData,
    				processData: false,
    				contentType: false
    				
    			}).done(function(data){
    				
    				console.log("ok: "+data.imgUrl);
    				
    			}).fail(function(data){
    				
    				console.log("error");
    				console.log(data);
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
    }

    var initSelect = function(){
        $.ajax({
            url: 'individual/rosterBatchInfo',
            type: 'POST',
            dataType: 'json',
            data: {type: 1}
        })
        .done(function(d) {
            var $select2 = $('[data-toggle="select2"]');
            $.each(d.rosterBatchList,function(index, el) {
                $select2.append('<option value="'+el.id+'">'+el.batchCode+'</option>')
            });
            $select2.select2();
        })
    }

    var init = function(){
        $uploadCompent.on('click', function(e){
            $fileInput.trigger('click.file:selected');
            fileUpload();
        });
        initSelect();
        download();
    }
    init();
})(jQuery)