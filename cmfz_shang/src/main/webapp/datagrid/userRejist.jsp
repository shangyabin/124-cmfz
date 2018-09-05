<%--
  Created by IntelliJ IDEA.
  User: 小尚
  Date: 2018/9/4
  Time: 23:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" pageEncoding="utf-8" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/echarts.min.js"></script>
<script type="text/javascript">
    $(function(){


        //基于准备好的dom，出事话echarts实例
        var myChart=echarts.init(document.getElementById('main'));
        //指定图标的配置项和数据
        $.ajax({
            url:"${pageContext.request.contextPath}/test/test",
            type:"POST",
            success:function(data){
                var option={
                    title:{
                        text:'cmfz活跃用户统计', //标题
                    },
                    tooltip:{},  //提示框组件
                    legend:{  //图例组建
                        data:['柱状','折线']
                    },
                    xAxis:{  //x轴
                        data:["一周内","两周内","三周内"]
                    },
                    yAxis:{},//y轴
                    series:[{
                        name:'柱状', //坐标轴名称
                        type:'bar',  //柱状图
                        data:data  //数据
                    },{
                        name:'折线',
                        type:"line",
                        data:data
                    }]
                };
                //使用刚指定的配置项和数据显示图表
                myChart.setOption(option);
            }
        });

    })
</script>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>