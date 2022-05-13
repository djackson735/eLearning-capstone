<template>
  <div id="home">
        <div id="menu">
          <SideNav />
        </div>
        <div id="content">
          <CourseEditor />
        </div>
        </div>
</template>

<script>
import CourseEditor from "@/components/course/CourseEditor";
import SideNav from "@/components/course/SideNav";

export default {
  name: "home",
  components: {
    SideNav,
    CourseEditor,
  },
};
</script>

<style>
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
/*  height: 1000px;*/
/*}*/
/*#content {*/
/*  float: right;*/
/*  flex-basis: 100%;*/
/*  background-color: lightgray;*/
/*}*/
</style>