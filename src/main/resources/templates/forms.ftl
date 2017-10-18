<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<#include "navigation.ftl"/>

<#include "sidebar.ftl"/>

<div class="col-sm-9 col-sm-offset-3 col-lg-10 col-lg-offset-2 main">
    <div class="row">
        <ol class="breadcrumb">
            <li><a href="#"><span class="glyphicon glyphicon-home"></span></a></li>
            <li class="active">Dashboard</li>
        </ol>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Dashboard</h1>
        </div>
    </div><!--/.row-->

    <div class="copyrights">Collect from <a href="http://www.cssmoban.com/" >网页模板</a></div>

    <div class="row">
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-blue panel-widget ">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-shopping-cart glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">120</div>
                        <div class="text-muted">New Orders</div>
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
                        <div class="large">52</div>
                        <div class="text-muted">Comments</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-teal panel-widget">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-user glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">24</div>
                        <div class="text-muted">New Users</div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-12 col-md-6 col-lg-3">
            <div class="panel panel-red panel-widget">
                <div class="row no-padding">
                    <div class="col-sm-3 col-lg-5 widget-left">
                        <em class="glyphicon glyphicon-stats glyphicon-l"></em>
                    </div>
                    <div class="col-sm-9 col-lg-7 widget-right">
                        <div class="large">25.2k</div>
                        <div class="text-muted">Visitors</div>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">Site Traffic Overview</div>
                <div class="panel-body">
                    <div class="canvas-wrapper">
                        <canvas class="main-chart" id="line-chart" height="200" width="600"></canvas>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>好评率</h4>
                    <div class="easypiechart" id="easypiechart-blue" data-percent="92" ><span class="percent">${proportion1}%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>中评率</h4>
                    <div class="easypiechart" id="easypiechart-orange" data-percent="65" ><span class="percent">${proportion2}%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>差评率</h4>
                    <div class="easypiechart" id="easypiechart-teal" data-percent="56" ><span class="percent">${proportion3}%</span>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xs-6 col-md-3">
            <div class="panel panel-default">
                <div class="panel-body easypiechart-panel">
                    <h4>Visitors</h4>
                    <div class="easypiechart" id="easypiechart-red" data-percent="27" ><span class="percent">27%</span>
                    </div>
                </div>
            </div>
        </div>
    </div><!--/.row-->

    <div class="row">
        <div class="col-md-8">

            <div class="panel panel-default chat">
                <div class="panel-heading" id="accordion"><span class="glyphicon glyphicon-comment"></span> Chat</div>
                <div class="panel-body">
                    <ul>
                        <li class="left clearfix">`</li>
                        <c:forEach items="${commentList}" var="comment">
                            <li class="right clearfix">
								<span class="chat-img pull-right">
									<img src="http://placehold.it/80/dde0e6/5f6468" alt="User Avatar" class="img-circle" />
								</span>
                                <div class="chat-body clearfix">
                                    <div class="header">
                                        <strong class="pull-left primary-font"> JaneDoe</strong> <small class="text-muted">${comment.updateTime}</small>
                                    </div>
                                    <p>
                                    ${comment.commentContent}

                                    </p>
                                </div>
                            </li>
                        </c:forEach>

                    </ul>
                </div>

                <div class="panel-footer">
                    <div class="input-group">
                        <input id="btn-input" type="text" class="form-control input-md" placeholder="Type your message here..." />
                        <span class="input-group-btn">
								<button class="btn btn-success btn-md" id="btn-chat">Send</button>
							</span>
                    </div>
                </div>
            </div>

        </div><!--/.col-->


    </div><!--/.col-->
</div><!--/.row-->
</div>	<!--/.main-->

<script src="/js/jquery-1.11.1.min.js"></script>
<script src="/js/bootstrap.min.js"></script>
<script src="/js/chart.min.js"></script>
<script src="/js/chart-data.js"></script>
<script src="/js/easypiechart.js"></script>
<script src="/js/easypiechart-data.js"></script>
<script src="/js/bootstrap-datepicker.js"></script>
<script>
    $('#calendar').datepicker({
    });

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
</body>

</html>
