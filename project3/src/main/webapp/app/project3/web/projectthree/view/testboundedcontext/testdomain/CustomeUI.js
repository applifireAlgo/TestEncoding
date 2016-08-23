Ext.define('Project3.project3.web.projectthree.view.testboundedcontext.testdomain.CustomeUI', {
     "xtype": "customeUIView",
     "items": [{
          "xtype": "hiddenfield",
          "fieldLabel": "issueId",
          "bindable": "issueId",
          "margin": 5,
          "name": "issueId",
          "columnWidth": 0.5,
          "itemId": "issueId_hiddenfield"
     }, {
          "xtype": "textfield",
          "fieldLabel": "issueName",
          "margin": 5,
          "bindable": "issueName",
          "name": "issueName",
          "columnWidth": 0.5,
          "itemId": "issueName_textfield"
     }, {
          "xtype": "textfield",
          "fieldLabel": "component",
          "margin": 5,
          "bindable": "component",
          "name": "component",
          "columnWidth": 0.5,
          "itemId": "component_textfield"
     }, {
          "xtype": "button",
          "name": "ds",
          "text": "Button",
          "margin": 5,
          "scale": "medium",
          "columnWidth": 0.5,
          "itemId": "ds_button",
          "listeners": {
               "click": "onDsClick"
          }
     }],
     "layout": "column",
     "autoScroll": true,
     "border": true,
     "title": "Column Layout",
     "margin": 5,
     "itemId": "panel_ext_44545",
     "dockedItems": [],
     "extend": "Ext.panel.Panel",
     "requires": ["Project3.project3.web.projectthree.controller.testboundedcontext.testdomain.CustomeUIController", "Project3.project3.shared.projectthree.viewmodel.testboundedcontext.testdomain.CustomeUIViewModel", "Project3.project3.shared.projectthree.model.testboundedcontext.testdomain.CustomeUIModel"],
     "viewModel": "CustomeUIViewModel",
     "controller": "CustomeUIController"
});