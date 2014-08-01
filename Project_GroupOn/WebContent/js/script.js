var routing_module = angular.module('routing_module', [ 'ngRoute' ]);

routing_module.config([ '$routeProvider', function($routeProvider) {
	$routeProvider

	// route for the home page
	.when('/', {
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	})

	// route for the about page
	.when('/login', {
		templateUrl : 'pages/login.html',
		controller : 'loginController'
	})
	.when('/travel', {
		templateUrl : 'pages/travel.html',
		controller : 'mainController'
	})

	// route for the contact page
	.when('/signup', {
		templateUrl : 'pages/signup.html',
		controller : 'contactController'
	}).when('/addoffer', {
		templateUrl : 'pages/addOffer.html',
		controller : 'contactController'
	}).otherwise({
		templateUrl : 'pages/home.html',
		controller : 'mainController'
	});
} ]);

// create the controller and inject Angular's $scope
routing_module
		.controller(
				'mainController',
				function($scope, $http) {
					// create a message to display in our view
					$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
					var getOffers = function() {
						$http({
							url : 'EnterData',
							method : 'GET',
					         params : {
						          formtype : "home_page"
						         },
							headers : {
								'Content-Type' : 'application/x-www-form-urlencoded'
							}
						})
						.success(function(result) {

							$scope.offers = result;
						});
					};
					getOffers();
				});
routing_module.controller('aboutController',function($scope,$http,$location){
	$scope.login = function() {
	      $http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
	      $http(
	        {
	         url : 'service/login',
	         method : 'POST',
	         data : {
	          email : $scope.email,
	          password : $scope.password
	         },
	         headers : {
	          'Content-Type' : 'application/x-www-form-urlencoded'
	         }
	        })
	        .success(
	          function(result) {
	           if (result.status == "success") {
	            console.log(result.result.name);
	            $cookieStore.put("usertype",
	              result.result.usetype);
	            $cookieStore.put("email",
	              result.result.email);
	            $cookieStore.put("name",
	              result.result.name);

	            if (result.result.usetype == "corporate") {
	             $location
	               .path('/corporatedashboard');
	            } else {
	             $location.path('/home');
	            }

	           } else {
	            $scope.alert.showAlert = true;
	            $scope.alert.msg = result.message;
	           }
	          });
	};
	
});
