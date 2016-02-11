'use strict';

/* App Module */

var app = angular.module('App', [
    'ngRoute',
    'appControllers',
    'ngMaterial'
]);

app.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
        when('/login', {
            templateUrl: 'templates/login.html',
            controller: 'LoginCtrl'
        }).
        when('/console', {
            templateUrl: 'templates/console.html',
            controller: 'ConsoleCtrl'
        }).
        otherwise({
            redirectTo: '/login'
        });
    }

    ]);