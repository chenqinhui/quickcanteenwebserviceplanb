<#--
Created by IntelliJ IDEA.
User: 可爱的11
Date: 2017/7/17
Time: 16:41
To change this template use File | Settings | File Templates.
-->
<!DOCTYPE html>
<html>
<head>
<#include "head.ftl"/>
</head>

<body>

<div class="row">
    <div class="col-xs-10 col-xs-offset-1 col-sm-8 col-sm-offset-2 col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
            <div class="panel-heading">Log in</div>
            <div class="panel-body">
                <form role="form">
                    <fieldset>
                        <div class="form-group">
                            <input class="form-control" placeholder="企业账号" name="account" id="account" type="account" autofocus="">
                        </div>
                        <div class="form-group">
                            <input class="form-control" placeholder="密码" name="password" id="password" type="password" value="">
                        </div>
                        <div class="checkbox">
                            <label>
                                <input name="remember" type="checkbox" value="Remember Me">记住账号
                            </label>
                        </div>
                        <div class="btn btn-primary" onclick="loginfuc();return false;">登录</div>
                    </fieldset>
                </form>
            </div>
        </div>
    </div><!-- /.col-->
</div><!-- /.row -->



<#include "third_party_file.ftl"/>
<script src="/js/chart.min.js"></script>
<script src="/js/chart-data.js"></script>
<script src="/js/easypiechart.js"></script>
<script src="/js/easypiechart-data.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/jquery.cookie.js"></script>
<script>
    !function ($) {
        $(document).on("click","ul.nav li.parent > a > span.icon", function(){
            $(this).find('em:first').toggleClass("glyphicon-minus");
        });
        $(".sidebar span.icon").find('em:first').addClass("glyphicon-plus");
    }(window.jQuery);

    $(window).on('resize', function () {
        if ($(window).width() > 768) $('#sidebar-collapse').collapse('show')
    })
    $(window).on('resize', function () {
        if ($(window).width() <= 767) $('#sidebar-collapse').collapse('hide')
    })
</script>

<script type="text/javascript">
    function loginfuc() {
        var account = $("#account").val();
        var password = $("#password").val();

        $.ajax({
            type: "post",
            url:"/api/company/login",
            timeout:8000,
            dataType:"json",
            data:{
                "account":account,
                "password":password
            },

            success:function(data){
                if(data.returnCode==="0"){
                    alert("账号或密码错误！");
                }
                else if(data.returnCode==="-1"){
                    alert("没有该用户");
                }
                else{
                    alert("登录成功");
                    alert(data.token);
                    $.cookie('X-TOKEN', data.token, { path: '/' });
                    window.location.href="index";
                }
            },

            error:function(){
                alert("请求出错")
            }
        })

    }
</script>

</body>

</html>
