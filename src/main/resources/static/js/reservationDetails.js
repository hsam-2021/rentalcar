$(document)
		.ready(
				function() {

					var location = {
						"NY-CV" : "Brooklyn, NewYork",
						"NY-NB" : "Bronx, NewYork",
						"NY-HG" : "Flushing, NewYork",
						"NY-SG" : "Woodside, NewYork",
						"NY-LC" : "Staten Island, NewYork",
						"NY-GR" : "Ridgewood, NewYork",
						"NY-SA" : "Jackson Heights, NewYork",
						"NY-SM" : "Huntington Station, NewYork",
						"NY-SA" : "Jamaica, NewYork"
					};

					var columnDefs = [

					{
						data : 'idReservation',
						title : 'Reservation ID',
						type : 'readonly'
					}, {
						data : 'client',
						title : 'clientid',
						type : 'hidden',
						visible : false,
						searchable : false
					},

					{
						data : 'clientName',
						title : 'Customer Name',
						type : 'readonly'
					},

					{
						data : 'initDate',
						title : 'Start Date',
						type : 'date'
					},

					{
						data : 'finalDate',
						title : 'End Date',
						type : 'date'
					},

					{
						data : 'pickupLoc',
						title : 'Pickup Location',
						type : 'select',
						options : location,
						render : function(data, type, row, meta) {
							if (data == null || !(data in location))
								return null;
							return location[data];

						}
					}, {
						data : 'dropoffLoc',
						title : 'DropOff Location',
						type : 'select',
						options : location,
						render : function(data, type, row, meta) {
							if (data == null || !(data in location))
								return null;
							return location[data];

						}
					}, {
						data : 'carType',
						title : 'Car Type',
						type : 'readonly'
					}, {
						data : 'bookingAmount',
						title : 'Booking Amount',
						type : 'readonly'
					} ];

					var myTable;

					myTable = $('#reservationDetailsTable')
							.DataTable(
									{
										'sPaginationType' : 'full_numbers',
										ajax : {
											url : '/getCustomerReservation?clientId='
													+ $("#clientId").val(),
											dataSrc : '',
											error : function(xhr, result,
													status) {
												if (xhr.status != 200) {
													alert("You Have Encountered an Unexpected Error. Please Contact System Administrator for Assistance");
												}
											}
										},
										columns : columnDefs,
										dom : 'Bfrtip',
										select : 'single',
										responsive : true,
										altEditor : true,
										buttons : [ {
											extend : 'selected',
											text : 'Edit',
											name : 'edit'
										}, {
											extend : 'selected',
											text : 'Delete',
											name : 'delete'
										}, {
											text : 'Refresh',
											name : 'refresh'
										} ],
										onDeleteRow : function(datatable,
												rowdata, success, error) {
											var deleteReservation = {
												idReservation : rowdata.idReservation
											};
											$
													.ajax(
															{
																url : '/deleteReservation',
																type : 'POST',
																data : JSON
																		.stringify(deleteReservation),
																contentType : 'application/json; charset=utf-8'
															})
													.done(
															function(result) {

																$(
																		'#altEditor-modal-')
																		.modal(
																				'hide');

																$(
																		'#reservationDetailsTable')
																		.DataTable().ajax
																		.reload();
															})
													.fail(
															function(xhr,
																	result,
																	status) {
																alert('Delete reservation '
																		+ xhr.statusText
																		+ ' '
																		+ xhr.responseText
																		+ ' '
																		+ xhr.status);
															});
										},
										onEditRow : function(datatable,
												rowdata, success, error) {
											var editReservationRecord = {
												startDate : rowdata.initDate,
												endDate : rowdata.finalDate,
												officePickCode : rowdata.pickupLoc,
												officeReturnCode : rowdata.dropoffLoc,
												idReservation : rowdata.idReservation
											};

											$
													.ajax(
															{
																url : '/updateReservation',
																type : 'POST',
																data : JSON
																		.stringify(editReservationRecord),
																contentType : 'application/json; charset=utf-8'

															})
													.done(
															function(result) {

																$(
																		'#altEditor-modal-')
																		.modal(
																				'hide');
																$(
																		'#reservationDetailsTable')
																		.DataTable().ajax
																		.reload();
															})
													.fail(
															function(xhr,
																	result,
																	status) {
																alert('Edit Reservation '
																		+ xhr.statusText
																		+ ' '
																		+ xhr.responseText
																		+ ' '
																		+ xhr.status);
															});
										}
									});

				});