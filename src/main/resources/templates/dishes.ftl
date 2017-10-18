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
            <li class="active">菜品显示</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page"></h1>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading"></div>
                <div class="panel-body">
                    <table data-toggle="table" data-url="tables/data1.json" data-show-refresh="true"
                           data-show-toggle="true" data-show-columns="true" data-search="true"
                           data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name"
                           data-sort-order="desc">
                        <thead>
                        <tr>
                            <th data-field="available" data-sortable="true">上架状态</th>
                            <th data-field="id" data-sortable="true">菜品编号</th>
                            <th data-field="name" data-sortable="true">菜品名称</th>
                            <th data-field="price" data-sortable="true"> 菜品价格</th>
                            <th data-field="picture" data-sortable="true">菜品图片</th>
                            <th data-field="modify" data-sortable="true">操作</th>

                        </tr>

                        </thead>


                        <tbody>
                        <tr>
                            <td data-field="add">增加</td>
                            <td data-field="null" data-sortable="true"></td>
                            <td data-field="null" data-sortable="true"></td>
                            <td data-field="null" data-sortable="true"></td>
                            <td data-field="null" data-sortable="true"></td>
                            <td data-field="null" data-sortable="true">
                                <a data-toggle="modal" data-target="#myModal" title="添加">
                                    <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
                                </a>
                            </td>
                        </tr>
                        <#list dishesList as dishes>
                        <tr>
                            <td data-field="available" data-sortable="true"><#if (dishes.available==1)>
                                上架</#if><#if (dishes.available==0)>已下架</#if></td>
                            <td data-field="id" data-sortable="true"> ${dishes.dishesId}</td>
                            <td data-field="name" data-sortable="true"><a data-toggle="modal"
                                                                          data-target="#checkModal${dishes.dishesId}"
                                                                          href="">${dishes.dishesName}</a></td>
                            <td data-field="price" data-sortable="true">${dishes.price}</td>
                            <td data-field="picture" data-sortable="true">菜品图片</td>
                            <td data-field="modify" data-sortable="true">
                                <a data-toggle="modal" data-target="#myModal${dishes.dishesId}" title="编辑">
                                    <span class="glyphicon glyphicon-edit" aria-hidden="true"></span>
                                </a>
                                <a title="删除" style="margin-left: 40px" onclick="deletedishes(${dishes.dishesId});">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </a>
                        </tr>
                        </#list>
                        </tbody>

                    </table>

                </div>
            </div>
        </div>
    </div><!--/.row-->


<#list dishesList as dishes>
    <div class="modal fade" style="width:800px ;height:900px " id="myModal${dishes.dishesId}" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel${dishes.dishesId}">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel${dishes.dishesId}">修改菜品</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <p style="margin-left: 50px">菜名<input id="name${dishes.dishesId}"
                                                          style="width:120px; height:30px; margin-left: 40px"
                                                          type="text" value="${dishes.dishesName}"/></p>
                    <p style="margin-left: 50px;margin-top: 30px">价格<input id="price${dishes.dishesId}"
                                                                           style="width:120px; height:30px; margin-left: 40px"
                                                                           type="text" value="${dishes.price}"/></p>
                    <p style="margin-left: 50px;margin-top: 30px">介绍<input id="introduction${dishes.dishesId}"
                                                                           style="width:300px; height:60px; margin-left: 40px;"
                                                                           type="text"
                                                                           value="${dishes.dishesIntroduce}"/></p>
                    <p style="margin-left: 50px;margin-top: 30px">图片</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" onclick="edit('${dishes.dishesId}');">提交</button>
                </div>
            </div>
        </div>
    </div>
</#list>

<#list dishesList as dishes>
    <div class="modal fade" style="width:800px ;height:1500px " id="checkModal${dishes.dishesId}" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel${dishes.dishesId}">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel${dishes.dishesId}">查看菜品</h4>
                </div>
                <div class="modal-body" style="height: 500px;overflow:auto">

                    <p style="margin-left: 50px">菜名: ${dishes.dishesName}
                    <p style="margin-left: 50px;margin-top: 30px">价格: ${dishes.price}
                    <p style="margin-left: 50px;margin-top: 30px">介绍: ${dishes.dishesIntroduce}
                    </p>
                    <p style="margin-left: 50px;margin-top: 30px">评分: ${dishes.rating}
                    <p style="margin-left: 50px;margin-top: 30px">图片 </p>
                    <p style="margin-left: 50px;margin-top: 30px">下面是同学们的评价</p>
                    <table data-toggle="table" data-url="tables/data1.json" data-show-refresh="true"
                           data-show-toggle="true" data-show-columns="true" data-search="true"
                           data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name"
                           data-sort-order="desc">
                        <thead>
                        <tr>
                            <th data-field="time" data-sortable="true">评价时间</th>
                            <th data-field="name" data-sortable="false">评价用户</th>
                            <th data-field="rating" data-sortable="true">评分</th>
                            <th data-field="content" data-sortable="false"> 评价内容</th>
                        </tr>
                        </thead>

                        <tbody>
                            <#list dishes.commentVos as comments>
                            <tr>
                                <td data-field="time" data-sortable="true">${comments.commentTimeStr}</td>
                                <td data-field="name" data-sortable="false">${comments.commenterName}</td>
                                <td data-field="rating" data-sortable="true">${comments.rating}</td>
                                <td data-field="content" data-sortable="false">${comments.commentContent}</td>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                   <#-- <#list dishes.commentVos as comment>
                        <p style="margin-left: 50px;margin-top: 30px">评论人:${comment.commenterName}</p>
                        <p style="margin-left: 50px;margin-top: 30px">评分:${comment.rating}</p>
                        <p style="margin-left: 50px;margin-top: 30px">评论: </p>
                        <p style="margin-left: 50px;margin-top: 30px">${comment.commentContent}</p>
                    </#list>-->
                </div>
            </div>
        </div>
    </div>
</#list>

    <div class="modal fade" style="width:800px ;height:900px " id="myModal" tabindex="-1" role="dialog"
         aria-labelledby="myModalLabel">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabels">修改菜品</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <p style="margin-left: 50px">菜名<input id="name" style="width:120px; height:30px; margin-left: 40px"
                                                          type="text"/></p>
                    <p style="margin-left: 50px;margin-top: 30px">价格<input id="price"
                                                                           style="width:120px; height:30px; margin-left: 40px"
                                                                           type="text"/></p>
                    <p style="margin-left: 50px;margin-top: 30px">介绍<input id="introduction"
                                                                           style="width:300px; height:60px; margin-left: 40px;"
                                                                           type="text"/></p>
                    <p style="margin-left: 50px;margin-top: 30px">图片<span style="margin-left: 40px"><form
                            id="signupListImportForm"  class="import-file-form"  enctype="multipart/form-data">    
                            <input type="file"  name="excelFile"  id="PictureFile"  style="display:none"
                                    multiple="multiple"  onchange="fileUpload()"></form></span>
                        <button id="upload" onclick="uploadpic();return false;">上传</button>
                    </p>
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
    function edit(dishesId) {
        var name = $("#name" + dishesId).val();
        var price = $("#price" + dishesId).val();
        var introduction = $("#introduction" + dishesId).val();

        $.ajax({
            type: "post",
            url: "/api/company/edit",
            timeout: 8000,
            dataType: "json",
            data: {
                "dishesId": dishesId,
                "name": name,
                "price": price,
                "introduction": introduction
            },

            success: function (data) {
                if (data.returnCode === "0") {
                    alert("修改失败");
                }
                else {
                    alert("修改成功");
                    window.location.href = "tables";
                }
            },

            error: function () {
                alert("请求出错")
            }
        })
    }

    function deletedishes(dishesId) {
        if (confirm('确定要执行此操作吗?')) {
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

    function addDishes() {

        var name = $("#name").val();
        var price = $("#price").val();
        var introduction = $("#introduction").val();


        $.ajax({
            type: "post",
            url: "/api/company/add",
            timeout: 8000,
            dataType: "json",
            data: {
                "name": name,
                "price": price,
                "introduction": introduction
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

</body>

</html>
