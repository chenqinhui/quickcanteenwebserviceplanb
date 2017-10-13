<style type="text/css">
    .sidebar ul.nav li.parent ul li.active a {
        background-color: #30a5ff;
        color: #fff !important;
    }
</style>
<div id="sidebar-collapse" class="col-sm-3 col-lg-2 sidebar">
    <form role="search">
        <div class="form-group">
            <input type="text" class="form-control" placeholder="Search">
        </div>
    </form>
    <ul class="nav menu">
        <li <#if module == "index">class="active"</#if>><a href="index"><span
                class="glyphicon glyphicon-dashboard"></span>主页</a></li>
        <li <#if module == "dishes">class="active"</#if>><a href="dishes"><span
                class="glyphicon glyphicon-list-alt"></span> 菜品</a></li>
        <li <#if module == "comments">class="active"</#if>><a href="panels"><span
                class="glyphicon glyphicon-comment"></span>用户评价</a></li>
        <li class="parent">
            <a href="orders">
                <span class="glyphicon glyphicon-list"></span>订单<span data-toggle="collapse" href="#sub-item-1"
                                                                      class="icon pull-right"><em
                    class="glyphicon glyphicon-s glyphicon-plus"></em></span>
            </a>
            <ul class="children collapse" id="sub-item-1">
            <#list statusList as s>
                <li <#if status?? && status.getDesc() == s.getDesc()>class="active"</#if>>
                    <a href="orders?status=${s.getValue()}">
                        <span class="glyphicon glyphicon-share-alt"></span>${s.getDesc()}(${orderCountByStatus[s.getDesc()?string]})
                    </a>
                </li>
            </#list>
            </ul>
        </li>
        <li role="presentation" class="divider"></li>
        <li><a href="profile"><span class="glyphicon glyphicon-user"></span>用户信息</a></li>
    </ul>
</div><!--/.sidebar-->