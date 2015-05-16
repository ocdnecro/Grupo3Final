/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


(function(){var aplicacionHospital= angular.module('aplicacionHospital',[]);
    
    
                   
    aplicacionHospital.directive('toolbar', function(){
        return{
            restrict:'E',
            templateUrl: 'partials/toolbar.html',
            controller:function(){
                this.tab=0;
                this.occ = "hide";
                this.selectTab=function(setTab){
                    this.tab=setTab;
                };
                this.isSelected=function(tabParam){
                    return this.tab===tabParam;
                };
            },
            controllerAs:'toolbar'
        };
    });
    
     aplicacionHospital.directive('pacientesForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/pacientes-form.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                self.competitor={};
                self.var = 0;
                
                this.isPreguntado=function(){
                    return true;
                };
                
                this.verEpiPacientes=function(id){    
                    console.log("poiuy");
                    self.pa = {id: "54FD3C141AEF28D54A9B2F3D", fecha: "201001011213", cedula: 1, localizacion: "Frente", alivio: 0.0, intensidad: 0.0, nivelDolor: 10.0};
                   self.competitors = [{id: "54FD3C141AEF28D54A9B2F3D", fecha: "201001011213", cedula: 1, localizacion: "Frente", alivio: 0.0, intensidad: 0.0, nivelDolor: 10.0}];
                   console.log("va a mirar paa"+ self.pa.id);
                   var head = {'Autorization': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzdWFyaW8xIiwicGFzc3dvcmQiOiJVc3VhcmlvMSIsImVtYWlsIjoidXN1YXJpbzFAY2xpbmljYS5jb20iLCJuYW1lIjoiVXN1YXJpbzEgQXBlbGxpZG9Vc3VhcmlvMSIsImxldmVsQWNjZXNzIjoiVXNlciJ9.LYgLvJ79kenLFlyfaRVib3_aZ56iiD59oqCksUZMC-w'};
                   $http.get('http://172.24.99.155:8080/Doctor/consultarEpisodiosPaciente/11/'+id, {    headers: {'x_rest_user': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzdWFyaW8xIiwicGFzc3dvcmQiOiJVc3VhcmlvMSIsImVtYWlsIjoidXN1YXJpbzFAY2xpbmljYS5jb20iLCJuYW1lIjoiVXN1YXJpbzEgQXBlbGxpZG9Vc3VhcmlvMSIsImxldmVsQWNjZXNzIjoiVXNlciJ9.LYgLvJ79kenLFlyfaRVib3_aZ56iiD59oqCksUZMC-w'}}).success(function(data){
                        self.competitors=data;
                        this.nohash = this.competitors.
                         toolbar.occ = "active";
                         console.log("va a mirar pa"+ self.pa.id);
                    });
                    return self.competitors;
                };
                
                this.hashear=function(){
                    
                }
                
            }],
            controllerAs:'pacientesCtrl'
        };
    });
    
    aplicacionHospital.directive('pacientesFechaForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/pacientes-fecha-form.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                self.competitor={};
                self.var = 0;
                this.verEpiPacientes=function(id,feI,feF){
                    $http.get('http://localhost:8080/Doctor/consultarEpisodiosPaciente/11/'+id+'/'+feI+'/'+feF, {    headers: {'x_rest_user': 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6InVzdWFyaW8xIiwicGFzc3dvcmQiOiJVc3VhcmlvMSIsImVtYWlsIjoidXN1YXJpbzFAY2xpbmljYS5jb20iLCJuYW1lIjoiVXN1YXJpbzEgQXBlbGxpZG9Vc3VhcmlvMSIsImxldmVsQWNjZXNzIjoiVXNlciJ9.LYgLvJ79kenLFlyfaRVib3_aZ56iiD59oqCksUZMC-w'}}).success(function(data){
                        self.competitors=data;
                         toolbar.occ = "active";
                    });
                    return self.competitors;
                };
            }],
            controllerAs:'pacientesFechaCtrl'
        };
    });
    
    aplicacionHospital.directive('pacienteForm', function(){
        return{
            restrict:'E',
            templateUrl:'partials/paciente-form.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                self.competitor={};
                self.var = 0;
                this.verEpiPacientes=function(ced, id){
                    $http.get('http://app-clinica-arquisoft-g3.herokuapp.com​/Paciente/obtenerEpisodios/'+ced+'/'+id).success(function(data){
                        self.competitors=data;
                         toolbar.occ = "active";
                    });
                   return self.competitors;
                };
            }],
            controllerAs:'pacienteCtrl'
        };
    });
    
    aplicacionHospital.directive('prueba', function(){
        return{
            restrict:'E',
            templateUrl:'partials/prueba.html',
            controller: ['$http',function($http){
                var self=this;
                
                this.pruebap=function(){
                    toolbar.occ = "active";
                };
            }],
            controllerAs:'pCtrl'
        };
    });
    
    aplicacionHospital.directive('pacientesInfo', function(){
        return{
            restrict:'E',
            templateUrl:'partials/pacientes-info.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                
                this.verEpiPacientes=function(){                     
                    $http.get('app-clinica-arquisoft-g3.herokuapp.com​/Doctor/consultarEpisodiosPaciente/11/01').success(function(data){
                        self.competitors=data;
                         console.log("poiuy");
                    });
                    return self.competitors;
                };
            }],
            controllerAs:'pacientesInCtrl'
        };
    });
    
    aplicacionHospital.directive('logIn', function(){
        return{
            restrict:'E',
            templateUrl:'partials/log-in.html',
            controller: ['$http',function($http){
                var self=this;
                self.competitors=[];
                self.competitor={};
                self.var = 0;
                this.doLogin=function(ced, id){
                    $http.post('http://172.24.99.167:8080/auth/login').success(function(data){
                        self.competitors=data;
                         toolbar.occ = "active";
                    });
                   return self.competitors;
                };
                
                
                
            }],
            controllerAs:'loginCtrl'
        };
    });
    
})();
