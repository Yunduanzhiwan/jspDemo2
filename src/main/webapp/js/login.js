/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//checkEmail
function checkEmail(email) {
    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    return re.test(email);
}


//
function checkPhone(phone){
    var phoneReg = /^[0-9]{10}$/;
    if(!phoneReg.test(phone)){
        return false;
    }
    return true;
}

function check() {


    var account = document.getElementById("account").value;


    if (account=="" || account==null) {
        alert("账号不能为空");
        return false;
    }



    var password = document.getElementById("password").value;
    if (password == "" | password == null) {
        alert("请输入密码!");
        return false;
    }
    if (password.length < 6) {
        alert("密码过短!");
        return false;
    }

    return true;
}
