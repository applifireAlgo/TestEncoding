Ext.define('Project3.project3.web.projectthree.view.testboundedcontext.testdomain.IssueMain', {
     "xtype": "issueMainView",
     "extend": "Ext.tab.Panel",
     "customWidgetType": "vdTabLayout",
     "autoScroll": false,
     "controller": "IssueMainController",
     "restURL": "/Issue",
     "defaults": {
          "split": true
     },
     "requires": ["Project3.project3.shared.projectthree.model.testboundedcontext.testdomain.IssueModel", "Project3.project3.web.projectthree.controller.testboundedcontext.testdomain.IssueMainController", "Project3.project3.shared.projectthree.viewmodel.testboundedcontext.testdomain.IssueViewModel"],
     "communicationLog": [],
     "tabPosition": "bottom",
     "items": [{
          "title": "Data Browser",
          "layout": "border",
          "defaults": {
               "split": true
          },
          "autoScroll": false,
          "customWidgetType": "vdBorderLayout",
          "items": [{
               "xtype": "tabpanel",
               "customWidgetType": "vdTabLayout",
               "margin": "5 0 5 5",
               "border": 1,
               "style": {
                    "borderColor": "#f6f6f6",
                    "borderStyle": "solid",
                    "borderWidth": "1px"
               },
               "displayName": "Issue",
               "name": "IssueTreeContainer",
               "itemId": "IssueTreeContainer",
               "restURL": "/Issue",
               "autoScroll": false,
               "collapsible": true,
               "titleCollapse": true,
               "collapseMode": "header",
               "collapsed": false,
               "items": [{
                    "xtype": "treepanel",
                    "customWidgetType": "vdTree",
                    "title": "Browse",
                    "name": "entityTreePanel",
                    "useArrows": true,
                    "rootVisible": false,
                    "itemId": "IssueTree",
                    "listeners": {
                         "select": "treeClick"
                    },
                    "tbar": [{
                         "xtype": "triggerfield",
                         "customWidgetType": "vdTriggerField",
                         "emptyText": "Search",
                         "triggerCls": "",
                         "listeners": {
                              "change": "onTriggerfieldChange",
                              "buffer": 250
                         }
                    }, "->", {
                         "xtype": "tool",
                         "type": "refresh",
                         "tooltip": "Refresh Tree Data",
                         "handler": "onTreeRefreshClick"
                    }]
               }, {
                    "title": "Advance Search",
                    "xtype": "form",
                    "customWidgetType": "vdFormpanel",
                    "itemId": "queryPanel",
                    "buttons": [{
                         "text": "Filter",
                         "handler": "onFilterClick",
                         "name": "filterButton"
                    }],
                    "items": []
               }],
               "region": "west",
               "width": "20%"
          }, {
               "region": "center",
               "layout": "border",
               "defaults": {
                    "split": true
               },
               "customWidgetType": "vdBorderLayout",
               "items": [{
                    "customWidgetType": "vdFormpanel",
                    "xtype": "form",
                    "displayName": "Issue",
                    "title": "Issue",
                    "name": "Issue",
                    "itemId": "IssueForm",
                    "bodyPadding": 10,
                    "items": [{
                         "name": "issueId",
                         "itemId": "issueId",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "issueId",
                         "margin": "5 5 5 5",
                         "fieldLabel": "issueId<font color='red'> *<\/font>",
                         "fieldId": "4D93BAC5-F6AE-46C4-9F5C-DA9F6ACDCED7",
                         "minLength": "1",
                         "maxLength": "256",
                         "hidden": true,
                         "value": "",
                         "bindable": "issueId"
                    }, {
                         "name": "issueName",
                         "itemId": "issueName",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "issueName",
                         "margin": "5 5 5 5",
                         "fieldLabel": "issueName<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "7C75AAE1-B118-42EA-BC37-713241E94016",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "issueName",
                         "columnWidth": 0.5
                    }, {
                         "name": "component",
                         "itemId": "component",
                         "xtype": "textfield",
                         "customWidgetType": "vdTextfield",
                         "displayName": "Component",
                         "margin": "5 5 5 5",
                         "fieldLabel": "Component<font color='red'> *<\/font>",
                         "allowBlank": false,
                         "fieldId": "FAC363D2-D5FC-4BBF-B6E6-788F7C07285D",
                         "minLength": "1",
                         "maxLength": "256",
                         "bindable": "component",
                         "columnWidth": 0.5
                    }, {
                         "name": "versionId",
                         "itemId": "versionId",
                         "xtype": "numberfield",
                         "customWidgetType": "vdNumberfield",
                         "displayName": "versionId",
                         "margin": "5 5 5 5",
                         "value": "-1",
                         "fieldLabel": "versionId",
                         "fieldId": "59C10949-5A77-4EE8-AA93-7F9559627930",
                         "bindable": "versionId",
                         "hidden": true
                    }],
                    "layout": "column",
                    "defaults": {
                         "columnWidth": 0.5,
                         "labelAlign": "left",
                         "labelWidth": 200
                    },
                    "autoScroll": true,
                    "dockedItems": [{
                         "xtype ": "toolbar",
                         "customWidgetType": "vdBBar",
                         "dock": "bottom",
                         "ui": "footer",
                         "isToolBar": true,
                         "isDockedItem": true,
                         "parentId": 1,
                         "customId": 662,
                         "layout": {
                              "type": "hbox"
                         },
                         "items": [{
                              "xtype": "tbfill",
                              "customWidgetType": "vdTbFill",
                              "parentId": 662,
                              "customId": 806
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "saveFormButton",
                              "margin": 5,
                              "text": "Save",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "saveFormButton",
                              "parentId": 662,
                              "customId": 807,
                              "listeners": {
                                   "click": "saveForm"
                              }
                         }, {
                              "customWidgetType": "vdButton",
                              "xtype": "button",
                              "name": "resetFormButton",
                              "margin": 5,
                              "text": "Reset",
                              "hiddenName": "button",
                              "canHaveParent": false,
                              "itemId": "resetFormButton",
                              "parentId": 662,
                              "customId": 808,
                              "listeners": {
                                   "click": "resetForm"
                              }
                         }]
                    }],
                    "tools": [{
                         "type": "help",
                         "tooltip": "Console",
                         "handler": "onConsoleClick"
                    }, {
                         "type": "refresh",
                         "tooltip": "Refresh Tab",
                         "handler": "init"
                    }],
                    "extend": "Ext.form.Panel",
                    "region": "center"
               }, {
                    "xtype": "gridpanel",
                    "customWidgetType": "vdGrid",
                    "displayName": "Issue",
                    "title": "Details Grid",
                    "name": "IssueGrid",
                    "itemId": "IssueGrid",
                    "restURL": "/Issue",
                    "columns": [{
                         "header": "issueId",
                         "dataIndex": "issueId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "primaryDisplay",
                         "dataIndex": "primaryDisplay",
                         "hidden": true
                    }, {
                         "header": "primaryKey",
                         "dataIndex": "primaryKey",
                         "hidden": true
                    }, {
                         "header": "issueName",
                         "dataIndex": "issueName",
                         "flex": 1
                    }, {
                         "header": "Component",
                         "dataIndex": "component",
                         "flex": 1
                    }, {
                         "header": "createdBy",
                         "dataIndex": "createdBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "createdDate",
                         "dataIndex": "createdDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedBy",
                         "dataIndex": "updatedBy",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "updatedDate",
                         "dataIndex": "updatedDate",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "versionId",
                         "dataIndex": "versionId",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "activeStatus",
                         "dataIndex": "activeStatus",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "header": "txnAccessCode",
                         "dataIndex": "txnAccessCode",
                         "hidden": true,
                         "flex": 1
                    }, {
                         "xtype": "actioncolumn",
                         "customWidgetType": "vdActionColumn",
                         "sortable": false,
                         "menuDisable": true,
                         "items": [{
                              "icon": "images/delete.gif",
                              "tooltip": "Delete Record",
                              "handler": "onDeleteActionColumnClickMainGrid"
                         }]
                    }],
                    "listeners": {
                         "itemclick": "onGridItemClick"
                    },
                    "tools": [{
                         "type": "refresh",
                         "tooltip": "Refresh Grid Data",
                         "listeners": {
                              "click": "onGridRefreshClick"
                         },
                         "hidden": true
                    }],
                    "collapsible": true,
                    "titleCollapse": true,
                    "collapseMode": "header",
                    "region": "south",
                    "height": "40%"
               }]
          }]
     }, {
          "title": "Add New",
          "itemId": "addNewForm",
          "layout": "border",
          "customWidgetType": "vdBorderLayout",
          "autoScroll": false,
          "items": [{
               "customWidgetType": "vdFormpanel",
               "xtype": "form",
               "displayName": "Issue",
               "title": "Issue",
               "name": "Issue",
               "itemId": "IssueForm",
               "bodyPadding": 10,
               "items": [{
                    "name": "issueId",
                    "itemId": "issueId",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "issueId",
                    "margin": "5 5 5 5",
                    "fieldLabel": "issueId<font color='red'> *<\/font>",
                    "fieldId": "4D93BAC5-F6AE-46C4-9F5C-DA9F6ACDCED7",
                    "minLength": "1",
                    "maxLength": "256",
                    "hidden": true,
                    "value": "",
                    "bindable": "issueId"
               }, {
                    "name": "issueName",
                    "itemId": "issueName",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "issueName",
                    "margin": "5 5 5 5",
                    "fieldLabel": "issueName<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "7C75AAE1-B118-42EA-BC37-713241E94016",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "issueName",
                    "columnWidth": 0.5
               }, {
                    "name": "component",
                    "itemId": "component",
                    "xtype": "textfield",
                    "customWidgetType": "vdTextfield",
                    "displayName": "Component",
                    "margin": "5 5 5 5",
                    "fieldLabel": "Component<font color='red'> *<\/font>",
                    "allowBlank": false,
                    "fieldId": "FAC363D2-D5FC-4BBF-B6E6-788F7C07285D",
                    "minLength": "1",
                    "maxLength": "256",
                    "bindable": "component",
                    "columnWidth": 0.5
               }, {
                    "name": "versionId",
                    "itemId": "versionId",
                    "xtype": "numberfield",
                    "customWidgetType": "vdNumberfield",
                    "displayName": "versionId",
                    "margin": "5 5 5 5",
                    "value": "-1",
                    "fieldLabel": "versionId",
                    "fieldId": "59C10949-5A77-4EE8-AA93-7F9559627930",
                    "bindable": "versionId",
                    "hidden": true
               }],
               "layout": "column",
               "defaults": {
                    "columnWidth": 0.5,
                    "labelAlign": "left",
                    "labelWidth": 200
               },
               "autoScroll": true,
               "dockedItems": [{
                    "xtype ": "toolbar",
                    "customWidgetType": "vdBBar",
                    "dock": "bottom",
                    "ui": "footer",
                    "isToolBar": true,
                    "isDockedItem": true,
                    "parentId": 1,
                    "customId": 662,
                    "layout": {
                         "type": "hbox"
                    },
                    "items": [{
                         "xtype": "tbfill",
                         "customWidgetType": "vdTbFill",
                         "parentId": 662,
                         "customId": 806
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "saveFormButton",
                         "margin": 5,
                         "text": "Save",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "saveFormButton",
                         "parentId": 662,
                         "customId": 807,
                         "listeners": {
                              "click": "saveForm"
                         }
                    }, {
                         "customWidgetType": "vdButton",
                         "xtype": "button",
                         "name": "resetFormButton",
                         "margin": 5,
                         "text": "Reset",
                         "hiddenName": "button",
                         "canHaveParent": false,
                         "itemId": "resetFormButton",
                         "parentId": 662,
                         "customId": 808,
                         "listeners": {
                              "click": "resetForm"
                         }
                    }]
               }],
               "tools": [{
                    "type": "help",
                    "tooltip": "Console",
                    "handler": "onConsoleClick"
               }, {
                    "type": "refresh",
                    "tooltip": "Refresh Tab",
                    "handler": "init"
               }],
               "extend": "Ext.form.Panel",
               "region": "center"
          }]
     }]
});