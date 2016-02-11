app.directive('ngEnter', function() {
        return function(scope, element, attrs) {
            element.bind("keydown keypress", function(event) {
                if(event.which === 13) {
                    scope.$apply(function(){
                        scope.$eval(attrs.ngEnter, {'event': event});
                    });
                    event.preventDefault();
                }
            });
        };
    });

app.directive('nginputstyle', function () {
       return {
               link: function(scope, element, attributes){
               element.css('color', '#fff'),
               element.css('font-weight', '100'),
               element.css('border-bottom-color', '#ffffff');
             }
        }
   });

app.directive('ngselectstyle', function () {
       return {
               link: function(scope, element, attributes){
               element.css('color', '#fff'),
               element.css('font-weight', '100'),
               element.css('background', '#1e1e1e');
               element.css('border-bottom-color', '#ffffff');
             }
        }
   });