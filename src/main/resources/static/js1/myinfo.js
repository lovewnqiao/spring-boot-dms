function updateUserInfo(){
     $.ajax({
        type: "POST",
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        url: "/user/update",
        data: $('#user_info_commit').serialize(),
        success: function(data) {
            console.log(data)
            if(data.success){
                window.parent.document.getElementById("index_userName").innerText=data.name;
                $.messager.show({
                    title: 'Success',
                    msg: '修改成功'
                });
            }
        }
    })
    return false;
}