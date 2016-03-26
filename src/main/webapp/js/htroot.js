var dataTableMap = {};

/**
 * Recibe un String como "id;nombre;apellido" y debe transformar en un
 * diccionario {"data":"id", "data":"nombre", "data":"apellido"}
 */
function getDictColumns(columnsStr) {

 var arrayCol = columnsStr.split(";");

 var dictColumns = [];

 jQuery.each(arrayCol, function(i, val) {
  var item = {
   "data" : val
  };
  dictColumns.push(item);
 });
 return dictColumns;
}

function escapeHtml(string) {
	var entityMap = {
            "&": "&amp;",
            "<": "&lt;",
            ">": "&gt;",
            '"': '&quot;',
            "'": '&#39;',
            "/": '&#x2F;'
   }; 
 return String(string).replace(/[&"'\/]/g, function(s) {
  return entityMap[s];
 });
}

function createDataTableAncestor(name, serverSide, source, columnsStr,
  dataColumn, lenguaje) {

 var dictColumnas = getDictColumns(columnsStr);

 var mServerSide = false;

 if (serverSide == 'true') {
  mServerSide = true;
 }

 var tabla = $('#' + name).dataTable({
  "processing" : true,
  "serverSide" : mServerSide,
  /**
   * "bLengthChange": false, No mostrar para elegir cantidad de filas a
   * mostrar
   */
  "searchDelay" : 2000,
  "stateSave" : true,
  "bRetrieve" : true,
  "sAjaxSource" : source,
  "columns" : dictColumnas,
  "language" : lenguaje,
  "fnServerParams" : function(aoData) {
   aoData.push({
    "name" : "param1",
    "value" : $("#" + name).attr('param1')
   });
   aoData.push({
    "name" : "param2",
    "value" : $("#" + name).attr('param2')
   });
   aoData.push({
    "name" : "param3",
    "value" : $("#" + name).attr('param3')
   });
  },
  "fnServerData" : function(sSource, aoData, fnCallback, oSettings) {
   oSettings.jqXHR = $.ajax({
    "dataType" : 'json',
    "type" : "GET",
    "url" : sSource,
    "data" : aoData,
    "success" : function(data) {
     $.each(data.aaData, function(index, item) {
      $.each(item, function(key, value) {
       item[key.valueOf()] = escapeHtml(value);
      });
     });
     fnCallback(data);
    }
   });
  }
 });
 tabla.addClass('datatable');
 dataTableMap[name] = tabla;

}

function createPickerTable(pickerId, serverSide, source, columnsStr, fieldId,
  fieldValue, idColumnNumber, valueColumnNumber, lenguaje) {
 /*
  * - pickerId: el id único asignado al picker. Ej: usuario_picker -
  * serverSide: si el filtrado de datos se hará con javascript o mediante sql
  * en el servidor (cada vez que cambie el input filter). Ej: true -
  * columnsStr: las columnas de la tabla. Ej: "id;codigo;nombre;apellido" -
  * fieldId: El id del input(generalmente hidden) al que queremos asignar el
  * id del registro seleccionado. Ej: usuario_id - fieldValue: El id del
  * input(generalmente tipo text) en el que queremos mostrar el registro
  * seleccionado. Ej: usuario_nom_ape - idColumnNumber: El número de columna
  * de la tabla que queremos usar como ID. Si tenemos las columnas ID,
  * CODIGO, DESCRIPCION, usamos cero puesto que el campo ID queremos guardar -
  * valueColumnNumber: El número de columna de la tabla cuyo valor queremos
  * mostrar al usuario. Si tenemos las columnas ID, CODIGO, DESCRIPCION,
  * usamos 2 puesto que el campo DESCRIPCION queremos mostrar
  */
 var tableId = pickerId + 'DT';
 createDataTableAncestor(tableId, serverSide, source, columnsStr,
   idColumnNumber, lenguaje);
 $('#' + tableId).removeClass('table-striped display');

 $('#' + tableId + ' tbody').on('click', 'tr', function() {
  var rowId = $('td', this).eq(idColumnNumber).text();
  $('#' + fieldId).val(rowId);
  var columnValue = $('td', this).eq(valueColumnNumber).text();
  $('#' + fieldValue).val(columnValue);
  if (!$(this).hasClass('selected') ) { 
     $('#'+tableId).DataTable().$('tr.selected').removeClass('selected');
        $(this).addClass('selected'); 
     }
 });

 $('#' + pickerId + '_btn').click(function() {

 });
}

function createDataTable(name, serverSide, source, columnsStr, editUrl,
		editColumnNumber, lenguaje, withoutId, reportUrl) {
	createDataTableAncestor(name, serverSide, source, columnsStr, 1, lenguaje);
	var tabla = $('#' + name).dataTable();
	if (withoutId != "true" && withoutId != true) {
		tabla.fnSetColumnVis(0, false);
	}
	
	//por default editUrl se usa tambien para el reporte a menos que se especifique en reportUrl
	if(editUrl !== undefined && editUrl !== "null" && editUrl !== null){
		$('#' + name + ' tbody').on('click', 'tr', function() {
			var fid = tabla.fnGetData(this, 0);
			if (isNaN(fid)) {
				return;
			}
			window.location.href = editUrl + fid;
		});
		$('#' + name + '_pdf').click(function() {

			var filter = $('#' + name + '_filter input').val();
			window.location.href = editUrl + 'getpdf?sSearch=' + filter

		});
	}
	if(reportUrl !== undefined && reportUrl !== "null" && reportUrl !== null){
		if(reportUrl != "false" && reportUrl != false){
			$('#' + name + '_pdf').click(function() {
				var filter = $('#' + name + '_filter input').val();
				window.location.href = reportUrl + 'getpdf?sSearch=' + filter
			});
		}else{//no se quiere el boton de reporte
			$('#' + name + '_pdf').remove();
		}
	}
}

function actualizarRoles() {

 var formRoles = $('#rolPickerForm');

 formRoles.submit(function(ev) {
  $.ajax({
   type : formRoles.attr('method'),
   url : formRoles.attr('action'),
   data : formRoles.serialize(), // serializes the form's elements.
   success : function(data) {
    // alert('Roles Actualizados');
   }
  });

  ev.preventDefault();
 });
 formRoles.submit();
}

function create_suggestbox(field, suggestId, source, idName, displayName,
  idValue, displayValue, required) {

 var suggest = $('#' + suggestId).magicSuggest({
  data : function() {
   var dataEscaped;
   $.ajax({
    "dataType" : 'json',
    "type" : "GET",
    "url" : source,
    "async" : false,
    "success" : function(data) {
     $.each(data, function(index, item) {
      $.each(item, function(key, value) {
       item[key.valueOf()] = escapeHtml(value);
      });
     });
     dataEscaped = data;
    }
   });
   return dataEscaped;
  },
  valueField : idName,
  method : 'get',
  displayField : displayName,
  queryParam : 'sSearch',
  selectFirst : true,
  required : required,
  maxSelection : 1,
  placeholder : 'Ingrese texto para buscar',
 });

 if (idValue != null && idValue != "null") {
  var data = {};
  data[idName] = idValue;
  data[displayName] = displayValue;
  $("#" + field).val(idValue);
  suggest.addToSelection([ data ]);
  suggest.setValue(data);
 }

 $($('#' + suggestId).magicSuggest()).on('selectionchange', function(e, m) {

  var value = this.getValue();

  $("#" + field).val(value);
 });

 return suggest;
}

function isEmpty(value) {

 return value == null || value.trim() == "";
}

function checkSize(fileId, maxSize){
    var input = document.getElementById(fileId);
    var file = input.files[0];
    
    if(file.size > maxSize){
    	$("#notificaciones").html('<div class="alert alert-danger alert-dismissable">'+
    			'<button type="button" class="close" data-dismiss="alert">×</button>'+
    			'<div>'+$('#'+fileId).attr("size-error")+'</div></div>');
    	return false;
    }
    
}


function previsualizar(input, imgId) {

    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            $(imgId).attr('src', e.target.result);
        }

        reader.readAsDataURL(input.files[0]);
    }
}

function borrarHtml(id){
	$("#"+id).remove();
}


function actualizarUsuarios() {

	 var formRoles = $('#usuariosPickerForm');

	 formRoles.submit(function(ev) {
	  $.ajax({
	   type : formRoles.attr('method'),
	   url : formRoles.attr('action'),
	   data : formRoles.serialize(), // serializes the form's elements.
	   success : function(data) {
	    // alert('Roles Actualizados');
	   }
	  });

	  ev.preventDefault();
	 });
	 formRoles.submit();
	}


//si no hay JSON.stringify
function serializer(obj) {

	var t = typeof (obj);
	if (t != "object" || obj === null) {

		// simple data type
		if (t == "string") obj = '"'+obj+'"';
		return String(obj);

	}
	else {

		// recurse array or object
		var n, v, json = [], arr = (obj && obj.constructor == Array);

		for (n in obj) {
			v = obj[n]; t = typeof(v);

			if (t == "string") v = '"'+v+'"';
			else if (t == "object" && v !== null) v = JSON.stringify(v);

			json.push((arr ? "" : '"' + n + '":') + String(v));
		}

		return (arr ? "[" : "{") + String(json) + (arr ? "]" : "}");
	}
};



