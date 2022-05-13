<template>
  <div id="home">
        <div id="menu">
          <SideNav />
        </div>
        <div id="content">
        <h1>Add New Curricula</h1>
        <curricula-form />
        </div>
        </div>
</template>

<script>
import CurriculaForm from "@/components/course/CurriculaForm";
import SideNav from "@/components/course/SideNav";

export default {
  components: {
    SideNav,
    CurriculaForm
  }
};
</script>