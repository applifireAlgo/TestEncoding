Ext.define('Project3.view.appreportui.ReportView', {
	extend : 'Ext.form.Panel',
	requires : ['Project3.view.appreportui.ReportViewController',
	            'Project3.view.appreportui.datagrid.DataGridPanel',
	            'Project3.view.appreportui.datagrid.DataGridView',
	            'Project3.view.appreportui.querycriteria.QueryCriteriaView',
	            'Project3.view.appreportui.chart.ChartView',
	            'Project3.view.appreportui.datapoint.DataPointView',
	            'Project3.view.googlemaps.map.MapPanel',
	            'Project3.view.appreportui.chartpoint.ChartPointView'
	            ],
	xtype : 'reportView',
	controller : 'reportViewController',
	layout : 'border',
	reportJSON:null,
	bodyStyle:{
        background:'#f6f6f6'
    },
	listeners : {
		scope : 'controller',
		afterrender : 'afterRenderReport',
		boxready : 'fetchReportData',
		added:'onReportAdded'
	}
});
