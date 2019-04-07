
//宿舍楼信息修改
function editBuilding(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改宿舍楼信息');
        //显示未修改前的宿舍楼信息
        $('#fim').form('load',row);
    }
}


//宿舍楼信息保存按钮事件
function saveBuilding(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/building/update?id=0";
    } else{
        add="/building/update?id="+row.id;
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

//宿舍楼删除按钮事件
function removeBuilding(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该记录?',function(r){
            if (r){

                $.post('/building/remove_building',{id:row.id},function(result){
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