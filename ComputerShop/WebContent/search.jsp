<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		
		<script src="/jQuery/jquery-1.11.0.js">
		</script>
		<script>
		$(document).ready(function() {
			
			//var components = $('input#components').val(); 
			//var devices = $('input#devices').val();
			
			var components = new Array($('input#components').val()); 
			var devices = new Array($('input#devices').val());
			
		    $('.for_components').hide(); 
		    
		    $('#first_pick').change(function(){
		        if($('#first_pick').val() == 'Devices') {
		            $('.for_devices').show(); 
		            $('.for_components').hide();
		        } else {
		            $('.for_devices').hide(); 
		            $('.for_components').show();
		        } 
		    });
		    
		    $("button#search").click(function(){
		    	
		    	var name = $('input#name').val();
		    	var description = $('input#description').val();
		    	
		    	if ($('#first_pick').val() == 'Devices') {
		    		var selectedComponents = new Array($('#components').val());
		    		
		    		for (var i=0; i<devices.length; i++) {
		    		
		    		}
		    	
		    	} else {
		    		var priceMin = new Array($('#price_min').val());
		    		var priceMax = new Array($('#price_max').val());
		    		var quantityMin = new Array($('#quantity_min').val());
		    		var quantityMax = new Array($('#quantity_max').val());
		    		var category = new Array($('#category').val());
		    		
		    	}
		    	
		    	

		    	
		    	
		    	
		    	
		    	for (var i = 0; i < data.length; i++) {
		    		
		    		//   APPEND TO TABLE - RESULT !
		    		//$("div#d_1").append('<div id="article_'+i.toString()+'"><div>'+data[i].title+'</div><div>'+    	        		
		    	    //   		data[i].description+'</div><div>'+data[i].text+'</div></div><br/>');
	    	    }  
	    	});
		    
		    $('#name_sort').click(function(){
		        
		    });
		   
	    });
	
		
	
		    
	
				
				
				
				
				
				
				
		 
		</script>
	</head>
	
	<body>
	
		<table> 
			<tr>
				<td>Search: <select name="first_pick"> 
							<option> Categories</option>
							<option> Components</option>
							</select>
				</td>	
				
				<td> Name: <input id="name" type="text"> <button id="name_sort">sort by name</button> </td>		
				<td> Description: <input id="description" type="text"> </td>
				<td class="for_components"> Price range (min): <input id="price_min" type="number"> </td>		
				<td class="for_components"> Price range (max): <input id="price_max" type="number"> </td>
				<td class="for_components"> Available quantity (min): <input id="quantity_min" type="number"> </td>
				<td class="for_components"> Available quantity (max): <input id="quantity_max" type="number"> </td>
				<td class="for_components"> Category: <select id="category"> 
													  	<c:forEach items="${categories}" var="cat" >
															<option value="${cat.name}">${cat.name}</option>
														</c:forEach>	
													  </select> </td>		
						
						
				<td class="for_devices"> Components: <select multiple id="components"> 
													  	<c:forEach items="${components}" var="comp" >
															<option value="${comp.name}">${comp.name}</option>
														</c:forEach>	
													  </select> </td>		
						
				<td> <button id="search"> GO FETCH! </button> </td>
			</tr>
		</table>
		
		<table id="results">
		</table>			
		
		<input type="hidden" id="components" value="${components}">
		<input type="hidden" id="devices" value="${devices}">
	</body>
</html>