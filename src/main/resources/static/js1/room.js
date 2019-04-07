
//房间信息修改
function editRoom(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改房间信息');
        //显示未修改前的房间信息
        $('#fim').form('load',row);
    }
}


//房间信息保存按钮事件
function saveRoom(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/room/update?id=0";
    } else{
        add="/room/update?id="+row.id;
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

//房间删除按钮事件
function removeRoom(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该记录?',function(r){
            if (r){

                $.post('/room/remove_room',{id:row.id},function(result){
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