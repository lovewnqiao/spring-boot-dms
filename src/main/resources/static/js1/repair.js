
//维修信息修改
function editRepair(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改维修信息');
        //显示未修改前的维修信息
        $('#fim').form('load',row);
    }
}


//维修信息保存按钮事件
function saveRepair(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/repair/update?id=0";
    } else{
        add="/repair/update?id="+row.userId;
    }

    $('#fim').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#modifydg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '保存成功'
                });
            } else {
                $.messager.show({
                    title: 'Error',
                    msg: result.msg
                });
            }
        }
    });
}

//维修删除按钮事件
function removeRepair(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该记录?',function(r){
            if (r){

                $.post('/repair/remove_repair',{id:row.userId},function(result){
                    if (result.success){

                        $('#datagrid').datagrid('reload');	// reload the user data
                        $.messager.show({
                            title: 'Success',
                            msg: '删除成功'
                        });
                    } else {
                        $.messager.show({	// show error message
                            title: 'Error',
                            msg: result.msg
                        });
                    }
                },'json');
            }
        });
    }
}