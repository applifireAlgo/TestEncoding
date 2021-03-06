Ext.define('Project3.project3.shared.projectthree.model.testboundedcontext.testdomain.IssueModel', {
     "extend": "Ext.data.Model",
     "fields": [{
          "name": "primaryKey",
          "type": "auto",
          "defaultValue": ""
     }, {
          "name": "issueId",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "issueName",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "component",
          "type": "string",
          "defaultValue": ""
     }, {
          "name": "versionId",
          "type": "int",
          "defaultValue": ""
     }, {
          "name": "entityAudit",
          "reference": "EntityAudit"
     }, {
          "name": "primaryDisplay",
          "type": "string",
          "defaultValue": ""
     }]
});