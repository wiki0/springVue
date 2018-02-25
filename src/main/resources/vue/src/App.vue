<template>
  <div>
    <left-menu></left-menu>
    <div id="page-wrapper" class="gray-bg">
      <top-bar></top-bar>
      <router-view/>
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

</style>
