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
                                <a data-toggle="modal" data-target="#myModal${order.orderId}" title="查看详情">
                                    <span class="glyphicon glyphicon-align-justify"
                                          aria-hidden="true"></span>
                                </a>
                                <#switch order.orderStatus>
                                    <#case 100>
                                        <a title="接受订单" onclick="changeStatus('${order.orderId}','30')" style="margin-left: 20px">
                                            <span class="glyphicon glyphicon-ok-circle" aria-hidden="true"></span>
                                        </a>
                                        <a title="拒绝接单" onclick="changeStatus('${order.orderId}','90')" style="margin-left: 20px">
                                            <span class="glyphicon glyphicon-remove-circle" aria-hidden="true"></span>
                                        </a>
                                        <#break >
                                    <#case 30>
                                        <#if order.timeslotId == 0 >
                                            <a title="准备完成" onclick="changeStatus('${order.orderId}','110')" style="margin-left: 20px">
                                                <span class="glyphicon glyphicon-road" aria-hidden="true"></span>
                                            </a>
                                        <#else >
                                            <a title="到窗" onclick="changeStatus('${order.orderId}','40')" style="margin-left: 20px">
                                                <span class="glyphicon glyphicon-cutlery" aria-hidden="true"></span>
                                            </a>
                                        </#if>
                                        <#break >
                                    <#case 50>
                                        <a title="完成订单" onclick="changeStatus('${order.orderId}','60')" style="margin-left: 20px">
                                            <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                                        </a>
                                        <#break >
                                    <#case 40>
                                        <a title="完成订单" onclick="changeStatus('${order.orderId}','60')" style="margin-left: 20px">
                                            <span class="glyphicon glyphicon-asterisk" aria-hidden="true"></span>
                                        </a>
                                        <#break >
                                    <#default>
                                </#switch>
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
<#list orderList as order>
    <div class="modal fade" style="width:800px ;height:900px " id="myModal${order.orderId}" tabindex="-1"
         role="dialog" aria-labelledby="myModalLabel${order.orderId}">
        <div class="modal-dialog" style="width:780px; " role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title" id="myModalLabel${order.orderId}">订单详情</h4>
                </div>
                <div class="modal-body" style="height: 400px">
                    <div class="panel-body">
                        <table data-toggle="table" data-url="tables/data1.json" data-show-refresh="true"
                               data-show-toggle="true" data-show-columns="true" data-search="true"
                               data-select-item-name="toolbar1" data-pagination="true" data-sort-name="name"
                               data-sort-order="desc">
                            <thead>
                            <tr>
                                <th data-field="dishes_name" data-sortable="true">菜品名称</th>
                                <th data-field="price" data-sortable="true">单价</th>
                                <th data-field="count" data-sortable="true">数量</th>
                            </tr>
                            </thead>
                            <tbody>
                                <#if (order.dishesVos?size>0) >
                                    <#list order.dishesVos as dishes>
                                    <tr>
                                        <td data-field="dishes_name" data-sortable="true">${dishes.dishesName}</td>
                                        <td data-field="price" data-sortable="true">${dishes.price}</td>
                                        <td data-field="count" data-sortable="true">${dishes.count}</td>
                                    </tr>
                                    </#list>
                                </#if>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="input-group">
                        <input id="btn-input" type="text" class="form-control input-md" placeholder="Add new task"/>
                        <span class="input-group-btn">
								<button class="btn btn-primary btn-md" id="btn-todo">Add</button>
							</span>
                    </div>
                </div>
            </div>
        </div>
    </div>
</#list>
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
    function changeStatus(orderId, toStatus) {
        //alert(orderId);
        $.ajax({
            type: "post",
            url: "/api/order/changeStatus",
            timeout: 8000,
            dataType: "json",
            data: {
                "orderId": orderId,
                "toStatus": toStatus
            },
            success: function () {
                alert("修改成功");
                window.location.href = "orders";
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
