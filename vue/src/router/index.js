import Vue from 'vue'
import Router from 'vue-router'
import dashBoard from '@/components/page/dashBoard'
import appForward from '@/components/page/appForward'
import appCollect from '@/components/page/appCollect'
import hostList from '@/components/page/hostList'
import hostMonitor from '@/components/page/hostMonitor'
import containerMonitor from '@/components/page/containerMonitor'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: '/',
            component: dashBoard
        },
        {
            path: '/appForward',
            name:'appForward',
            component: appForward
        },
        {
            path: '/appCollect',
            name:'appCollect',
            component: appCollect
        },
        {
            path: '/containerMonitor',
            name:'containerMonitor',
            component: containerMonitor
        },
        {
            path: '/hostList',
            name:'hostList',
            component: hostList
        },
        {
            path: '/hostMonitor',
            name:'hostMonitor',
            component: hostMonitor
        }
    ]
})
