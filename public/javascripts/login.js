var app = angular.module('MoviesRating', ['ngResource','ngRoute']);

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
            controller: 'LoginCtrl'
        })
        .when('/main', {
            templateUrl: 'partials/main.html',
            //controller: 'MainCtrl'
        })
        .otherwise({
            redirectTo: '/'
        });
}]);

app.controller('LoginCtrl', ['$scope', '$resource', 
    function($scope, $resource){
        console.log("Inside controller");
        var Users = $resource('/api/users');
        Users.query(function(users){
            $scope.users = users;
        });
    }
]);
