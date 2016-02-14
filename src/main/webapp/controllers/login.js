appControllers.controller('LoginCtrl', ['$scope', '$http', '$mdToast', '$document',
    function($scope, $http, $mdToast, $document) {

            $scope.doLogin = function() {
                $http({
                    method: 'POST',
                    url: 'login',
                    data: 'username=' + $scope.username + '&password=' + $scope.password,
                    headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                }).success(function (data) {
                    window.location.href = 'index.html#/console';
                }).error(function (data, status, headers, config) {
                  $scope.username = "";
                  $scope.password = "";
                  $mdToast.show({
                      templateUrl: 'templates/toast.html',
                      position: 'top right',
                      hideDelay: 5000
                    });

                });
            };

    }]);