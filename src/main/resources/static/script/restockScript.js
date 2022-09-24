$(document).ready(function() {
	loadData();
});

function loadData() {
	$.ajax({
		url: "/api/v1/meds/restock?id=1649668063", success: function(data) {
			$.each(data, function(i) {
				$('#tdata').append(
					'<tr role="row" mat-row="" data-test-id="table-row-stockarea-item" class="mat-row cdk-row oc-link ng-star-inserted">'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-stock_area mat-column-stock_area ng-star-inserted">' + data[i].itemId + '</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-stock_area mat-column-stock_area ng-star-inserted">' + data[i].restockQty + '</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-misc1 mat-column-misc1 ng-star-inserted">' + data[i].dispenseOn + '</td>'
					+ '<td role="cell" mat-cell="" class="mat-cell cdk-cell cdk-column-misc2 mat-column-misc2 ng-star-inserted">'
						+'<mat-form-field class="mat-form-field tw-w-full md:tw-w-96 ng-tns-c275-20 mat-primary mat-form-field-type-mat-select mat-form-field-appearance-outline mat-form-field-can-float mat-form-field-has-label mat-form-field-hide-placeholder ng-star-inserted">'
						 +  '<div class="mat-form-field-wrapper ng-tns-c275-20">'
						    +  '<div class="mat-form-field-flex ng-tns-c275-20">'
						       +  '<div class="mat-form-field-outline ng-tns-c275-20 ng-star-inserted">'
						          +  '<div class="mat-form-field-outline-start ng-tns-c275-20" style="width: 7px;"></div>'
						          +  '<div class="mat-form-field-outline-gap ng-tns-c275-20" style="width: 30.25px;"></div>'
						          +  '<div class="mat-form-field-outline-end ng-tns-c275-20"></div>'
						       +  '</div>'
						       +  '<div class="mat-form-field-outline mat-form-field-outline-thick ng-tns-c275-20 ng-star-inserted">'
						          +  '<div class="mat-form-field-outline-start ng-tns-c275-20" style="width: 7px;"></div>'
						          +  '<div class="mat-form-field-outline-gap ng-tns-c275-20" style="width: 30.25px;"></div>'
						          +  '<div class="mat-form-field-outline-end ng-tns-c275-20"></div>'
						       +  '</div>'
						      
						       +  '<div class="mat-form-field-infix ng-tns-c275-20">'
						          +  '<mat-select role="combobox" aria-autocomplete="none" aria-haspopup="true" class="mat-select ng-tns-c299-21 ng-tns-c275-20 mat-select-empty ng-star-inserted" aria-labelledby="mat-form-field-label-1 mat-select-value-1" id="mat-select-0" tabindex="0" aria-expanded="false" aria-required="false" aria-disabled="false" aria-invalid="false">'
						             +  '<div cdk-overlay-origin="" class="mat-select-trigger ng-tns-c299-21">'
						                +  '<div class="mat-select-value ng-tns-c299-21" id="mat-select-value-1">'
						                   +  '<span class="mat-select-placeholder mat-select-min-line ng-tns-c299-21 ng-star-inserted"></span>'
						                +  '</div>'
						                +  '<div class="mat-select-arrow-wrapper ng-tns-c299-21">'
						                   +  '<div class="mat-select-arrow ng-tns-c299-21"></div>'
						                +  '</div>'
						             +  '</div>'
						            
						          +  '</mat-select>'
						          +  '<span class="mat-form-field-label-wrapper ng-tns-c275-20">'
						             +  '<label class="mat-form-field-label ng-tns-c275-20 mat-empty mat-form-field-empty ng-star-inserted" id="mat-form-field-label-1" for="mat-select-0" aria-owns="mat-select-0">'
						               
						                +  '<mat-label class="ng-tns-c275-20 ng-star-inserted">CPC02 - 008890991</mat-label>'
						               
						             +  '</label>'
						            
						          +  '</span>'
						       +  '</div>'
						      
						    +  '</div>'
						   
						    +  '<div class="mat-form-field-subscript-wrapper ng-tns-c275-20">'
						      
						       +  '<div class="mat-form-field-hint-wrapper ng-tns-c275-20 ng-trigger ng-trigger-transitionMessages ng-star-inserted" style="opacity: 1; transform: translateY(0%);">'
						         
						          +  '<div class="mat-form-field-hint-spacer ng-tns-c275-20"></div>'
						       +  '</div>'
						      
						    +  '</div>'
						 +  '</div>'
						+'</mat-form-field>'
						+'</td>'
						+ '<td>	'
						+ '	<button mat-button="" color="primary" class="mat-focus-indicator mat-button mat-button-base mat-primary" onclick="dispense(\'' + data[i].id + '\')">'
						+ '	  	<span class="mat-button-wrapper">'
						+ '	      <span>Restock</span>'
						+ '	    </span>'
						+ '	    <span matripple="" class="mat-ripple mat-button-ripple">'
						+ '	    </span>'
						+ '	    <span class="mat-button-focus-overlay">'
						+ '	    </span>'
					    + '	</button>'
						+'</td>'
					+ '</tr>'
				)
			});
		}
	});
}

function dispense(data) {
	var url = '/api/v1/meds/restock/' + data;
	$.ajax({
		'headers': { 
	        'Accept': 'application/json',
	        'Content-Type': 'application/json' 
    	},
		'type': 'POST',
		'url': url,
		'dataType': "json",
		'success': function(data) {
	 		window.location.href = "/restock"
 		}
	});
}