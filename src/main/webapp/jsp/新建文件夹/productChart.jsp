<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>ECharts</title>
<script type="text/javascript" src="js/echarts.common.min.js"></script>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
</head>
<body>
	<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
	<div id="barChart" style="width: ; height: 400px;"></div>
	<div id="pieChart" style="width: 600px; height: 400px;"></div>
	<script type="text/javascript">
		var barChart = echarts.init(document.getElementById('barChart'));
		var pieChart = echarts.init(document.getElementById('pieChart'));
		barChart.showLoading();
		pieChart.showLoading();
		//使用ajax加载数据
		$.ajax({
			method:'get',
			url:'${pageContext.request.contextPath}/getCategoryBarChart',
			dataType:'json',
			success:function(data){
				//alert("成功");
				
				initBarChart(data);
				
				//alert(data);
				},
			error:function(){
				//alert("失败");
			},
			complete:function(){
				$.ajax({
					method:'get',
					url:'${pageContext.request.contextPath}/getCategoryPieChart',
					dataType:'json',
					success:function(data){
						//alert("成功");
						initPieChart(data);
						//alert("123"+data);
						},
					error:function(){
						alert("失败");
					}
					});
			}
			});
			
		function initBarChart(data){
			// 基于准备好的dom，初始化echarts实例
			
			// 显示标题，图例和空的坐标轴
			barChart.hideLoading();
			barChart.setOption({
				
				title : {
					text : '商品类目种类数量柱状图',
					x:'center'
				},
				tooltip : {},
				legend : {
					orient: 'vertical',
	                left: 'left',
					data : [ '商品' ]
				},
				xAxis : {
					data : data.category
				},
				yAxis : {},
				series : [ {
					name : '商品',
					type : 'bar',
					data : data.productNum
				} ]
			});
			
			/* myChart.showLoading();
			// 异步加载数据
			$.get('data.json').done(function(data) {
				// 填入数据
				myChart.hideLoading();
				myChart.setOption({
					xAxis : {
						data : data.category
					},
					series : [ {
						// 根据名字对应到相应的系列
						name : '数量',
						data : data.productNum
					} ]
				});
			}); */
		}
		function initPieChart(data){
			// 基于准备好的dom，初始化echarts实例
			
			// 显示标题，图例和空的坐标轴
			pieChart.hideLoading();
			pieChart.setOption({
				backgroundColor:'#F8F8F8',
				title : {
					text : '商品类目种类数量饼图',
					x:'center'
				},
				tooltip : {},
				legend : {
					orient: 'vertical',
	                left: 'left',
					data : data
				},
				series : [ {
					name : '商品数量',
					type : 'pie',
					radius : '55%',
                    center: ['50%', '60%'],
                    data:data,
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(0,0, 0, 0.3)'
                            }
                        }
                    },
                    labelLine: {
                        normal: {
                            lineStyle: {
                                color: 'rgba(0, 0, 0, 0.3)'
                            }
                        }
                    },
                    itemStyle: {
                        normal: {
                            shadowBlur: 200,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }
				} ]
			});
		}
	</script>
</body>
</html>
	