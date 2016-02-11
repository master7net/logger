appControllers.controller('ConsoleCtrl', ['$scope', '$http', '$interval',
    function ($scope, $http, $interval) {

        $scope.block = false;
        $scope.thread = '';
        $scope.level = 'ALL';
        $scope.grep = '';
        $scope.limit = 50;
        $scope.refresh = 0;
        $scope.skip = 0;
        $scope.autoScrolling = true;
        $scope.levels =  [
                            'ALL',
                            'FATAL',
                            'ERROR',
                            'WARN',
                            'INFO',
                            'DEBUG',
                            'TRACE'
                          ];

        $scope.load = function() {

             if (!$scope.block) {

                $scope.block = true;

                var request = {};
                request.threadName = $scope.thread;
                request.level = $scope.level;
                request.grep = $scope.grep;
                request.limit = $scope.limit;
                request.refresh = $scope.refresh;
                request.skip = $scope.skip;

                $http({
                    url: '/rest/log/',
                    method: "PUT",
                    data : request,
                        headers: {
                               'Content-type' : 'application/json'
                        }
                }).success(function (data) {

                    angular.forEach(data,function(message,index){

                        var logNode = document.createElement("div");
                        logNode.setAttribute("layout", "row");
                        logNode.setAttribute("class", "log");

                        var timeNode = document.createElement("div");
                        timeNode.setAttribute("class", "time");
                        timeNode.innerHTML =
                            message.timestamp.dayOfMonth + '.' +
                            message.timestamp.month.toLowerCase() + '.' +
                            message.timestamp.year + ' ' +
                            message.timestamp.hour + ':' +
                            message.timestamp.minute + ':' +
                            message.timestamp.second;
                        logNode.appendChild(timeNode)

                        var levelNode = document.createElement("div");
                        levelNode.innerHTML = message.level;
                        levelNode.setAttribute("class", "level");
                        logNode.appendChild(levelNode);

                        var collumnNode = document.createElement("div");
                        collumnNode.setAttribute("layout", "column");

                        var rowNode = document.createElement("div");
                        rowNode.setAttribute("layout", "row");

                        var nameNode = document.createElement("div");
                        nameNode.innerHTML = message.thread + "@" + message.loggerName.fullyQualifiedClassName + '.' + message.method + ':' + message.lineNumber;
                        rowNode.appendChild(nameNode);

                        collumnNode.appendChild(rowNode);

                        var messageNode = document.createElement("div");
                        messageNode.innerHTML = message.message;
                        if (message.message.length < 40) {
                            rowNode.appendChild(messageNode);
                            logNode.appendChild(collumnNode);
                            document.getElementById('console').appendChild(logNode);
                        }
                        else {
                            logNode.appendChild(collumnNode);
                            messageNode.setAttribute("class", "data");
                            document.getElementById('console').appendChild(logNode);
                            document.getElementById('console').appendChild(messageNode);
                        }


                        angular.forEach(message.throwables, function(throwable, throwablesIndex){

                            var throwableNode = document.createElement("div");
                            throwableNode.setAttribute("class", "data");
                            throwableNode.innerHTML = throwable.message;
                            document.getElementById('console').appendChild(throwableNode);

                            angular.forEach(throwable.stackTrace,function(stackTrace, stackTraceIndex){
                                var stackTraceNode = document.createElement("div");
                                stackTraceNode.setAttribute("class", "data");
                                stackTraceNode.innerHTML = stackTrace.className.fullyQualifiedClassName + "." + stackTrace.method + ":" + stackTrace.lineNumber + " (" + stackTrace.fileName + ")<br />";
                                document.getElementById('console').appendChild(stackTraceNode);
                            });

                        });

                        if ($scope.autoScrolling) {
                            window.scrollTo(0, document.body.scrollHeight);
                        }
                    });
                });
                $scope.refresh = 0;
                $scope.block = false;
            }
        }

        $scope.Timer = $interval(function () {
                $scope.load();
        }, 1000);

        $scope.reloadLog = function() {
            document.getElementById('console').innerHTML = '';
            $scope.refresh = 1;
            $scope.limit = 50;
        }

        $scope.clearConsole = function() {
            document.getElementById('console').innerHTML = '';
            $scope.limit = 50;
        }

        $scope.more = function() {
            document.getElementById('console').innerHTML = '';
            $scope.autoScrolling = false;
            $scope.refresh = 1;
            $scope.limit = $scope.limit + 50;
            $scope.load();
        }
        $scope.exit = function() {
            window.location.href = '/logout';
        }
}]);