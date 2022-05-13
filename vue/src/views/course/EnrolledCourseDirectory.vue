<template>
<div id="home">
  <div id="menu">
    <SideNav />
  </div>
  <div id="content">
    <enrolled-courses />
  </div>
</div>
</template>

<script>
import EnrolledCourses from '@/components/course/EnrolledCourses';
import SideNav from "@/components/course/SideNav";

export default {
  components: {
    SideNav,
    EnrolledCourses
  }
};
</script>
<style scoped>
/*#home {*/
/*  display: flex;*/
/*  flex-basis: 100%;*/
/*  justify-content: flex-start;*/
/*}*/
/*#menu {*/
/*  float: left;*/
/*  flex-basis: 300px;*/
/*  text-align: left;*/
/*  background-color: white;*/
/*  border: solid #42c2f5 2px;*/
/*  border-top: transparent;*/
/*}*/
/*#content {*/
/*  float: right;*/
/*  flex-basis: 100%;*/
/*  background-color: lightgray;*/
/*}*/
</style>