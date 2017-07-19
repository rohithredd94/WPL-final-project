var app = angular.module('MoviesRating', ['ngRoute']);

app.config(['$routeProvider', function($routeProvider){
    $routeProvider
        .when('/login', {
            templateUrl: 'partials/login.html'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);