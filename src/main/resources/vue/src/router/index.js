import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/page/HelloWorld'
import appForward from '@/components/page/appForward'

Vue.use(Router)

export default new Router({
    routes: [
        {
            path: '/',
            name: 'HelloWorld',
            component: HelloWorld
        },
        {
            path: '/app-forward',
            name: 'app-forward',
            component: appForward
        }
    ]
})
