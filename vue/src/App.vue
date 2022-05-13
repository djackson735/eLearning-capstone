<template>
  <div id="app">
    <Header />
    <Router-view/>

  </div>
</template>

<script>
import Header from '@/components/Header';
// import VideoBackground from "vue-responsive-video-background-player";
export default {
  components: {
    Header,
    // VideoBackground
  }
};
</script>

<!--<style>-->
<!--&lt;!&ndash;body {&ndash;&gt;-->
<!--&lt;!&ndash;  margin: 0;&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->
<!--&lt;!&ndash;#app {&ndash;&gt;-->
<!--&lt;!&ndash;  display: grid;&ndash;&gt;-->
<!--&lt;!&ndash;  grid-template-columns: 3% 1fr 3%;&ndash;&gt;-->
<!--&lt;!&ndash;  background-color: white;&ndash;&gt;-->
<!--&lt;!&ndash;}&ndash;&gt;-->
<!--&lt;!&ndash;</style>&ndash;&gt;-->