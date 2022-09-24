
$(document).ready(function() {
	loadData();
});

function loadData() {
	$.ajax({
		url: "/api/v1/meds", success: function(data) {
			$.each(data, function(i) {
				$('#tdata').append(
					'<tr role="row" mat-row="" data-test-id="table-row-stockarea-item" class="mat-row cdk-row oc-link ng-star-inserted">'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-stock_area mat-column-stock_area ng-star-inserted">' + data[i].itemId + '</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-stock_area mat-column-stock_area ng-star-inserted">' + data[i].description + '</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-misc1 mat-column-misc1 ng-star-inserted">' + data[i].parQty + 'EA</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-misc2 mat-column-misc2 ng-star-inserted">' + data[i].binId + '</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-control_level mat-column-control_level ng-star-inserted">'
					+ '<mat-form-field class="mat-form-field ng-tns-c275-22 mat-primary mat-form-field-type-mat-input mat-form-field-appearance-outline mat-form-field-can-float mat-form-field-has-label mat-form-field-hide-placeholder ng-untouched ng-pristine ng-valid ng-star-inserted">'
					+ '<div class="mat-form-field-wrapper ng-tns-c275-22">'
					+ '<div class="mat-form-field-flex ng-tns-c275-22">'
					+ '<div class="mat-form-field-outline ng-tns-c275-22 ng-star-inserted">'
					+ '<div class="mat-form-field-outline-start ng-tns-c275-22" style="width: 7px;"></div>'
					+ '<div class="mat-form-field-outline-gap ng-tns-c275-22" style="width: 122.5px;"></div>'
					+ '<div class="mat-form-field-outline-end ng-tns-c275-22"></div>'
					+ '</div>'
					+ '<div class="mat-form-field-outline mat-form-field-outline-thick ng-tns-c275-22 ng-star-inserted">'
					+ '<div class="mat-form-field-outline-start ng-tns-c275-22" style="width: 7px;"></div>'
					+ '<div class="mat-form-field-outline-gap ng-tns-c275-22" style="width: 122.5px;"></div>'
					+ '<div class="mat-form-field-outline-end ng-tns-c275-22"></div>'
					+ '</div>'
					+ '<div class="mat-form-field-infix ng-tns-c275-22">'
					+ '<input data-id="' + data[i].itemId + '" data-parQty=' + data[i].parQty + '" matinput="" type="text" cdkfocusinitial="" autofocus="" data-test-id="inp-search-route" class="item-dispense mat-input-element mat-form-field-autofill-control identify__restock-route-list ng-tns-c275-22 ng-untouched ng-pristine ng-valid cdk-text-field-autofill-monitored" id="mat-input-0" data-placeholder="Route Name" aria-invalid="false" aria-required="false">'
					+ '<span class="mat-form-field-label-wrapper ng-tns-c275-22">'
					+ '<label class="mat-form-field-label ng-tns-c275-22 mat-empty mat-form-field-empty ng-star-inserted" id="mat-form-field-label-3" for="mat-input-0" aria-owns="mat-input-0">'
					+ '<mat-label class="ng-tns-c275-22 ng-star-inserted"></mat-label>'
					+ '</label>'
					+ '</span>'
					+ '</div>'
					+ '</div>'
					+ '<div class="mat-form-field-subscript-wrapper ng-tns-c275-22">'
					+ '<div class="mat-form-field-hint-wrapper ng-tns-c275-22 ng-trigger ng-trigger-transitionMessages ng-star-inserted" style="opacity: 1; transform: translateY(0%);">'
					+ '<div class="mat-form-field-hint-spacer ng-tns-c275-22"></div>'
					+ '</div>'
					+ '</div>'
					+ '</div>'
					+ '</mat-form-field>'
					+ '</td>'
					+ '</tr>'
				)
			});
		}
	});
}

function dispense() {
	var dao = {data: []};
	var allData = [];
	$('.item-dispense').each(function(i, obj) {
		var data = { itemId: "", dispenseQty: "", parQty: "" }
		var dispenseQty = $(obj).val();
		if (dispenseQty !== "") {
			data.itemId = $(obj).attr('data-id');
			var dispense = parseInt(data.dispense = $(obj).val());
			var parQty = parseInt($(obj).attr('data-parQty'));
			data.parQty = parQty;
			data.dispenseQty = dispense;
			allData.push(data);
		}
	});
	dao.data = allData;
	console.log(allData);
	$.ajax({
		'headers': { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
    	},
		'type': 'POST',
		'url': '/api/v1/meds/dispense',
		'data': JSON.stringify(dao),
		'dataType': "json",
		'success': function(data) {
	 		window.location.href = "/restock"
 		}
	});

}