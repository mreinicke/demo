angular.module('mainApp', ['ngRoute', 'ui.bootstrap', 'ui.grid', 'ui.grid.selection'])

    .config(function ($routeProvider, $httpProvider) {
        $routeProvider.when('/', {
            templateUrl: 'home.html',
            controller: 'homeCtrl'
        }).when('/login', {
            templateUrl: 'login.html',
            controller: 'navigationCtrl'
        }).otherwise('/');
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }).controller('tabsCtrl', function ($rootScope, $scope, $http, $interval, $log, $modal, orderfactory) {
        $rootScope.$watch('customerdata', function (newvalue, oldvalue) {
            $scope.customerData = $rootScope.customerData.customerList
        })
        $scope.setSelected = function (row) {
            var getOrderData = orderfactory.getData(row.customer.customerId);
            getOrderData.then(function(promise){
                var modalInstance = $modal.open({
                    animation: $scope.animationsEnabled,
                    templateUrl: 'myModalContent.html',
                    controller: 'ModalInstanceCtrl',
                    scope: $scope,
                    size: 'lg',
                    resolve: {
                        customerData: function () {
                            return promise.orderList;
                        }
                    }
                });
                modalInstance.result.then(function (selectedItem) {
                    $scope.selected = selectedItem;
                }, function () {
                    $log.info('Modal dismissed at: ' + new Date());
                });
            });
        };
    }).controller('ModalInstanceCtrl', function ($scope, $http, $modalInstance, customerData) {

        $scope.orders = customerData;
        $scope.ok = function () {
            $modalInstance.close();
        };
        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
    }).controller('navigationCtrl', function ($rootScope, $scope, $http, $location, $route) {
        $scope.tab = function (route) {
            return $route.current && route === $route.current.controller;
        };
        $scope.isActive = function (viewLocation) {
            return viewLocation === $location.path();
        };
        $scope.logout = function () {
            $rootScope.authenticated = false;
            $location.path("/");
        };
    }).controller('homeCtrl', function ($rootScope, $scope, $http) {
        var xmlpayload = '';
        $http.get('/home/xmlresource').success(function (data) {
            $scope.xmlString = data.xmlString;
            xmlpayload = data.xmlString;

            var crespone = ''
            $.ajax({
                url: '/customers/customerdata',
                processData: false,
                type: "POST",
                data: xmlpayload,
                contentType: "application/xml",
                success: function (response) {
                    crespone = response
                    $rootScope.customerData = response;
                },
                error: function (response) {
                }
            });
        });
    }).controller('authenticateCtrl', function ($scope, $http, $location, $rootScope) {
        $scope.user = {};

        $scope.user.name = '';

        $scope.user.password = '';

        $scope.loginUser = function (user) {
            $scope.resetError();
            $http.post('login/authenticate', user).success(function (login) {
                if (login.sessionId === null) {
                    $scope.setError(login.status);
                    $rootScope.authenticated = false;
                    return;
                }
                $scope.user.email = '';
                $scope.user.password = '';
                $rootScope.token = login.sessionId;
                $rootScope.loginname = login.userName;
                $rootScope.authenticated = true;
                $location.path("/");
            }).error(function () {
                $scope.setError('Invalid user/password combination');
            });
        }
        $scope.resetError = function () {
            $scope.error = false;
            $scope.errorMessage = '';
        }
        $scope.setError = function (message) {
            $scope.error = true;
            $scope.errorMessage = message;
            $rootScope.SessionId = '';
            $rootScope.authenticated = false;
        }
    }).factory('orderfactory', function($http){
        var getData = function(id) {
            return $http({method:'GET', url:'/orders/orderdata/' + id}).then(function(result){
                return result.data;
            });
        };
        return { getData: getData };
    });

