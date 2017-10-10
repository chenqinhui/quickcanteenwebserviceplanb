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
            <li><a href="index"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">订单显示</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">订单显示</h1>
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
                            <th data-field="state" data-checkbox="true">菜品编号</th>
                            <th data-field="id" data-sortable="true">订单编号</th>
                            <th data-field="name" data-sortable="true">下单用户</th>
                            <th data-field="price" data-sortable="true">订单价格</th>
                            <th data-field="picture" data-sortable="true">订单状态</th>
                            <th data-field="modify" data-sortable="true">操作</th>

                        </tr>

                        </thead>


                        <tbody>
                        <#list orderList as order>
                        <tr>

                            <td data-field="state" data-checkbox="true">菜品编号</td>
                            <td data-field="id" data-sortable="true"> ${order.orderId}</td>
                            <td data-field="name" data-sortable="true"><a
                                    href="">${order.userName}</a></td>
                            <td data-field="price" data-sortable="true">${order.totalPrice}</td>
                            <td data-field="picture" data-sortable="true">
                                <#list statusList as s>
                                <#if (s.getValue() == order.orderStatus)>
                                ${s.getDesc()}
                                </#if>
                            </#list>
                            </td>
                            <td data-field="modify" data-sortable="true">
                                <a href="/orders/${order.orderId?c}" target="_blank">
                                    <span class="glyphicon glyphicon-align-justify"
                                          aria-hidden="true"></span>
                                </a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>

                    </table>

                </div>
            </div>
        </div>
    </div><!--/.row-->


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
