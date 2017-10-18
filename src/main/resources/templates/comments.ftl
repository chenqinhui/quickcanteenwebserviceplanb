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
            <li class="active">用户评价</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page"></h1>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-blue panel-widget">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-comment glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">${commentsNumber}</div>
                        <div class="text-muted">总评价数</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-teal panel-widget">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-comment glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">${goodCommentsNumber}</div>
                        <div class="text-muted">好评数</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-orange panel-widget">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-comment glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">${middleCommentsNumber}</div>
                        <div class="text-muted">中评数</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-red panel-widget">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-comment glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">${badCommentsNumber}</div>
                        <div class="text-muted">差评数</div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>平均分</h4>
                    <div class="easypiechart" id="easypiechart-blue" data-percent="${avgRatingPer}">
                        <span class="percent">${avgRating}分</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>好评率</h4>
                    <div class="easypiechart" id="easypiechart-teal" data-percent="${goodCommentsPercent}">
                        <span class="percent">${goodCommentsPercent}%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>中评率</h4>
                    <div class="easypiechart" id="easypiechart-orange" data-percent="${middleCommentsPercent}">
                        <span class="percent">${middleCommentsPercent}%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>差评率</h4>
                    <div class="easypiechart" id="easypiechart-red" data-percent="${badCommentsPercent}">
                        <span class="percent">${badCommentsPercent}%</span>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-md-12">
            <div class="panel panel-default">
                <div class="panel-body tabs">
                    <ul class="nav nav-pills">
                        <li class="active"><a href="#total" data-toggle="tab">全部</a></li>
                        <li><a href="#good" data-toggle="tab">好评</a></li>
                        <li><a href="#middle" data-toggle="tab">中评</a></li>
                        <li><a href="#bad" data-toggle="tab">差评</a></li>
                    </ul>

                    <div class="tab-content">
                        <div class="tab-pane fade in active" id="total">
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
                                <#list userComments as comments>
                                <tr>
                                    <td data-field="time" data-sortable="true">${comments.commentTimeStr}</td>
                                    <td data-field="name" data-sortable="false">${comments.commenterName}</td>
                                    <td data-field="rating" data-sortable="true">${comments.rating}</td>
                                    <td data-field="content" data-sortable="false">${comments.commentContent}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="good">
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
                                <#list goodUserComments as comments>
                                <tr>
                                    <td data-field="time" data-sortable="true">${comments.commentTimeStr}</td>
                                    <td data-field="name" data-sortable="false">${comments.commenterName}</td>
                                    <td data-field="rating" data-sortable="true">${comments.rating}</td>
                                    <td data-field="content" data-sortable="false">${comments.commentContent}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="middle">
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
                                <#list middleUserComments as comments>
                                <tr>
                                    <td data-field="time" data-sortable="true">${comments.commentTimeStr}</td>
                                    <td data-field="name" data-sortable="false">${comments.commenterName}</td>
                                    <td data-field="rating" data-sortable="true">${comments.rating}</td>
                                    <td data-field="content" data-sortable="false">${comments.commentContent}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                        <div class="tab-pane fade" id="bad">
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
                                <#list badUserComments as comments>
                                <tr>
                                    <td data-field="time" data-sortable="true">${comments.commentTimeStr}</td>
                                    <td data-field="name" data-sortable="false">${comments.commenterName}</td>
                                    <td data-field="rating" data-sortable="true">${comments.rating}</td>
                                    <td data-field="content" data-sortable="false">${comments.commentContent}</td>
                                </tr>
                                </#list>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div><!--/.col-->
    </div><!--/.row-->
</div>    <!--/.main-->

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
</body>

</html>
