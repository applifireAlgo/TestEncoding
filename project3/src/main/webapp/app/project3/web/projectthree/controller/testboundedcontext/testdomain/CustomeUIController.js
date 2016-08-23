Ext.define('Project3.project3.web.projectthree.controller.testboundedcontext.testdomain.CustomeUIController', {
     extend: 'Project3.view.fw.frameworkController.FrameworkViewController',
     alias: 'controller.CustomeUIController',
     onDsClick: function(me, e, eOpts) {
          var componentArray = [this.view.down('#issueId_hiddenfield'), this.view.down('#issueName_textfield'), this.view.down('#component_textfield')];
          if (this.validateAndShowErrorMessages(me, componentArray, false)) {
               return;
          }
          var jsonData = {};
          jsonData.issueId = this.view.down('#issueId_hiddenfield').getValue();
          jsonData.issueName = this.view.down('#issueName_textfield').getValue();
          jsonData.component = this.view.down('#component_textfield').getValue();
          me.setDisabled(true);
          var scope = this.getView();
          Ext.Ajax.request({
               url: 'secure/CreateSampleSeviceWS/createSampleSevice',
               async: true,
               method: 'POST',
               sender: scope,
               jsonData: jsonData,
               me: me,
               maskEnable: true,
               maskEle: scope,
               success: function(response, scope) {
                    scope.me.setDisabled(false);
                    var responseText = Ext.JSON.decode(response.responseText);
                    if (responseText.response.success) {
                         Ext.Msg.alert('Server Response', 'Saved');
                    } else if (!sessionTimeOutFlag) {
                         scope.sender.controller.responseHandler(responseText);
                    }
               },
               failure: function(response, scope) {
                    scope.me.setDisabled(false);
                    if (!sessionTimeOutFlag) {
                         var responseText = Ext.JSON.decode(response.responseText);
                         scope.sender.controller.responseHandler(responseText);
                    }
               }
          }, scope);
     }
});