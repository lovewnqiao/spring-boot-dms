//通知信息修改
function editNotice(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改通知信息');
        //显示未修改前的通知信息
        $('#fim').form('load',row);
    }
}


//通知分类信息保存按钮事件
function saveNotice(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/notice/update?id=0";
    } else{
        add="/notice/update?id="+row.id;
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

//通知分类信息删除按钮事件
function removeNotice(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该记录?',function(r){
            if (r){

                $.post('/notice/remove_notice',{id:row.id},function(result){
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
