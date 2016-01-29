appControllers.controller('LoginCtrl', ['$scope', '$http', '$mdToast', '$document',
    function($scope, $http, $mdToast, $document) {

                 var last = {
                  bottom: false,
                  top: true,
                  left: false,
                  right: true
                };
              $scope.toastPosition = angular.extend({},last);
              $scope.getToastPosition = function() {
                sanitizePosition();
                return Object.keys($scope.toastPosition)
                  .filter(function(pos) { return $scope.toastPosition[pos]; })
                  .join(' ');
              };

            $scope.showSimpleToast = function() {
            $mdToast.show(
              $mdToast.simple()
                .textContent('Simple Toast!')
                .position($scope.getToastPosition())
                .hideDelay(3000)
            );
           };

            $scope.doLogin = function() {
                $http({
                    method: 'POST',
                    url: '/login',
                    data: 'username=' + $scope.username + '&password=' + $scope.password,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success(function (data) {
                    window.location.href = 'index.html#/console';
                }).error(function (data, status, headers, config) {
                  $scope.username = "";
                  $scope.password = "";
                  $mdToast.show({
                      templateUrl: 'templates/toast.html',
                      hideDelay: 5000
                    });

                });
            };

    }]);