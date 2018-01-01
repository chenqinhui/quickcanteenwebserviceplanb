<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<title>订单结算</title>
		<meta name="keywords" content="KEYWORDS..." />
		<meta name="description" content="DESCRIPTION..." />
		<meta name="author" content="DeathGhost" />
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name='apple-touch-fullscreen' content='yes'>
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="format-detection" content="telephone=no">
		<meta name="format-detection" content="address=no">
		<link rel="icon" href="/images/icon/favicon.ico" type="image/x-icon">
		<link rel="apple-touch-icon-precomposed" sizes="57x57" href="/images/icon/apple-touch-icon-57x57-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="120x120" href="/images/icon/apple-touch-icon-120x120-precomposed.png">
		<link rel="apple-touch-icon-precomposed" sizes="196x196" href="/images/icon/apple-touch-icon-196x196-precomposed.png">
		<meta name="viewport" content="initial-scale=1, width=device-width, maximum-scale=1, user-scalable=no">
		<link rel="stylesheet" type="text/css" href="/wechat_css/reset.css">
		<link rel="stylesheet" type="text/css" href="/wechat_css/style.css" />
		<script type="text/javascript" src="/wechat_js/jquery-1.11.1.min.js" ></script>
		<script type="text/javascript" src="/wechat_js/date.js" ></script>
		<script type="text/javascript" src="/wechat_js/iscroll.js" ></script>
		<script>
			$(function() {
				var all = $("#time").html();
				var m = Number(all.substring(0, all.indexOf(":")));
				var s = Number(all.substring(all.indexOf(":") + 1, all.length + 1));
				var f = setInterval(function() {
					if(s < 10) {
						//如果秒数少于10在前面加上0
						$('#time').html(m + ':0' + s);
					} else {
						$('#time').html(m + ':' + s);
					}
					s--;
					if(s < 0) {
						//如果秒数少于0就变成59秒
						if(m==0){
							window.clearInterval(f); 
						}
						m--;
						s = 59;
					}
				}, 1000)
			})
			/*支付标签切换*/
			$(function(){
				$(".payway").click(function(){
					$(".fr>img").attr("src","/images/check (1).png");
					$(this).find(".fr>img").attr("src","/images/check (2).png") ;
				})
			})
		</script>

	<script type="text/javascript">
		$(function(){
			$('#beginTime').date();
			$('#endTime').date({theme:"datetime"});
		});
	</script>
	</head>

	<body style="overflow-y: scroll;height: 100%;">
		<div class="pay-order-header">
			<ul>
				<li>支付剩余时间</li>
				<li id="time">3:00</li>
			</ul>
		</div>
		
		<div class="order-details-header clearfix">			
			<h4 class="fl">就餐方式</h4>	
			<select class="fr" name="selects" id="selects" onchange="selectWay(this);">
				<option id="take_out" value="take_out">打包</option>
				<option id="eat_in" value="eat_in">堂吃</option>
				<option id="distribute" value="distribute">外卖</option>
			</select>
		</div>
		<script type="text/javascript" language="javascript">
            function selectWay(item) {
                var selected = $(item);
                //console.log(selected.val());
                if (selected.val()==="distribute") {
                    $("#order-settlement-address").css('display','');
                }
                else {
                    $("#order-settlement-address").css('display','none');
                }
            }
		</script>
        <div class="order-details-header clearfix" id="order-settlement-address" style="display: none">
            <h4 class="fl">送餐地址</h4>
            <h4 align="right" style="color: #000;text-align: right" href="#">${defaultAddress}</h4>
        </div>
		<div class="invoice" id="order-settlement">
			<span><input type="radio" name="radiobutton" value="radiobutton" checked> 即时 </span>
			<span><input type="radio" name="radiobutton" value="radiobutton" checked>预约时间
				<input id="endTime" class="kbtn" placeholder="预约时间" /></span>
		</div>
		<table width="100%" class="bg-fff order-det-cont" >
			<tbody>
				<tr>
					<td align="left" class="padl3" style="color:#999">订单号</td>
					<td align="right" width="50%" style="color:#999">${order.orderId}</td>
				</tr>
				<tr style="border-bottom: solid 8px #f1f1f1;">
					<td align="left" class="padl3">食堂名称</td>
					<td align="right" width="50%">${order.companyId}</td>
				</tr>
				<tr>
					<td align="left" class="padl3" style="color:#999">餐品详情</td>
					<td align="right" width="50%" style="color:#999">更多<img style="width:.3rem;" src="/images/jtx1.png" /></td>
				</tr>
				<#list order.dishesVos as dishes>
                <tr>
                    <td align="left" class="padl3">${dishes.dishesName}</td>
                    <td align="right" width="50%">×<em>${dishes.count}</em></td>
                </tr>
				</#list>
				<tr>
					<td align="left" colspan="1" class="padl3">餐盒费</td>
					<td align="right" class="padr3">￥<em>1</em></td>
				</tr>
                <#if order.deliverManId!=0>
                <tr>
                    <td align="left" colspan="1" class="padl3">配送费</td>
                    <td align="right" class="padr3">￥<em>5</em></td>
                </tr>
                </#if>
				<tr>
					<td align="left" colspan="1" class="padl3">餐品总额</td>
					<#if order.deliverManId!=0>
						<td align="right" class="padr3">￥<em>${order.totalPrice+1}</em></td>
					<#else>
                        <td align="right" class="padr3">￥<em>${order.totalPrice+5+1}</em></td>
					</#if>
				</tr>
				<tr>
					<td align="left" colspan="1" class="padl3">优惠券</td>
					<td align="right" class="padr3 color-ec7602">
						<a href="#" class="padding-right23 colore2bf1e">两张可用
						<i href="#" class="isNext"></i>
					</td>
				</tr>
				<tr>
					<td align="left" class="padl3">订单</td>	
					<td align="right" class="padr3 color-ec7602" style="font-size: 16px;">
						<a href="#" class="padding-right23 colore2bf1e">积分抵￥0.00
						<i href="#" class="isNext"></i>
					</td>
				</tr>				
				<tr style="border-top: solid 8px #f1f1f1;">
					<td align="left" colspan="1" class="padl3">实付金额:</td>
					<td align="right" class="padr3">
						<a href="#" class="padding-right23 colorf00">￥
						<#if order.deliverManId!=0>
                    		${order.totalPrice+1}
						<#else>
                    		${order.totalPrice+5+1}
						</#if>
						<i href="#" class="isNext"></i>
					</td>
				</tr>			
			</tbody>
		</table>
		
		<div style="height:1rem;"></div>
		<div class="order-set-paybutton">
			<div class="paybutton-left fl" style="width: 50%;text-align: center;">取消</div>
			<div class="paybutton-right fr" style="width: 50%;text-align: center;"><a onclick="updateOrderStatus(${order.orderId},100);return false;">确认订单</a></div>
			<div class="clearfix"></div>
		</div>
        <script type="text/javascript">
            function updateOrderStatus(orderId, toStatus) {
                alert("!!!");
                $.ajax({
                    type: "post",
                    url: "/api/order/updateOrderState",
                    timeout: 8000,
                    dataType: "json",
                    data: {
                        "orderId": orderId,
                        "orderStatus": toStatus
                    },
                    success: function () {
                        alert("修改成功");
                        if(toStatus==100)
                        	window.location.href = "/weChat/success/"+orderId;
                        if(toStatus==80)
                            window.location.href = "/weChat/index";
                    },
                    error: function () {
                        alert("请求出错")
                    }
                })
            }
		</script>
<div id="datePlugin"></div>
	</body>
	
</html>