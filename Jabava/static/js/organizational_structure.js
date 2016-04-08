// JavaScript Document
$(function () {

        var updateOutput = function (e) {
            var list = e.length ? e : $(e.target),
                    output = list.data('output');
            if (window.JSON) {
                output.val(window.JSON.stringify(list.nestable('serialize')));//, null, 2));
            } else {
                output.val('JSON browser support required for this demo.');
            }
        };
        // activate Nestable for list 1
		$('#nestable_third').nestable({
            group: 1
        }).on('change', updateOutput);
		updateOutput($('#nestable_third').data('output', $('#nestable_tow-output')));
})