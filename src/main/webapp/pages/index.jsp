<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
     <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Report Application</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-sRIl4kxILFvY47J16cr9ZwB07vP4J8+LH7qKQnuqkuIAvNWLzeN8tE5YBujZqJLB" crossorigin="anonymous">
  </head>
  <body>
  
  <div class="container">
  
  <h1 Class=" py-3">Insurance Report Generator</h1>
  
  <form:form  action="/citizen/search" method="post" modelAttribute="search">
  <table class="table table-responsive table-borderless">
   <tr class="px-0 ">
            <td >Plan Name:</td>
            <td >
             <form:select path="planName" class="form-control">
               		<form:option value="">-Select-</form:option>
                	<form:options items="${plans}"/>
             </form:select>
            </td>
            <td>Plan Status:</td>
              <td>
               <form:select path="planStatus" class="form-control">
                	<form:option value="">-Select-</form:option>
                	<form:options items="${Status}"/>
             </form:select>
              </td>
            <td>Gender:</td>
            <td>
               <form:select path="gender" class="form-control">
                	<form:option value="">-Select-</form:option>
                	<form:option value="Female">Female</form:option>
                    <form:option value="Male">Male</form:option>
                	
             </form:select>
              </td>
   </tr>
   
   <tr class="px-1">
            <td >Start Date: </td>
            <td ><form:input path="startDate"  type="Date" class="form-control" /></td>
            <td>End Date:</td>
            <td ><form:input path="endDate"  type="Date" class="form-control" /></td>
            
   </tr>
   
    <tr>
    
    	<td>       
			<a href="/citizen/" class="btn btn-secondary form-control" >Reset</a>
		</td>
		<td>       
			<form:button class="btn btn-primary form-control"> Submit</form:button>
		</td>
    </tr>
    
  </table>
  
  </form:form>
 
  <hr/>
  
      <table class="table table-responsive table-striped-columns table-hover table-bordered border-secondary">
      <thead class="table-primary">
         <tr>
             <th>SL.NO</th>
             <th>Citizen Name</th>
             <th>Gender</th>
             <th>Insurance Plan</th>
             <th>Plan Status</th>
             <th>Start Date</th>
             <th>End Date</th>
             <th>Benefit Amount</th>
             <th>Denial Reason</th>
         </tr>
      </thead>
      <tbody>
         
      </tbody>
       
          	<c:forEach items="${citizenData}" var="data" varStatus="slno">       
          		   <tr>
          		   		<td>${slno.count}</td>
          		   		<td>${data.citizenName}</td>
          		   		<td>${data.gender}</td>
          		   		<td>${data.planName}</td>
          		   		<td>${data.planStatus}</td>
          		   		<td>${data.startDate}</td>
          		   		<td>${data.endDate}</td>
          		   		<td>${data.benefitAmount}</td>
          		   		<td>${data.denialReason}</td>
          		   		
          		   </tr>
        		   
        	 </c:forEach>
        	 <c:if test="${empty citizenData}">
        	   <tr>
        	         <td colspan="9" style="text-align: center;">No Record Found</td>
        	   </tr>
        	 </c:if>
        		
         
      
      </table>   
   <hr/>
  </div>
  
    
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js" integrity="sha384-FKyoEForCGlyvwx9Hj09JcYn3nv7wiPVlz7YYwJrWVcXK/BmnVDxM+D2scQbITxI" crossorigin="anonymous"></script>
  </body>
</html>