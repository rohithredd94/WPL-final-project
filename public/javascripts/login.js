var app = angular.module('MoviesRating', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider){
    $routeProvider
        .when('/', {
            templateUrl: 'partials/home.html',
            //controller: 'HomeCtrl'
        })
        .when('/signup', {
            templateUrl: 'partials/signup.html',
            //controller: 'HomeCtrl'
        })
        .when('/login', {
            templateUrl: 'partials/login.html',
            //controller: 'HomeCtrl'
        })

        .otherwise({
            redirectTo: '/'
        });
}]);
