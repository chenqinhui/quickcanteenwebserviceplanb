
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Lumino - Tables</title>

    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <link href="/css/datepicker3.css" rel="stylesheet">
    <link href="/css/bootstrap-table.css" rel="stylesheet">
    <link href="/css/styles.css" rel="stylesheet">

    <!--[if lt IE 9]>
    <script src="/js/html5shiv.js"></script>
    <script src="/js/respond.min.js"></script>
    <![endif]-->

</head>

<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#sidebar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"><span>${company_name}</span> 菜品管理系统</a>
            <ul class="user-menu">
                <li class="dropdown pull-right">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="glyphicon glyphicon-user"></span> ${company_name} <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#"><span class="glyphicon glyphicon-user"></span> 基本信息</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-cog"></span> 设置</a></li>
                        <li><a href="#"><span class="glyphicon glyphicon-log-out"></span> 注销</a></li>
                    </ul>
                </li>
            </ul>
        </div>

    </div><!-- /.container-fluid -->
</nav>

<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li><a href="index"><span class="glyphicon glyphicon-dashboard"></span> 主页</a></li>
        <li><a href="widgets"><span class="glyphicon glyphicon-th"></span> Widgets</a></li>
        <li><a href="charts"><span class="glyphicon glyphicon-stats"></span> Charts</a></li>
        <li class="active"><a href="tables"><span class="glyphicon glyphicon-list-alt"></span> 上架菜品</a></li>
        <li><a href="forms"><span class="glyphicon glyphicon-pencil"></span> Forms</a></li>
        <li><a href="panels"><span class="glyphicon glyphicon-info-sign"></span> Alerts &amp; Panels</a></li>
        <li class="parent ">
            <a href="#">
                <span class="glyphicon glyphicon-list"></span> Dropdown <span data-toggle="collapse" href="#sub-item-1" class="icon pull-right"><em class="glyphicon glyphicon-s glyphicon-plus"></em></span>
            </a>
            <ul class="children collapse" id="sub-item-1">
                <li>
                    <a class="" href="#">
                        <span class="glyphicon glyphicon-share-alt"></span> Sub Item 1
                    </a>
                </li>
                <li>
                    <a class="" href="#">
                        <span class="glyphicon glyphicon-share-alt"></span> Sub Item 2
                    </a>
                </li>
                <li>
                    <a class="" href="#">
                        <span class="glyphicon glyphicon-share-alt"></span> Sub Item 3
                    </a>
                </li>
            </ul>
        </li>
        <li role="presentation" class="divider"></li>
        <li><a href="login.html"><span class="glyphicon glyphicon-user"></span> Login Page</a></li>
    </ul>
</div><!--/.sidebar-->

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">菜品显示</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">菜品显示</h1>
        </div>
    </div><!--/.row-->


    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"></div>
                <div class="panel-body">
                    <table data-toggle="table" data-url="tables/data1.json"  data-show-refresh="true" data-show-toggle="true" data-show-columns="true" data-search="true" data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name" data-sort-order="desc">
                        <thead>
                        <tr>
                            <th data-field="state" data-checkbox="true" >菜品编号</th>
                            <th data-field="id" data-sortable="true">菜品编号</th>
                            <th data-field="name" data-sortable="true">菜品名称</th>
                            <th data-field="price"  data-sortable="true"> 菜品价格</th>
                            <th data-field="picture" data-sortable="true">菜品图片</th>
                            <th data-field="modify" data-sortable="true">操作</th>

                        </tr>

                        </thead>


                        <tbody>
                            <tr>
                                <td data-field="add" >增加</td>
                                <td data-field="null" data-sortable="true"></td>
                                <td data-field="null" data-sortable="true"></td>
                                <td data-field="null"  data-sortable="true"></td>
                                <td data-field="null" data-sortable="true"></td>
                                <td data-field="null" data-sortable="true"><img src="/image/add.svg" data-toggle="modal" data-target="#myModal" ></td>
                            </tr>
                        <#list dishesList as dishes>
                            <tr>

                                <td data-field="state" data-checkbox="true" >菜品编号</td>
                                <td data-field="id" data-sortable="true"> ${dishes.dishesId}</td>
                                <td data-field="name" data-sortable="true"><a href="detail?dishesId=${dishes.dishesId}">${dishes.dishesName}</a></td>
                                <td data-field="price"  data-sortable="true">${dishes.price}</td>
                                <td data-field="picture" data-sortable="true">菜品图片</td>
                                <td data-field="modify" data-sortable="true"><img src="/image/edit.svg" data-toggle="modal" data-target="#myModal${dishes.dishesId}"><img src="/image/close.svg" style="margin-left: 40px" onclick="deletedishes(${dishes.dishesId});"></td>
                            </tr>
                        </#list>
                        </tbody>

                    </table>

                </div>
            </div>
        </div>
    </div><!--/.row-->


    <#list dishesList as dishes>
        <div class="modal fade" style="width:800px ;height:900px " id="myModal${dishes.dishesId}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel${dishes.dishesId}">
            <div class="modal-dialog" style="width:780px; " role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                        <h4 class="modal-title" id="myModalLabel${dishes.dishesId}">修改菜品</h4>
                    </div>
                    <div class="modal-body" style="height: 400px">
                        <p style="margin-left: 50px">菜名<input id="name${dishes.dishesId}" style="width:120px; height:30px; margin-left: 40px"  type="text" value="${dishes.dishesName}"/></p>
                        <p style="margin-left: 50px;margin-top: 30px">价格<input id="price${dishes.dishesId}" style="width:120px; height:30px; margin-left: 40px"  type="text"  value="${dishes.price}"/></p>
                        <p style="margin-left: 50px;margin-top: 30px">介绍<input id="introduction${dishes.dishesId}" style="width:300px; height:60px; margin-left: 40px;"  type="text"  value="${dishes.dishesIntroduce}"/></p>
                        <p style="margin-left: 50px;margin-top: 30px">图片</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="edit('${dishes.dishesId}');" >提交</button>
                    </div>
                </div>
            </div>
        </div>
    </#list>

    <div class="modal fade" style="width:800px ;height:900px " id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabels">修改菜品</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <p style="margin-left: 50px">菜名<input id="name" style="width:120px; height:30px; margin-left: 40px"  type="text" /></p>
                    <p style="margin-left: 50px;margin-top: 30px">价格<input id="price" style="width:120px; height:30px; margin-left: 40px"  type="text"  /></p>
                    <p style="margin-left: 50px;margin-top: 30px">介绍<input id="introduction" style="width:300px; height:60px; margin-left: 40px;"  type="text"  /></p>
                    <p style="margin-left: 50px;margin-top: 30px">图片<span style="margin-left: 40px"><form id="signupListImportForm" class="import-file-form" enctype="multipart/form-data">    
                            <input type="file" name="excelFile" id="PictureFile" style="display:none" multiple="multiple" onchange="fileUpload()"></form></span><button id="upload" onclick="uploadpic();return false;">上传</button></p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="addDishes(); return false;">提交</button>
                </div>
            </div>
        </div>
    </div>


    <script>
        $(function () {
            $('#hover, #striped, #condensed').click(function () {
                var classes = 'table';
                if ($('#hover').prop('checked')) {
                    classes += ' table-hover';
                }
                if ($('#condensed').prop('checked')) {
                    classes += ' table-condensed';
                }
                $('#table-style').bootstrapTable('destroy')
                        .bootstrapTable({
                            classes: classes,
                            striped: $('#striped').prop('checked')
                        });
            });
        });

        function rowStyle(row, index) {
            var classes = ['active', 'success', 'info', 'warning', 'danger'];

            if (index % 2 === 0 && index / 2 < classes.length) {
                return {
                    classes: classes[index / 2]
                };
            }
            return {};
        }
    </script>



</div><!--/.main-->

<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/chart.min.js"></script>
<script src="/js/chart-data.js"></script>
<script src="/js/easypiechart.js"></script>
<script src="/js/easypiechart-data.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script src="/js/bootstrap-table.js"></script>
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
    function edit(dishesId){
        var name = $("#name"+dishesId).val();
        var price = $("#price"+dishesId).val();
        var introduction =$("#introduction"+dishesId).val();

        $.ajax({
            type: "post",
            url:"/api/company/edit",
            timeout:8000,
            dataType:"json",
            data:{
                "dishesId":dishesId,
                "name":name,
                "price":price,
                "introduction":introduction
            },

            success:function(data){
                if(data.returnCode==="0"){
                    alert("修改失败");
                }
                else{
                    alert("修改成功");
                    window.location.href="tables";
                }
            },

            error:function(){
                alert("请求出错")
            }
        })
    }

    function deletedishes(dishesId) {
        if(confirm('确定要执行此操作吗?'))
        {
            $.ajax({
                type: "post",
                url: "/api/company/delete",
                timeout: 8000,
                dataType: "json",
                data: {
                    "dishesId": dishesId
                },
                success: function (data) {
                    if (data.returnCode === "0") {
                        alert("删除失败");
                    }
                    else {
                        alert("删除成功");
                        window.location.href = "tables";
                    }
                },

                error: function () {
                    alert("请求出错")
                }
            })
        }


    }

    function addDishes(){

        var name = $("#name").val();
        var price = $("#price").val();
        var introduction =$("#introduction").val();


        $.ajax({
            type: "post",
            url: "/api/company/add",
            timeout: 8000,
            dataType: "json",
            data: {
                "name":name,
                "price":price,
                "introduction":introduction
            },
            success: function (data) {
                alert(data.returnCode);
                if (data.returnCode === "0") {
                    alert("添加失败");
                }
                else {
                    alert("添加成功");
                    window.location.href = "tables";
                }
            },

            error: function () {
                alert("请求出错")
            }
        })
    }

    function uploadpic() {
        var picaddress = $("PictureFile").val();
        $.ajax({
            type: "post",
            url: "/api/company/upload",
            timeout: 8000,
            dataType: "json",
            data: {
                "fileToUpload": picaddress
            },

            success: function (data) {
                if (data.message == "uploadError") {
                    alert("上传失败");
                }
                else {
                    alert("上传成功");
                }
            },

            error: function () {
                alert("请求出错")
            }


        })
    }
</script>
<script src="/js/jquery.cookie.js"></script>
<script type="text/javascript">
    function logout(){
        $.removeCookie('X-TOKEN');
        window.location.href = "login";
    }
</script>

</body>

</html>