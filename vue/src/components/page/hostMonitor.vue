<template>
    <div>
        <div class="col-lg-12">
            <div class="ibox">
                <div class="ibox-title">
                    <h5><i class="fa fa-bar-chart"></i> CPU/MEM利用率监控</h5>
                    <div class="ibox-tools">
                        <a class="collapse-link">
                            <i class="fa fa-chevron-up"></i>
                        </a>
                    </div>
                </div>
                <div class="ibox-content">
                    <ul class="nav nav-tabs" role="tablist">
                        <li class="active"><a href="#first" aria-controls="home" role="tab"
                                              data-toggle="pill">近一天的情况</a></li>
                        <li><a href="#second" aria-controls="profile" role="tab"
                               data-toggle="pill">近一周的情况</a></li>
                        <li><a href="#three" aria-controls="profile" role="tab"
                               data-toggle="pill">近一个月的情况</a></li>

                    </ul>
                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="first">
                            <div :class="className" :style="{height:height,width:width}" ref="chart1">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
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
            }
        },
        mounted() {
            this.initChart();
        },
        methods: {
            initChart() {
                this.chart1 = echarts.init(this.$refs.chart1);
                this.chart1.setOption({
                    title: {
                        text: 'CPU/MEM利用率'
                    },
                    tooltip: {
                        trigger: 'axis',
                        formatter:'{b0}<br/> {a0}:{c0}%<br/>{a1}:{c1}%'
                    },
                    legend: {
                        data:['CPU','MEM']
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
                        data: ['宿主机1', '宿主机2', '宿主机3', '宿主机4', '宿主机5', '宿主机6', '宿主机7','宿主机8','宿主机9','宿主机10']
                    },
                    yAxis: {
                        type: 'value',
                        axisLabel: {
                            show: true,
                            interval: 'auto',
                            formatter: '{value} %'
                        }
                    },
                    series: [
                        {
                            name: 'CPU',
                            type: 'bar',
                            // stack: '总量',
                            barWidth: '15',
                            data: [2.0, 3.0, 4, 7, 6, 3.2, 1.4, 3.3, 3.8, 2.9]
                        },
                        {
                            name: 'MEM',
                            type: 'bar',
                            barWidth: '15',
                            data: [2.2, 1.8, 3.0, 2.5, 4.4, 1.9, 4.5, 2.1, 7.3, 4.8]
                        }
                    ]
                });
            }
        }
    }
</script>

<style scoped>

</style>