<template>
    <div>
        <left-menu></left-menu>
        <div id="page-wrapper" class="gray-bg">
            <top-bar></top-bar>
            <div class="wrapper wrapper-content">
                <transition name="fade" mode="out-in">
                    <router-view></router-view>
                </transition>
            </div>
        </div>
        <!-- Back to top -->
        <div id="back-to-top"><a href="#">Back to Top</a>
        </div>
    </div>
</template>

<script>
    import leftMenu from '@/components/common/leftMenu'
    import topBar from '@/components/common/topBar'

    export default {
        name: 'App',
        components: {
            leftMenu,
            topBar
        },
        methods: {
            handleScroll() {
                var scrollTop = window.pageYOffset || document.documentElement.scrollTop || document.body.scrollTop;
                if (scrollTop > 200) {
                    $("#back-to-top").fadeIn(200);
                } else {
                    $("#back-to-top").fadeOut(200);
                }
            }
        },
        watch: {
            $route(to, from) {
                $('#page-wrapper').height(800);
            }
        },
        mounted() {
            $('#back-to-top, .back-to-top').click(function () {
                $('html, body').animate({scrollTop: 0}, '800');
                return false;
            });
            window.addEventListener('scroll', this.handleScroll)
        }
    }
</script>

<style>
    /* 可以设置不同的进入和离开动画 */
    /* 设置持续时间和动画函数 */
    .fade-enter-active {
        transition: all .3s ease;
    }

    .fade-leave-active {
        transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
    }

    .fade-enter, .fade-leave-to {
        transform: translateX(10px);
        opacity: 0;
    }

    #back-to-top {
        position: fixed;
        z-index: 1000;
        bottom: 10px;
        right: 10px;
        display: none;
    }

    #back-to-top a {
        display: block;
        width: 32px;
        height: 32px;
        text-indent: -9999px;
        border-radius: 2px;
        transition: all 0.4s cubic-bezier(0.445, 0.05, 0.55, 0.95);
    }

    #back-to-top.rightsidebar {
        right: 215px;
    }

    #back-to-top a {
        background: rgba(0, 0, 0, 0.4) url('./assets/backtop.png') no-repeat center center;
    }

    #back-to-top a:hover {
        background-color: #263238;
    }
</style>
