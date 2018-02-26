<template>
    <div>
        <div class="row wrapper border-bottom white-bg page-heading">
            <div class="col-lg-10">
                <h2>计算资源节点：</h2>
                <!-- col-md-4 start here -->
                <span>
                    <i style="font-size: 24px" class="fa fa-calculator color-blue"></i>
                    <span class="stats-number s24 strong" data-from="0" data-to="12750">总节点：100</span>
                </span>
                <span>
                    <i class="fa fa-check" style="color: #61c16d;margin-left: 30px"></i>
                    <span class="stats-number s16 strong" data-from="0" data-to="12750">已注册节点：100</span>
                </span>
                <span>
                    <i class="fa fa-circle" style="color: red;margin-left: 30px"></i>
                    <span class="stats-number s16 strong" data-from="0" data-to="12750">未注册节点：0</span>
                </span>
                <span>
                    <i class="fa fa-circle" style="color: blue;margin-left: 30px"></i>
                    <span class="stats-number s16 strong" data-from="0" data-to="12750">已运行节点：88</span>
                </span>
                <span>
                    <i class="fa fa-circle" style="color: orange;margin-left: 30px"></i>
                    <span class="stats-number s16 strong" data-from="0" data-to="12750">未运行节点：12</span>
                </span>
            </div>
            <div class="col-lg-2">

            </div>
        </div>
        <br/>
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-bar-chart"></i> 访问量TOP排序及响应时间</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="fullscreen-link">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div :class="className" :id="id" :style="{height:height,width:width}" ref="chart1">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-arrows"></i> 系统消息通知</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="fullscreen-link">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <table style='width: 494.5px;height: 320px'>
                        <tr>
                            <td><i class="fa fa-circle" style="color: red;"></i>错误</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                        <tr>
                            <td><i class="fa fa-circle" style="color: red;"></i>错误</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                        <tr>
                            <td><i class="fa fa-circle" style="color: red;"></i>错误</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                        <tr>
                            <td><i class="fa fa-circle" style="color: red;"></i>错误</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                        <tr>
                            <td><i class="fa fa-circle" style="color: red;"></i>错误</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                        <tr>
                            <td><i class="fa fa-circle" style="color: red;"></i>错误</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                        <tr>
                            <td><i class="fa fa-circle" style="color: orange;"></i>警告</td>
                            <td>192.168.1.144这台服务器发生了错误</td>
                            <td>2017-11-13 05:35:45</td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-line-chart"></i> API网关连接个数</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="fullscreen-link">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div :class="className" :id="id" :style="{height:height,width:width}" ref="chart2">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-line-chart"></i> 弹性执行情况</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="fullscreen-link">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div :class="className" :id="id" :style="{height:height,width:width}" ref="chart3">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-line-chart"></i> 任务队列执行情况</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="fullscreen-link">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div :class="className" :id="id" :style="{height:height,width:width}" ref="chart4">
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-6">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-bar-chart"></i> 已发布API数及运行情况</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                        <a class="fullscreen-link">
                            <i class="fa fa-expand"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <div :class="className" :id="id" :style="{height:height,width:width}" ref="chart5">
                    </div>
                </div>
            </div>
        </div>
        <br/>
    </div>
</template>
<script>
    import echarts from 'echarts'

    export default {
        props: {
            className: {
                type: String,
                default: 'yourClassName'
            },
            id: {
                type: String,
                default: 'yourID'
            },
            width: {
                type: String,
                default: '100%'
            },
            height: {
                type: String,
                default: '320px'
            }
        },
        data() {
            return {
                chart1: null,
                chart2: null,
                chart3: null,
                chart4: null,
                chart5: null,
            }
        },
        mounted() {
            this.initChart();
        },
        methods: {
            initChart() {
                this.chart1 = echarts.init(this.$refs.chart1);
                this.chart1.setOption({
                    color: ["#bbe0bd"],
                    title: {
                        text: '最高访问量耗时',
                        left: 'center',
                    },
                    tooltip: {
                        trigger: 'axis',
                        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                        }
                    },
                    legend: {
                        data: ['']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    xAxis: {
                        type: 'value'
                    },
                    yAxis: {
                        type: 'category',
                        data: ['0.5秒', '1秒', '2秒', '4秒', '5秒', '10秒', '15秒', '30秒', '45秒', '1分钟']
                    },
                    series: [
                        {
                            name: 'API1',
                            type: 'bar',
                            // stack: '总量',
                            label: {
                                normal: {
                                    show: true,
                                    position: 'insideRight'
                                }
                            },
                            data: [450, 873, 1600, 2800, 5300, 7450, 9890, 12300, 14800, 17200]
                        },
                    ]
                });

                this.chart2 = echarts.init(this.$refs.chart2);
                this.chart2.setOption({

                    color: ['#607d8b', '#4caf50', '#61a0a8', '#d48265'],
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    legend: {
                        data: ['网关1', '网关2', '网关3', '网关4']
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ['10分钟', '20分钟', '30分钟', '40分钟', '50分钟', '1小时', '90分钟']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            symbol: 'circle',//拐点样式
                            symbolSize: 8,//拐点大小
                            name: '网关1',
                            type: 'line',
                            stack: '总量',
                            smooth: true,
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            symbol: 'circle',//拐点样式
                            symbolSize: 8,//拐点大小
                            name: '网关2',
                            type: 'line',
                            stack: '总量',
                            smooth: true,
                            data: [220, 182, 191, 234, 290, 330, 310]
                        },
                        {
                            symbol: 'circle',//拐点样式
                            symbolSize: 8,//拐点大小
                            name: '网关3',
                            type: 'line',
                            stack: '总量',
                            smooth: true,
                            data: [150, 232, 201, 154, 190, 330, 410]
                        },
                        {
                            symbol: 'circle',//拐点样式
                            symbolSize: 8,//拐点大小
                            name: '网关4',
                            type: 'line',
                            stack: '总量',
                            smooth: true,
                            data: [320, 332, 301, 334, 390, 330, 320]
                        },

                    ]
                });

                this.chart3 = echarts.init(this.$refs.chart3);
                this.chart3.setOption({
                    color: ['#4caf50', '#607d8b'],
                    title: {
                        text: '一段时间内发生的弹性次数',
                        lfet: "center"
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    legend: {
                        data: ['API网关弹性', 'API服务弹性']
                    },
                    calculable: true,
                    xAxis: [
                        {
                            type: 'category',
                            boundaryGap: false,
                            data: ['1秒', '30秒', '60秒', '90秒', '120秒', '150秒']
                        }
                    ],
                    yAxis: [
                        {
                            type: 'value'
                        }
                    ],
                    series: [
                        {
                            name: 'API网关弹性',
                            symbol: 'circle',//拐点样式
                            symbolSize: 8,//拐点大小
                            type: 'line',
                            stack: '总量',
                            smooth: true,
                            areaStyle: {
                                normal: {
                                    opacity: 0.3
                                }
                            },
                            data: [120, 132, 101, 134, 90, 230]
                        },
                        {
                            name: 'API服务弹性',
                            symbol: 'circle',//拐点样式
                            symbolSize: 8,//拐点大小
                            type: 'line',
                            stack: '总量',
                            smooth: true,
                            areaStyle: {
                                normal: {
                                    opacity: 0.3
                                }
                            },
                            data: [220, 182, 191, 234, 290, 330]
                        },

                    ]
                });

                this.chart4 = echarts.init(this.$refs.chart4);
                this.chart4.setOption({
                    color: ["#607d8b", "#a5d6a7"],
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['已运行的API', '未运行的API',]
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: true,
                        data: ['1小时', '6小时', '12小时', '1天', '2天', '5天', '1周']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '已运行的API',
                            type: 'bar',
                            // stack: '总量',
                            barWidth: '15',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name: '未运行的API',
                            type: 'bar',
                            barWidth: '15',
                            data: [220, 182, 191, 234, 290, 330, 310]
                        },
                    ]
                });

                this.chart5 = echarts.init(this.$refs.chart5);
                this.chart5.setOption({
                    color: ["#3fa4f5", "#6b8693"],
                    title: {
                        text: ''
                    },
                    tooltip: {
                        trigger: 'axis'
                    },
                    legend: {
                        data: ['一执行任务', '未执行任务']
                    },
                    grid: {
                        left: '3%',
                        right: '4%',
                        bottom: '3%',
                        containLabel: true
                    },
                    toolbox: {
                        feature: {
                            saveAsImage: {}
                        }
                    },
                    xAxis: {
                        type: 'category',
                        boundaryGap: true,
                        data: ['1小时', '6小时', '12小时', '1天', '2天', '5天', '1周']
                    },
                    yAxis: {
                        type: 'value'
                    },
                    series: [
                        {
                            name: '未执行任务',
                            type: 'bar',
                            // stack: '总量',
                            barWidth: '15',
                            data: [120, 132, 101, 134, 90, 230, 210]
                        },
                        {
                            name: 'API2',
                            type: 'bar',
                            barWidth: '15',
                            data: [220, 182, 191, 234, 290, 330, 310]
                        },
                    ]
                });
            }
        }
    }
</script>