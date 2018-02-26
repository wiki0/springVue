<template>
    <div>
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <img src="../../assets/logo.png">
                        </div>
                        <div class="logo-element">
                            <img src="../../assets/logosm.png">
                        </div>
                    </li>
                    <li :class="{'active':$route.name==='/'}">
                        <router-link to="/"><i class="fa fa-home"></i> <span class="nav-label">首页</span></router-link>
                    </li>
                    <li :class="{ 'active': $route.name==='hostMonitor'|| $route.name==='hostList' }">
                        <a href="#"><i class="fa fa-desktop"></i> <span class="nav-label">宿主机</span><span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li :class="{ 'active': $route.name==='hostMonitor' }">
                                <router-link to="/hostMonitor"><i class="fa fa-folder-open"></i>宿主机监控</router-link>
                            </li>
                            <li :class="{ 'active': $route.name==='hostList' }">
                                <router-link to="/hostList"><i class="fa fa-list"></i>宿主机列表</router-link>
                            </li>
                        </ul>
                    </li>
                    <li :class="{'active':$route.name==='containerMonitor'}">
                        <router-link to="/containerMonitor"><i class="fa fa-archive"></i> <span class="nav-label">容器</span></router-link>
                    </li>
                    <li :class="{ 'active': $route.name==='appForward'|| $route.name==='appCollect' }">
                        <a href="#"><i class="fa fa-ge"></i> <span class="nav-label">应用</span><span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li :class="{ 'active': $route.name==='appForward' }">
                                <router-link to="/appForward"><i class="fa fa-mail-forward"></i>转发应用</router-link>
                            </li>
                            <li :class="{ 'active': $route.name==='appCollect' }">
                                <router-link to="/appCollect"><i class="fa fa-history"></i>收录应用</router-link>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-paper-plane"></i> <span class="nav-label">API</span> <span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="api-monitor.html"><i class="fa fa-bar-chart"></i>API监控</a></li>
                            <li><a href="api-static.html"><i class="fa fa-bar-chart"></i>API访问统计</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cloud"></i> <span class="nav-label">服务</span> <span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="service-statistics.html"><i class="fa fa-list"></i>服务统计</a></li>
                            <li><a href="service-monitor.html"><i class="fa fa-folder-open"></i>服务监控</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-tasks"></i> <span class="nav-label">任务管理</span> <span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="service-monitor.html"><i class="fa fa-list"></i>任务监控</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="container.html"><i class="fa fa-th-large"></i> <span class="nav-label">网络</span> </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-cloud"></i> <span class="nav-label">日志</span> <span
                                class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="service-statistics.html"><i class="fa fa-list"></i>日志查询</a></li>
                            <li><a href="service-monitor.html"><i class="fa fa-folder-open"></i>日志分析</a></li>
                        </ul>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">全链路日志</span> </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">事件</span> </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">中间件</span> </a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-th-large"></i> <span class="nav-label">分布式存储</span> </a>
                    </li>
                </ul>

            </div>
        </nav>
    </div>
</template>

<script>
    import store from '@/vuex/store';

    export default {
        name: "left-menu",
        data() {
            return {}
        },
        store
    }
</script>

<style scoped>

</style>
