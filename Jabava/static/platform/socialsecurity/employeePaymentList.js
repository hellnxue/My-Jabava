(function($){

	$('[data-toggle="tab"]').on('shown.bs.tab', function (e) {
		e.target // newly activated tab
		//console.log($(e.target).attr('data-tab-name'))
		var getTargetTab = $(e.target).attr('data-tab-name') == 'paymentDetailedInfo' ? true:false;
		
		if(getTargetTab){
			var getDetailTable = $('#detailTable'),
				getCacheStatus = getDetailTable.attr('data-cache') == 'cached' ? true:false
			if(!getCacheStatus){
				showDetail();
			}
		}
	})
	
	$('.btn-search-paymentlist').click(function(){
		showDetail();
	});
})(jQuery)

function showDetail(){
	//$('[data-bill=code]').text( '账单号' + billCode + '明细' );
	//$('#billYmShow').text(billYm);
	
	//清除动态生成的表头
	//$('#detailHeader').empty();
	
	loadHeader($('#personIdForPaymentList').val());

 	//$('[data-toggle="showmore"]').attr('data-showorhide', 0).text('展开 >');	//默认隐藏
	
}

function loadHeader(personId){
	$.ajax({
		url:"employee/loadPaymentBillPersonHeader",
		type : "POST",
		dataType:'json',
		data: {"personId": personId},
		error: function(XMLHttpRequest, textStatus, errorThrown){
			alert(textStatus);
		},
		success: function(data){
			if(data){
		    	//if(detailTable){
		    	//	detailTable.destroy();
		    	//}
		    	//in case the columns change
	    		$('#detailTable').empty();
		    	
				initDetailTable(data,personId);
			}
		}
	});
}

function initDetailTable(data,personId){
	//读取数据，初始化表头及DataTables的columns
	var columns = [];
	
	//动态添加表头
	var ele;
	var firstRow = $("<tr></tr>");
	var secondRowEles = [];
	$.each(data,function(){
		if(this.fieldName){
			ele = '<th rowspan="2">' + this.showName + '</th>';
			firstRow.append($(ele));
			
			columns.push({"data":this.fieldName});
		}else{
			//ele = '<th style=" padding:0;">';
            //ele += '<div class="ylbx">' + this.prodName + '</div>';
            //ele += '<div class="baoxian">';
            //ele += '<ul>';
            ele = '<th colspan="' + this.cols.length + '">' + this.prod_name + '</th>';
            firstRow.append($(ele));
                
			for(var i = 0; i < this.cols.length; i ++){
				//if(i < this.cols.length - 1){
				//	ele += '<li>' + this.cols[i].showName + '</li>';
    			//}else{
    			//	ele += '<li class="lasts">' + this.cols[i].showName + '</li>';
    			//}
				secondRowEles.push(this.cols[i]);
				//合并列初始隐藏
				//columns.push({"data":this.cols[i].fieldName, "visible":false});
				columns.push({"data":this.cols[i].fieldName});
			};
			
			//ele += '</ul>';
			//ele += '</div>';
			//ele += '</th>';
			
			//$('#detailHeader').append($(ele));
		}
	});

	// append header to the table
	var header = $('<thead></thead>');
	header.append(firstRow);
	
	if(secondRowEles.length == 0){
		$('#detailTable').html(header);
		return false;
	}
	
	var secondRow = $("<tr></tr>");
	$.each(secondRowEles,function(){
        secondRow.append($('<th class="xyz">' + this.showName + '</th>'));
	});

	header.append(secondRow);
	$('#detailTable').html(header);
	
	//for(var i = 9; i < length; i ++){
	//	columns[i].visible = false;
	//}
    initTable(columns,personId);
}

function initTable(columns,personId){
	detailTable = $('#detailTable').DataTable({
		"dom":
    		//"<'row'<'col-sm-12'l>>" +
    		"<'row'<'col-sm-12 table-responsive'tr>>",
    		//"<'row'<'col-sm-5'i><'col-sm-7'p>>",
			
	    "processing": true,
		"serverSide": true,
		"bDestroy": true,
		"sServerMethod": "POST",
		"ordering": false,
		//"bSort": true,
		"columns": columns,
		"columnDefs": [
			{defaultContent: '', targets: '_all'}
		],
		"ajax": {
			"url": "employee/listPaymentBillPerson",
			"data": {
				"personId": personId,
				"startMonth": $('#startMonthForPaymentList').val(),
				"endMonth": $('#endMonthForPaymentList').val()
			}
		},
		drawCallback: function(){
			$('#detailTable').attr('data-cache','cached');
		},
	 	"language": {
            //"lengthMenu": "每页显示 _MENU_ 条记录",
            "zeroRecords": "暂无数据",
            //"info": "显示 第 _PAGE_ 页 共 _PAGES_ 页",
            "infoEmpty": "暂无数据"
            //"infoFiltered": "(筛选自 _MAX_ 条记录)"
		}
	});
	
	//$("#detailTable").css('width', 'auto');
	 $("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
	 $(".xyz").css("border-top-width","1px");
}

function showOrHide1(){
	var oShowMore = $('[data-toggle="showmore"]'),
		visible = eval(oShowMore.attr('data-showorhide'))

	if(visible){
			oShowMore.text('展开 >')
	}else{
		oShowMore.text('隐藏 <')
	}
	oShowMore.attr('data-showorhide', !visible);
	
	if(detailTable){
		var length = detailTable.columns()[0].length;
		for(var i = 9; i < length; i ++){
			var column = detailTable.column(i);
			column.visible(!column.visible());
		}
	}
	
	$("table.table-bordered th:last-child, table.table-bordered td:last-child").css("border-right-width","1px");
	$(".xyz").css("border-top-width","1px");
}

$('[data-motive]').on('click', function(event){
    var getMotive = $(event.target).attr('data-motive');
    switch(getMotive){
        case 'exportPayment':
        	$('#paymentListForm').attr('target','_blank');
        	$('#paymentListForm').attr('action','employee/exportEmployeePayment');
            $('#paymentListForm').submit();
            break;
    }
});
