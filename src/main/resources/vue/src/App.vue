<template>
  <div>
    <left-menu></left-menu>
    <div id="page-wrapper" class="gray-bg dashbard-1">
      <top-bar></top-bar>
      <div class="row" id="app">
        <router-view/>
      </div>
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
    }
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }
</style>
