<!--  
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<body>
	<h1>Title : ${title}</h1>
	<h1>Message : ${message}</h1>

	<c:url value="/logout" var="logoutUrl" />
	<form action="${logoutUrl}" method="post" id="logoutForm">
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</form>
	<script>
		function formSubmit() {
			document.getElementById("logoutForm").submit();
		}
	</script>

	<c:if test="${pageContext.request.userPrincipal.name != null}">
		<h2>
			Welcome : ${pageContext.request.userPrincipal.name} | <a
				href="javascript:formSubmit()"> Logout</a>
		</h2>
	</c:if>

</body>
</html>
-->

<!DOCTYPE html>
<html>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js"></script>
<body>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script>
    jQuery(document).ready(function() {
      $("#shit").click(function(event) {
       alert("sht");
        // Prevent the form from submitting via the browser.
        event.preventDefault();
        searchAjax();
    
      });
    });
    
    function searchAjax() {
      var data = {id:10,name:"fdsfd",description:"shit"};
    
      $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "http://localhost:8080/petproject/createdish",
        data : JSON.stringify(data),
        dataType : 'json',
        timeout : 100000,
        success : function(data) {
          console.log("SUCCESS: ", data);
          display(data);
        },
        error : function(e) {
          console.log("ERROR: ", e);
          display(e);
        },
        done : function(e) {
          console.log("DONE");
        }
      });
    }
    </script>

<div ng-app="myApp" ng-controller="myCtrl"> 

<p>Today's welcome message is:</p>

<h1>{{myWelcome}}</h1>


<table>
    <tr>
       
        <th> Name</th>
        <th>Staff</th>
    </tr>
    <tr ng-repeat="tour in myWelcome">
        
        <td> <input ng-model="tour.tourname"></td>
        <td>{{tour.price}}</td>
    </tr>
</table>


<button ng-click="myFunc()">OK</button>
</div>


<script>
var app = angular.module('myApp', []);
app.controller('myCtrl', function($scope, $http) {
	
	 $scope.myWelcome ='lol'; 
     var a = 12;
  
  
     
     
     
  $http({
	  method: 'GET',
	  url: 'http://localhost:8080/petproject/name'
	}).then(function successCallback(response) {
		$scope.myWelcome =response.data;
		
		
	
		  
		
		
	  }, function errorCallback(response) {
		  console.log(response);
	  });
  
  
  $scope.myFunc = function() {
	  $http({
		  method: 'GET',
		  url: 'http://localhost:8080/petproject/param?id='+a
		}).then(function successCallback(response) {
			
			
		  }, function errorCallback(response) {
			  console.log(response);
		  });  };
  
	
  
});
</script>
  <button id="shit">ds</button>

</body>
</html>
