$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'demo/uschoose/list',
        datatype: "json",
        colModel: [			
			// { label: 'id', name: 'id', index: 'id', width: 50, key: true },
			{ label: '姓名', name: 'name', index: 'name', width: 80 }, 			
			{ label: '性别', name: 'sex', index: 'sex', width: 80, formatter: function(value, options, row){
                    return value === 0 ? '<span class="label label-danger">女</span>' :
                        (value === 1 ? '<span class="label label-success">男</span>' :
                            '<span class="label label-warning">保密</span>');
                }},
			{ label: '年龄', name: 'age', index: 'age', width: 80 },
			{ label: '手机', name: 'phone', index: 'phone', width: 80 }			
        ],
		viewrecords: true,
        height: document.documentElement.clientHeight - 130,
        rowNum: 10,
		rowList : [10,30,50,100,500,1000],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
        q:{
            username: null
        },
		showList: true,
		title: null,
		usChoose: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
        query1: function () {
            $.ajax({
                type: "POST",
                url: baseURL + "demo/uschoose/search",
                // contentType: "application/json",
                // data: JSON.stringify(ids),
                success: function(r){
                    console.log(r);
                    // if(r.code == 0){
                    //     layer.msg("操作成功", {icon: 1});
                    //     $("#jqGrid").trigger("reloadGrid");
                    // }else{
                    //     layer.alert(r.msg);
                    // }
                }
            });
        },
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.usChoose = {sex:1};
		},
		update: function (event) {
			var id = getSelectedRow();
			if(id == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(id)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.usChoose.id == null ? "demo/uschoose/save" : "demo/uschoose/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.usChoose),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var ids = getSelectedRows();
			if(ids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "demo/uschoose/delete",
                        contentType: "application/json",
                        data: JSON.stringify(ids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(id){
			$.get(baseURL + "demo/uschoose/info/"+id, function(r){
                vm.usChoose = r.usChoose;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{
                postData:{'username': vm.q.username},
                page:page
            }).trigger("reloadGrid");
		}
	}
});