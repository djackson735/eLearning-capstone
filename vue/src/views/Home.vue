<template>
  <div id="home">
    <SideNav/>
    <div id="content">
      <video-background :src="require( '@/assets/RoyaltyFreeDice.mp4' )"
                        style="max-height: 50%; height: 100vh;">
        <h1 id="welcomeText" class="text-center " style="color: white;">Welcome to Dungeon Lab.</h1>
      </video-background>
    </div>
  </div>
</template>

<script>
import SideNav from "@/components/course/SideNav";
import VideoBackground from "vue-responsive-video-background-player";
export default {
  name: "home",
  components: {
    SideNav,
    VideoBackground
  },
};
</script>

<style>
/*#home {*/
/*  display: flex;*/
/*  flex-basis: 100%;*/
/*  justify-content: flex-start;*/
/*}*/

/*#content {*/
/*  float: right;*/
/*  flex-basis: 100%;*/
/*  background-color: lightgray;*/
/*}*/
</style>