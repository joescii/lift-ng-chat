angular.module('LiftChat', ['ChatServices'])

.controller('ChatController', ['$scope', 'chatService',
    function($scope, chatSvc){
  $scope.onSend = function() {
    chatSvc.sendChat($scope.chatInput);
    $scope.chatInput = '';
  }
  $scope.chat = {
    msgs: ['1', '2', 'angular', 'hola']
  }
}])

;