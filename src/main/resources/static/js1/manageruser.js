
//添加新学生
function newUsers(){
    //添加用户对话框
    $('#adddg').dialog('open').dialog('setTitle','添加学生');
    //数据清空
    $('#fam').form('clear');

}

//学生信息修改
function editUsers(){
    //选中某一行
    var row = $('#datagrid').datagrid('getSelected');
    if (row){
        $('#modifydg').dialog('open').dialog('setTitle','修改学生信息');
        //显示未修改前的学生信息
        $('#fim').form('load',row);
    }
}


//学生信息保存按钮事件
function saveUsers(){
    var row = $('#datagrid').datagrid('getSelected');
    var add;
    if(row==null){
        add="/user/update?id=0";
    } else{
        add="/user/update?id="+row.userId;
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

//学生信息添加按钮事件
function addUsers(){
    var add="/user/save_users";
    $('#fam').form('submit',{
        url: add,
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
            var result = eval('('+result+')');
            if (result.success){

                $('#adddg').dialog('close');		// close the dialog
                $('#datagrid').datagrid('reload');	// reload the user data
                $.messager.show({
                    title: 'Success',
                    msg: '添加成功'
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


//用户删除按钮事件
function removeUsers(){
    var row = $('#datagrid').datagrid('getSelected');

    if (row){
        $.messager.confirm('Confirm','确定要删除该学生?',function(r){
            if (r){

                $.post('/user/remove_users',{id:row.userId},function(result){
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