/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function check() {
    var username = document.getElementById("username").value;
    if (username == "" | username == null) {
        alert("请输入用户名!");
        return false;
    }
    var password1 = document.getElementById("password1").value;
    if (password1 == "" | password1 == null) {
        alert("请输入密码!");
        return false;
    }
    if (password1.length < 6) {
        alert("密码过短!");
        return false;
    }
    var password2 = document.getElementById("password2").value;
    if (password1 != password2) {
        alert("两次密码不一致!");
        return false;
    }
    var phone = document.getElementById("phone").value;
    if (phone == "" | phone == null) {
        alert("请输入手机号!");
        return false;
    }
    if (phone.length != 11) {
        alert("手机格式错误!");
        return false;
    }
    var email = document.getElementById("email").value;
    if (email == "" | email == null) {
        alert("请输入邮箱!");
        return false;
    }
    if (email.indexOf(".") == -1 | email.indexOf("@") == -1) {
        alert("邮箱格式错误!");
        return false;
    }


    return true;
}
