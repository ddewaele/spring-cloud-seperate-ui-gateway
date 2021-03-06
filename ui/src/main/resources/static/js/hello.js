angular.module('hello', [ 'ngRoute' ]).config(function($routeProvider) {

	$routeProvider.when('/', {
		templateUrl : 'home.html',
		controller : 'home',
		controllerAs : 'controller'
	}).otherwise('/');

}).controller('navigation',

function($rootScope, $http, $location, $route) {

	var self = this;

	self.tab = function(route) {
		return $route.current && route === $route.current.controller;
	};

	//$rootScope.$apply(
	$http.get('/user').success(function(data) {
		if (data.name) {
			$rootScope.authenticated = true;
		} else {
			$rootScope.authenticated = false;
		}

	}).error(function() {
		$rootScope.authenticated = false;

	})
	//);

	self.credentials = {};

	self.logout = function() {
		$http.post('logout', {}).finally(function() {
			$rootScope.authenticated = false;
			$location.path("/");
		});
	}

}).controller('home', function($http) {
	var self = this;

	$http({method: 'GET', url: '/resource/'
	}).then(function successCallback(response) {
		self.greeting = response;
	}, function errorCallback(response) {
		self.greeting = response;
	});




});
