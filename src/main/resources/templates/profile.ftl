<#--
Created by IntelliJ IDEA.
User: 可爱的11
Date: 2017/7/17
Time: 16:42
To change this template use File | Settings | File Templates.
-->

<!DOCTYPE html>
<html>
<head>
<#include "head.ftl"/>
</head>

<body>
<#include "navigation.ftl"/>

<#include "sidebar.ftl"/>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">用户信息</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page"></h1>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-heading"><span class="glyphicon glyphicon-user"></span>基本信息</div>
                <div class="panel-body">
                    <form class="form-horizontal" action="" method="post">
                        <fieldset>
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="name">企业编号</label>
                                <div class="col-md-9">
                                    <input id="id" name="id" type="text"
                                           contenteditable="false" class="form-control" disabled="false"
                                           value="${company_info.companyId}">
                                </div>
                            </div>
                            <!-- Image input-->
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="name">头像</label>
                                <div class="col-md-9">
                                    <img id="portrait" name="image" src="/image/hexishitang.jpg" width="100dp"
                                         height="100dp" data-toggle="modal" data-target="#uploadPortrait">
                                </div>
                            </div>

                            <!-- Name input-->
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="name">企业名</label>
                                <div class="col-md-9">
                                    <input id="name" name="name" type="text" placeholder="${company_info.companyName}" disabled="false"
                                           contenteditable="false" class="form-control">
                                </div>
                            </div>

                            <!-- Account input-->
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="email">账号</label>
                                <div class="col-md-9">
                                    <input id="email" name="email" type="text"
                                           placeholder="${company_info.accountNumber}" contenteditable="false" disabled="false"
                                           class="form-control">
                                </div>
                            </div>

                            <!-- Password input-->
                            <div class="form-group">
                                <label class="col-md-3 control-label" for="email">密码</label>
                                <div class="col-md-9">
                                    <a id="password" name="password" type="password" class="form-control"
                                       data-toggle="modal" data-target="#editPassword">点击修改密码</a>
                                </div>
                            </div>

                        </fieldset>
                    </form>
                </div>
            </div>
        </div><!--/.col-->
    </div><!--/.row-->
    <div class="modal fade" style="width:800px ;height:900px " id="editPassword" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改密码</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <div class="panel-body">
                        <p style="margin-left: 50px">原密码<input id="old_pwd"
                                                               style="width:120px; height:30px; margin-left: 40px"
                                                               type="text"/></p>
                        <p style="margin-left: 50px;margin-top: 30px">新密码<input id="new_pwd"
                                                                                style="width:120px; height:30px; margin-left: 40px"
                                                                                type="text"/></p>
                        <p style="margin-left: 50px;margin-top: 30px">确认新密码<input id="new_pwd_again"
                                                                                  style="width:120px; height:30px; margin-left: 40px"
                                                                                  type="text"/></p>
                    </div>
                </div>
                <div class="modal-footer">
                        <button class="btn btn-primary btn-md" type="button" onclick="editPassword();return false;">提交</button>
                </div>

            </div>
        </div>
    </div>
    <div class="modal fade" style="width:800px ;height:900px " id="uploadPortrait" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改头像</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <div class="panel-body">
                        <form id="uploadForm">
                            <input type="file" id="file" name="file">
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-md" type="button" onclick="fire_ajax_submit();return false;">提交</button>
                </div>

            </div>
        </div>
    </div>
    <div class="modal fade" style="width:800px ;height:900px " id="uploadPortrait" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改头像</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <div class="panel-body">
                        <form id="uploadForm">
                            <input type="file" id="file" name="file">
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-md" type="button" onclick="fire_ajax_submit();return false;">提交
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-md" type="button" onclick="editPassword();return false;">提交
                    </button>
                </div>

            </div>
        </div>
    </div>
    <div class="modal fade" style="width:800px ;height:900px " id="uploadPortrait" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel">修改头像</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <div class="panel-body">
                        <form id="uploadForm">
                            <input type="file" id="file" name="file">
                        </form>
                    </div>
                </div>
                <div class="modal-footer">
                    <button class="btn btn-primary btn-md" type="button" onclick="fire_ajax_submit();return false;">提交
                    </button>
                </div>

            </div>
        </div>
    </div>
</div>    <!--/.main-->


<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/chart.min.js"></script>
<script src="/js/chart-data.js"></script>
<script src="/js/easypiechart.js"></script>
<script src="/js/easypiechart-data.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script>
    $('#calendar').datepicker({});

    !function ($) {
        $(document).on("click", "ul.nav li.parent > a > span.icon", function () {
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
    function fire_ajax_submit() {
        var formData = new FormData($("#uploadForm")[0]);
        formData.append("companyId", $("#id").val());
        $.ajax({
            url: '/upload',
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                alert(data.fileName);
            },

            error: function () {
                alert("请求出错");
            }

        });
    }

    function editPassword() {
        var oldPwd = $("#old_pwd").val();
        var newPwd = $("#new_pwd").val();
        var newPwdAgain = $("#new_pwd_again").val();
<<<<<<< HEAD
        if(oldPwd==""||newPwd==""||newPwdAgain==""){
            alert("密码不能为空");
        }
        else if (newPwd != newPwdAgain) {
=======
        if (newPwd != newPwdAgain) {
>>>>>>> 619fe45d2ea0b05635e413c4b052cfd73355deae
            alert("两次密码输入不一致");
        }
        else if(newPwd.length<6||newPwd.length>18){
            alert("密码必须为6~18位");
        }
        else{
            $.ajax({
                type: "post",
                url: "/api/company/editPassword",
                timeout: 8000,
                dataType: "json",
                data: {
                    "oldPwd": oldPwd,
                    "newPwd": newPwd
                },

                success: function (data) {
                    if (data.returnCode === "0") {
                        alert("修改失败，密码错误");
                    }
                    else {
                        alert("修改成功");
                        window.location.href = "profile";
                    }
                },

                error: function () {
                    alert("请求出错");
                }
            })
        }
    }
</script>

</body>

</html>
