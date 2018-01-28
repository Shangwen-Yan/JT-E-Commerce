	$(function(){
		// 基于准备好的dom，初始化echarts实例
        var myChart1 = echarts.init($("#main1")[0]);

        // 指定图表的配置项和数据
        var option = {
            title: {
                text: 'ECharts 入门示例'
            },
            tooltip: {},
            legend: {
                data:['销量']
            },
            xAxis: {
                data: ["衬衫","羊毛衫","雪纺衫","裤子","高跟鞋","袜子"]
            },
            yAxis: {},
            dataZoom: [
                       {   // 这个dataZoom组件，默认控制x轴。
                           type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                           start: 10,      // 左边在 10% 的位置。
                           end: 60         // 右边在 60% 的位置。
                       },
                       {   // 这个dataZoom组件，也控制x轴。
                           type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
                           start: 10,      // 左边在 10% 的位置。
                           end: 60         // 右边在 60% 的位置。
                       }
                   ],
            series: [{
                name: '销量',
                type: 'bar',
                itemStyle: {
                    normal: {
                        opacity: 0.8
                    }
                },
                data: [5, 20, 36, 10, 10, 20]
            }]
        };

        // 使用刚指定的配置项和数据显示图表。
        myChart1.setOption(option);
        
//-----------------------------------------------------------------------------
        
        var myChart2 = echarts.init($("#main2")[0]);
        myChart2.showLoading();
        myChart2.hideLoading();
        myChart2.setOption({
        	title:{text:'一个饼状图'},
        	backgroundColor: '#FFB6C1',
        	textStyle: {
                color: 'rgba(255, 255, 255, 1)'
            },
        	itemStyle: {
        	    normal: {
        	        // 阴影的大小
        	        shadowBlur: 200,
        	        // 阴影水平方向上的偏移
        	        shadowOffsetX: 0,
        	        // 阴影垂直方向上的偏移
        	        shadowOffsetY: 0,
        	        // 阴影颜色
        	        shadowColor: 'rgba(0, 0, 0, 0.5)'
        	    },
        	    emphasis: {
        	        shadowBlur: 200,
        	        shadowColor: 'rgba(0, 0, 0, 0.5)'
        	    }
        	},

            series : 
                {
                    name: '访问来源',
                    type: 'pie',
                   // label:'outside',
                    //labelLine: 'normal',
                    label: {
                        normal: {
                            textStyle: {
                                color: 'rgba(255, 255, 255, 1)'
                            }
                        }
                    },
                    roseType: 'angle',
                    radius: '55%',
                    data:[
                        {value:235, name:'视频广告'},
                        {value:274, name:'联盟广告'},
                        {value:310, name:'邮件营销'},
                        {value:335, name:'直接访问'},
                        {value:400, name:'搜索引擎'}
                    ],
                    visualMap: {
                        // 不显示 visualMap 组件，只用于明暗度的映射
                        show: false,
                        // 映射的最小值为 80
                        min: 80,
                        // 映射的最大值为 600
                        max: 600,
                        inRange: {
                            // 明暗度的范围是 0 到 1
                            colorLightness: [0, 1]
                        }
                    }   
                }
        });
        
//---------------------------------------------------------
        var myChart3 = echarts.init($("#main3")[0]);

        var categoryData = [
                            '北京','天津','河北','山西','内蒙古','辽宁','吉林','黑龙江',
                            '上海','江苏','浙江','安徽','福建','江西','山东','河南',
                            '湖北','湖南','广东','广西','海南','重庆','四川','贵州',
                            '云南','西藏','陕西','甘肃','青海','宁夏','新疆'
                        ];


                        option = {
                            baseOption: {
                                timeline: {
                                    axisType: 'category',
                                    autoPlay: true,
                                    playInterval: 1000,
                                    data: [
                                        '2002-01-01', '2003-01-01', '2004-01-01',
                                        '2005-01-01', '2006-01-01', '2007-01-01',
                                        '2008-01-01', '2009-01-01', '2010-01-01',
                                        '2011-01-01'
                                    ],
                                    label: {
                                        formatter : function(s) {
                                            return (new Date(s)).getFullYear();
                                        }
                                    }
                                },
                                title: {
                                    subtext: 'Media Query 示例'
                                },
                                tooltip: {
                                    trigger:'axis',
                                    axisPointer: {
                                        type: 'shadow'
                                    }
                                },
                                xAxis: {
                                    type: 'value',
                                    name: 'GDP（亿元）',
                                    max: 30000,
                                    data: null
                                },
                                yAxis: {
                                    type: 'category',
                                    data: categoryData,
                                    axisLabel: {interval: 0},
                                    splitLine: {show: false}
                                },
                                legend: {
                                    data: ['第一产业', '第二产业', '第三产业', 'GDP', '金融', '房地产'],
                                    selected: {
                                        'GDP': false, '金融': false, '房地产': false
                                    }
                                },
                                calculable : true,
                                series: [
                                    {name: 'GDP', type: 'bar'},
                                    {name: '金融', type: 'bar'},
                                    {name: '房地产', type: 'bar'},
                                    {name: '第一产业', type: 'bar'},
                                    {name: '第二产业', type: 'bar'},
                                    {name: '第三产业', type: 'bar'},
                                    {name: 'GDP占比', type: 'pie'}
                                ]
                            },
                            media: [
                                {
                                    option: {
                                        legend: {
                                            orient: 'horizontal',
                                            left: 'right',
                                            itemGap: 10
                                        },
                                        grid: {
                                            left: '10%',
                                            top: 80,
                                            right: 90,
                                            bottom: 100
                                        },
                                        xAxis: {
                                            nameLocation: 'end',
                                            nameGap: 10,
                                            splitNumber: 5,
                                            splitLine: {
                                                show: true
                                            }
                                        },
                                        timeline: {
                                            orient: 'horizontal',
                                            inverse: false,
                                            left: '20%',
                                            right: '20%',
                                            bottom: 10,
                                            height: 40
                                        },
                                        series: [
                                            {name: 'GDP占比', center: ['75%', '30%'], radius: '28%'}
                                        ]
                                    }
                                },
                                {
                                    query: {maxWidth: 670, minWidth: 550},
                                    option: {
                                        legend: {
                                            orient: 'horizontal',
                                            left: 200,
                                            itemGap: 5
                                        },
                                        grid: {
                                            left: '10%',
                                            top: 80,
                                            right: 90,
                                            bottom: 100
                                        },
                                        xAxis: {
                                            nameLocation: 'end',
                                            nameGap: 10,
                                            splitNumber: 5,
                                            splitLine: {
                                                show: true
                                            }
                                        },
                                        timeline: {
                                            orient: 'horizontal',
                                            inverse: false,
                                            left: '20%',
                                            right: '20%',
                                            bottom: 10,
                                            height: 40
                                        },
                                        series: [
                                            {name: 'GDP占比', center: ['75%', '30%'], radius: '28%'}
                                        ]
                                    }
                                },
                                {
                                    query: {maxWidth: 550},
                                    option: {
                                        legend: {
                                            orient: 'vertical',
                                            left: 'right',
                                            itemGap: 5
                                        },
                                        grid: {
                                            left: 55,
                                            top: '32%',
                                            right: 100,
                                            bottom: 50
                                        },
                                        xAxis: {
                                            nameLocation: 'middle',
                                            nameGap: 25,
                                            splitNumber: 3
                                        },
                                        timeline: {
                                            orient: 'vertical',
                                            inverse: true,
                                            right: 10,
                                            top: 150,
                                            bottom: 10,
                                            width: 55
                                        },
                                        series: [
                                            {name: 'GDP占比', center: ['45%', '20%'], radius: '28%'}
                                        ]
                                    }
                                }
                            ],
                            options: [
                                {
                                    title: {text: '2002全国宏观经济指标'},
                                    series: [
                                        {data: dataMap.dataGDP['2002']},
                                        {data: dataMap.dataFinancial['2002']},
                                        {data: dataMap.dataEstate['2002']},
                                        {data: dataMap.dataPI['2002']},
                                        {data: dataMap.dataSI['2002']},
                                        {data: dataMap.dataTI['2002']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2002sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2002sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2002sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2003全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2003']},
                                        {data: dataMap.dataFinancial['2003']},
                                        {data: dataMap.dataEstate['2003']},
                                        {data: dataMap.dataPI['2003']},
                                        {data: dataMap.dataSI['2003']},
                                        {data: dataMap.dataTI['2003']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2003sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2003sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2003sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2004全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2004']},
                                        {data: dataMap.dataFinancial['2004']},
                                        {data: dataMap.dataEstate['2004']},
                                        {data: dataMap.dataPI['2004']},
                                        {data: dataMap.dataSI['2004']},
                                        {data: dataMap.dataTI['2004']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2004sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2004sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2004sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2005全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2005']},
                                        {data: dataMap.dataFinancial['2005']},
                                        {data: dataMap.dataEstate['2005']},
                                        {data: dataMap.dataPI['2005']},
                                        {data: dataMap.dataSI['2005']},
                                        {data: dataMap.dataTI['2005']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2005sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2005sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2005sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2006全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2006']},
                                        {data: dataMap.dataFinancial['2006']},
                                        {data: dataMap.dataEstate['2006']},
                                        {data: dataMap.dataPI['2006']},
                                        {data: dataMap.dataSI['2006']},
                                        {data: dataMap.dataTI['2006']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2006sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2006sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2006sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2007全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2007']},
                                        {data: dataMap.dataFinancial['2007']},
                                        {data: dataMap.dataEstate['2007']},
                                        {data: dataMap.dataPI['2007']},
                                        {data: dataMap.dataSI['2007']},
                                        {data: dataMap.dataTI['2007']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2007sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2007sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2007sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2008全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2008']},
                                        {data: dataMap.dataFinancial['2008']},
                                        {data: dataMap.dataEstate['2008']},
                                        {data: dataMap.dataPI['2008']},
                                        {data: dataMap.dataSI['2008']},
                                        {data: dataMap.dataTI['2008']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2008sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2008sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2008sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2009全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2009']},
                                        {data: dataMap.dataFinancial['2009']},
                                        {data: dataMap.dataEstate['2009']},
                                        {data: dataMap.dataPI['2009']},
                                        {data: dataMap.dataSI['2009']},
                                        {data: dataMap.dataTI['2009']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2009sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2009sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2009sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2010全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2010']},
                                        {data: dataMap.dataFinancial['2010']},
                                        {data: dataMap.dataEstate['2010']},
                                        {data: dataMap.dataPI['2010']},
                                        {data: dataMap.dataSI['2010']},
                                        {data: dataMap.dataTI['2010']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2010sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2010sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2010sum']}
                                        ]}
                                    ]
                                },
                                {
                                    title : {text: '2011全国宏观经济指标'},
                                    series : [
                                        {data: dataMap.dataGDP['2011']},
                                        {data: dataMap.dataFinancial['2011']},
                                        {data: dataMap.dataEstate['2011']},
                                        {data: dataMap.dataPI['2011']},
                                        {data: dataMap.dataSI['2011']},
                                        {data: dataMap.dataTI['2011']},
                                        {data: [
                                            {name: '第一产业', value: dataMap.dataPI['2011sum']},
                                            {name: '第二产业', value: dataMap.dataSI['2011sum']},
                                            {name: '第三产业', value: dataMap.dataTI['2011sum']}
                                        ]}
                                    ]
                                }
                            ]
                        };

                        myChart.setOption(option);



            myChart3.setOption(option);
		
	});	
